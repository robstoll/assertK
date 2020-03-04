@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")

package ch.tutteli.atrium.domain.builders.creating.changers

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.core.polyfills.cast
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.builders.creating.changers.impl.subjectchanger.*
import ch.tutteli.atrium.domain.creating.changers.ChangedSubjectPostStep
import ch.tutteli.atrium.domain.creating.changers.FailureHandlerAdapter
import ch.tutteli.atrium.domain.creating.changers.SubjectChanger
import ch.tutteli.atrium.domain.creating.changers.subjectChanger
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.translations.DescriptionAnyAssertion
import kotlin.reflect.KClass

/**
 * Defines the contract for sophisticated `change subject` processes.
 */
interface SubjectChangerBuilder {

    companion object {
        /**
         * Entry point to use the [SubjectChangerBuilder].
         */
        fun <T> create(expect: Expect<T>): KindStep<T> = KindStepImpl(expect)

        @Deprecated("Do no longer use Assert, use Expect instead - this method was introduced in 0.9.0 to ease the migration from Assert to Expect; will be removed with 1.0.0")
        @Suppress("DEPRECATION", "DeprecatedCallableAddReplaceWith")
        fun <T> create(
            originalPlant: SubjectProvider<T>
        ): DeprecatedKindStep<T> = DeprecatedKindStepImpl(originalPlant)
    }

    /**
     * Step where one has to decide the kind of subject change.
     *
     * @param T the type of the current subject.
     */
    @Deprecated("Do no longer use Assert, use Expect instead - this method was introduced in 0.9.0 to ease the migration from Assert to Expect; will be removed with 1.0.0")
    interface DeprecatedKindStep<T> {
        /**
         * The previously specified assertion plant to which the new [Assert] will delegate assertion checking.
         */
        @Suppress("DEPRECATION")
        val originalPlant: SubjectProvider<T>

        @Deprecated("Do no longer use Assert, use Expect instead - this method was introduced in 0.9.0 to ease the migration from Assert to Expect; will be removed with 1.0.0")
        @Suppress("DEPRECATION", "DeprecatedCallableAddReplaceWith")
        fun <R : Any> unreported(
            transformation: (T) -> R
        ): Assert<R> = subjectChanger.unreportedToAssert(originalPlant, transformation)

        @Deprecated("Do no longer use Assert, use Expect instead - this method was introduced in 0.9.0 to ease the migration from Assert to Expect; will be removed with 1.0.0")
        @Suppress("DEPRECATION", "DeprecatedCallableAddReplaceWith")
        fun <R> unreportedNullable(
            transformation: (T) -> R
        ): AssertionPlantNullable<R> = subjectChanger.unreportedNullableToAssert(originalPlant, transformation)
    }

    /**
     * Step where one has to decide the kind of subject change.
     *
     * @param T the type of the current subject.
     */
    interface KindStep<T> {
        /**
         * The previously specified assertion container to which the new [Expect] will delegate assertion checking.
         */
        val originalExpect: Expect<T>


        /**
         * First and final step in the change-subject-process -- changes the subject without showing the change as
         * such in reporting.
         *
         * @return The newly created [Expect] for the new subject.
         */
        fun <R> unreported(transformation: (T) -> R): Expect<R> =
            subjectChanger.unreported(originalExpect, transformation)

        /**
         * Entry point of the building process to not only change the subject but also report the change in reporting.
         *
         * Typically the change is documented by adding a [DescriptiveAssertion] to the new resulting [Expect].
         *
         * This is basically a guide towards [SubjectChanger.reported],
         * hence in a more verbose manner but also more readable in many cases.
         */
        fun reportBuilder(): DescriptionRepresentationStep<T>
    }

    /**
     * Step which allows to specify the description and representation of the change.
     *
     * @param T the type of the current subject.
     */
    interface DescriptionRepresentationStep<T> {
        /**
         * The previously specified assertion container to which the new [Expect] will delegate assertion checking.
         */
        val originalExpect: Expect<T>

        /**
         * Uses [DescriptionAnyAssertion.IS_A] as description of the change,
         * the given [subType] as representation and tries to perform a down-cast of [originalExpect]'s
         * [Expect.maybeSubject] to the given type [TSub]
         */
        //TODO once kotlin supports to have type parameters as upper bounds of another type parameter next to `: Any` we should restrict TSub : T & Any
        fun <TSub : Any> downCastTo(subType: KClass<TSub>): FailureHandlerOption<T, TSub> =
            withDescriptionAndRepresentation(DescriptionAnyAssertion.IS_A, subType)
                .withTransformation {
                    Option.someIf(subType.isInstance(it)) { subType.cast(it) }
                }

        /**
         * Uses the given [description] and [representation] to represent the change by delegating to the other overload
         * which expects a [Translatable] instead of a [String].
         *
         * See the other overload for further information.
         */
        fun withDescriptionAndRepresentation(description: String, representation: Any?): TransformationStep<T> =
            withDescriptionAndRepresentation(Untranslatable(description), representation)

        /**
         * Uses the given [description] and [representation] to represent the change.
         * Moreover, subsequent options in the building step allow to define rules when the change cannot be applied, in
         * such a case an alternative description and representation might be used (depending on the implementation and
         * chosen options).
         *
         * Notice, if you want to use text (e.g. a [String]) as [representation],
         * then wrap it into a [RawString] via [RawString.create] and pass the [RawString] instead.
         */
        fun withDescriptionAndRepresentation(description: Translatable, representation: Any?): TransformationStep<T>

        companion object {
            /**
             * Creates a [DescriptionRepresentationStep] in the context of the [SubjectChangerBuilder].
             */
            fun <T> create(
                originalAssertionContainer: Expect<T>
            ): DescriptionRepresentationStep<T> = DescriptionRepresentationStepImpl(originalAssertionContainer)
        }
    }

    /**
     * Step to define the transformation which yields the new subject wrapped into a [Some] if the transformation
     * as such can be carried out; otherwise [None].
     *
     * @param T the type of the current subject.
     */
    interface TransformationStep<T> {
        /**
         * The previously specified assertion container to which the new [Expect] will delegate assertion checking.
         */
        val originalExpect: Expect<T>

        /**
         * The previously specified description which describes the kind of subject change.
         */
        val description: Translatable

        /**
         * The previously specified representation of the change.
         */
        val representation: Any

        /**
         * Defines the new subject, most likely based on the current subject (but does not need to be).
         */
        fun <R> withTransformation(transformation: (T) -> Option<R>): FailureHandlerOption<T, R>

        companion object {
            /**
             * Creates a [TransformationStep] in the context of the [SubjectChangerBuilder].
             */
            fun <T> create(
                originalAssertionContainer: Expect<T>,
                description: Translatable,
                representation: Any
            ): TransformationStep<T> = TransformationStepImpl(originalAssertionContainer, description, representation)
        }
    }

    /**
     * Optional step which allows to specify a custom [SubjectChanger.FailureHandler].
     *
     * @param T the type of the current subject.
     * @param R the type of the new subject.
     */
    interface FailureHandlerOption<T, R> {
        /**
         * The so far chosen options up to the [TransformationStep] step.
         */
        val transformationStep: TransformationStep<T>

        /**
         * The previously specified new subject.
         */
        val transformation: (T) -> Option<R>

        /**
         * Uses the given [failureHandler] as [SubjectChanger.FailureHandler]
         * to create the failing assertion in case the subject change fails.
         */
        fun withFailureHandler(failureHandler: SubjectChanger.FailureHandler<T, R>): FinalStep<T, R>

        /**
         * Uses the given [failureHandler] as [SubjectChanger.FailureHandler]
         * to create the failing assertion in case the subject change fails but previously maps the subject from
         * [T] to [R1] as the failure handler only deals with [R1] subjects.
         */
        fun <R1> withFailureHandlerAdapter(
            failureHandler: SubjectChanger.FailureHandler<R1, R>,
            map: (T) -> R1
        ): FinalStep<T, R> = withFailureHandler(FailureHandlerAdapter(failureHandler, map))

        /**
         * Uses the default [SubjectChanger.FailureHandler] which builds the failing assertion based on the specified
         * [TransformationStep.description] and [TransformationStep.representation] and includes the assertions
         * a given assertionCreator lambda would create.
         */
        fun withDefaultFailureHandler(): FinalStep<T, R>

        /**
         * Skips this step by using [withDefaultFailureHandler] and calls [FinalStep.build].
         * @return
         */
        fun build(): ChangedSubjectPostStep<T, R> = withDefaultFailureHandler().build()

        companion object {
            /**
             * Creates a [FailureHandlerOption] in the context of the [SubjectChangerBuilder].
             */
            fun <T, R> create(
                transformationStep: TransformationStep<T>,
                transformation: (T) -> Option<R>
            ): FailureHandlerOption<T, R> = FailureHandlerOptionImpl(transformationStep, transformation)
        }
    }

    /**
     * Final step in the change-subject-process, creates a [ChangedSubjectPostStep]
     * ased on the previously specified options.
     *
     * @param T the type of the current subject.
     * @param R the type of the new subject.
     */
    interface FinalStep<T, R> {
        /**
         * The so far chosen options up to the [TransformationStep] step.
         */
        val transformationStep: TransformationStep<T>

        /**
         * The previously specified new subject.
         */
        val transformation: (T) -> Option<R>

        /**
         * The previously specified [SubjectChanger.FailureHandler].
         */
        val failureHandler: SubjectChanger.FailureHandler<T, R>

        /**
         * Finishes the `reported subject change`-process by building a new [Expect] taking the previously chosen
         * options into account.
         *
         * @return A [ChangedSubjectPostStep] which allows to define what should happen with the new [Expect].
         */
        fun build(): ChangedSubjectPostStep<T, R>

        companion object {
            /**
             * Creates the [FinalStep] in the context of the [SubjectChangerBuilder].
             */
            fun <T, R> create(
                transformationStep: TransformationStep<T>,
                transformation: (T) -> Option<R>,
                failureHandler: SubjectChanger.FailureHandler<T, R>
            ): FinalStep<T, R> = FinalStepImpl(transformationStep, transformation, failureHandler)
        }
    }
}
