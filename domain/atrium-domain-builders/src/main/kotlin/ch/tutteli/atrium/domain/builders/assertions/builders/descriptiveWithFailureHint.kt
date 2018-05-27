package ch.tutteli.atrium.domain.builders.assertions.builders

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.assertions.builders.AssertionBuilderFinalStep
import ch.tutteli.atrium.assertions.builders.DescriptiveAssertionFinalStep
import ch.tutteli.atrium.assertions.builders.DescriptiveLikeAssertionDescriptionOption
import ch.tutteli.atrium.domain.builders.assertions.builders.impl.DescriptiveAssertionWithFailureHintFinalStepImpl
import ch.tutteli.atrium.domain.builders.assertions.builders.impl.DescriptiveAssertionWithFailureHintShowOptionImpl
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Option to create a [DescriptiveAssertion] like assertion with an additional hint which might be shown if the
 * [DescriptiveLikeAssertionDescriptionOption.test] fails.
 */
fun DescriptiveLikeAssertionDescriptionOption<DescriptiveAssertionFinalStep>.withFailureHint(
    failureHintFactory: () -> Assertion
): DescriptiveAssertionWithFailureHintShowOption =
    DescriptiveAssertionWithFailureHintShowOption.create(test, failureHintFactory)

/**
 * Option step which allows to specify in which situations the failure hint should be shown.
 */
interface DescriptiveAssertionWithFailureHintShowOption {
    /**
     * Defines that the failure hint shall be shown in any case.
     */
    val showForAnyFailure: DescriptiveLikeAssertionDescriptionOption<DescriptiveAssertionWithFailureHintFinalStep>

    /**
     * Defines that the failure hint shall only be shown if the given [predicate] holds.
     */
    fun showOnlyIf(predicate: () -> Boolean): DescriptiveLikeAssertionDescriptionOption<DescriptiveAssertionWithFailureHintFinalStep>

    companion object {
        fun create(
            test: () -> Boolean,
            failureHintFactory: () -> Assertion
        ): DescriptiveAssertionWithFailureHintShowOption
            = DescriptiveAssertionWithFailureHintShowOptionImpl(test, failureHintFactory)
    }
}

/**
 * Final step which creates a [DescriptiveAssertion] if the [test] holds or an [AssertionGroup] which includes
 * additionally a failure hint created by the given [failureHintFactory] in case [showHint] evaluates to `true`.
 */
interface DescriptiveAssertionWithFailureHintFinalStep : AssertionBuilderFinalStep<Assertion> {
    /**
     * The previously defined test which is used to determine [DescriptiveAssertion.holds].
     */
    val test: () -> Boolean

    /**
     *  The previously defined [showHint] predicate which defines whether the failure hint shall be shown
     *  in case the assertion fails or not.
     */
    val showHint: () -> Boolean

    /**
     * The previously defined factory method which creates the failure hint.
     */
    val failureHintFactory: () -> Assertion

    /**
     * The previously defined [DescriptiveAssertion.description].
     */
    val description: Translatable

    /**
     * The previously defined [DescriptiveAssertion.representation].
     */
    val representation: Any

    companion object {
        fun create(
            test: () -> Boolean,
            showHint: () -> Boolean,
            failureHintFactory: () -> Assertion,
            description: Translatable,
            representation: Any
        ): DescriptiveAssertionWithFailureHintFinalStep = DescriptiveAssertionWithFailureHintFinalStepImpl(
            test,
            showHint,
            failureHintFactory,
            description,
            representation
        )
    }
}
