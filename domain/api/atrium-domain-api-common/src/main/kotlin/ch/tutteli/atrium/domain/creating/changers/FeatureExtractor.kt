@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.domain.creating.changers

import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.FeatureExpect
import ch.tutteli.atrium.domain.creating.NewFeatureAssertions
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * The access point to an implementation of [FeatureExtractor].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
val featureExtractor by lazy { loadSingleService(FeatureExtractor::class) }

/**
 * Defines the contract for sophisticated `safe feature extractions` including assertion creation for the feature.
 *
 * It is similar to [NewFeatureAssertions] but differs in the intended usage.
 * [NewFeatureAssertions] are intended to make assertions about a return value of a method call or a property,
 * assuming that the call as such always succeeds (no exception is thrown).
 * The [FeatureExtractor] on the other hand should be used if it is already known,
 * that the call/access fails depending on given arguments.
 * For instance, [List.get] is a good example where it fails if the given index is out of bounds.
 */
interface FeatureExtractor {

    /**
     * Extracts a feature according to the given [featureExtraction], creates an [Expect] for the
     * new subject and applies [maybeSubAssertions] in case they are specified.
     *
     *
     * @param originalAssertionContainer the assertion container with the current subject (before the change) --
     *   if you use `ExpectImpl.changeSubject.reported(...)` within an assertion function (an extension function of
     *   [Expect]) then you usually pass `this` (so the instance of [Expect]) for this parameter.
     * @param description Describes the feature
     * @param representationForFailure Representation in case the extraction cannot be carried out.
     * @param featureExtraction Extracts the feature where it returns the feature wrapped into a [Some] if the
     *   extraction as such can be carried out, otherwise [None].
     * @param maybeSubAssertions Optionally, subsequent assertions for the feature (the new subject).
     *   This is especially useful if the extraction cannot be carried out, because this way we can then already
     *   show to you (in error reporting) what you wanted to assert about the feature (which gives you more context to
     *   the error).
     * @param representationInsteadOfFeature Per default the feature as such is used to represent itself. However,
     *   if you want a different representation, then use this parameter where passing `null` still means use the
     *   feature.
     *
     * @return The newly created [Expect] for the extracted feature.
     */
    fun <T, R> extract(
        originalAssertionContainer: Expect<T>,
        description: Translatable,
        representationForFailure: Any,
        featureExtraction: (T) -> Option<R>,
        maybeSubAssertions: Option<Expect<R>.() -> Unit>,
        representationInsteadOfFeature: ((R) -> Any)? = null
    ): FeatureExpect<T, R>
}
