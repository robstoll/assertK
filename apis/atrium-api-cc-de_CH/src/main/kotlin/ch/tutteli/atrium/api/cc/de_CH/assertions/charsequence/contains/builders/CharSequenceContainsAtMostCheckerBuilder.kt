package ch.tutteli.atrium.api.cc.de_CH.assertions.charsequence.contains.builders

import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains

/**
 * Represents the *deprecated* builder of a `contains at least once but at most` check within the fluent API of a
 * sophisticated `contains` assertion for [CharSequence].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 *
 * @constructor Represents the builder of a `contains at least once but at most` check within the fluent API of a
 *   sophisticated `contains` assertion for [CharSequence].
 * @param times The number which the check will compare against the actual number of times an expected object is
 *   found in the input of the search.
 * @param containsBuilder The previously used [CharSequenceContains.Builder].
 */
@Deprecated(
    "use the builder from the package creating, will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.api.cc.de_CH.creating.charsequence.contains.builders.CharSequenceContainsAtMostCheckerBuilder")
)
open class CharSequenceContainsAtMostCheckerBuilder<out T : CharSequence, out S : CharSequenceContains.SearchBehaviour>(
    times: Int,
    containsBuilder: CharSequenceContains.Builder<T, S>
) : ch.tutteli.atrium.api.cc.de_CH.creating.charsequence.contains.builders.CharSequenceContainsAtMostCheckerBuilder<T, S>(
    times, containsBuilder
)
