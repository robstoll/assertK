package ch.tutteli.atrium.api.cc.infix.en_UK.assertions.charsequence.contains.builders

import ch.tutteli.atrium.api.cc.infix.en_UK.atLeast
import ch.tutteli.atrium.api.cc.infix.en_UK.atMost
import ch.tutteli.atrium.api.cc.infix.en_UK.exactly
import ch.tutteli.atrium.assertions.charsequence.contains.ICharSequenceContains.ISearchBehaviour
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsAtMostCheckerBuilderBase
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsBuilder

/**
 * Represents the builder of a `contains at least once but at most` check within the fluent API of a
 * sophisticated `contains` assertion for [CharSequence].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 *
 * @constructor Represents the builder of a `contains at least once but at most` check within the fluent API of a
 *              sophisticated `contains` assertion for [CharSequence].
 * @param times The number which the check will compare against the actual number of times an expected object is
 *              found in the input of the search.
 * @param containsBuilder The previously used [CharSequenceContainsBuilder].
 */
open class CharSequenceContainsAtMostCheckerBuilder<T : CharSequence, S : ISearchBehaviour>(
    times: Int,
    containsBuilder: CharSequenceContainsBuilder<T, S>
) : CharSequenceContainsAtMostCheckerBuilderBase<T, S>(
    times,
    containsBuilder,
    nameContainsNotValuesFun(),
    { "`${containsBuilder::atMost.name} $it`" },
    { "`${containsBuilder::atLeast.name} $it`" },
    { "`${containsBuilder::exactly.name} $it`" }
)
