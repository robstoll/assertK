package ch.tutteli.atrium.assertions.charsequence.contains.builders

import ch.tutteli.atrium.assertions.charsequence.contains.ICharSequenceContains.IChecker
import ch.tutteli.atrium.assertions.charsequence.contains.ICharSequenceContains.ISearchBehaviour
import ch.tutteli.atrium.assertions.charsequence.contains.checkers.CharSequenceContainsAtLeastChecker

/**
 * The base class for builders which create a `contains at least` check within the fluent API of a sophisticated
 * `contains` assertion for [CharSequence].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 *
 * @property times The number which the check will compare against the actual number of times an expected object
 *                 is found in the input of the search.
 *
 * @constructor The base class for builders which create a `contains at least` check within the fluent API of a
 *              sophisticated `contains` assertion for [CharSequence].
 * @param times The number which the check will compare against the actual number of times an expected object is
 *              found in the input of the search.
 * @param containsBuilder The previously used [CharSequenceContainsBuilder].
 * @param nameContainsNotFun The name of the function which represents a `CharSequence contains not` assertion.
 * @param atLeastCall The name of the function which was called and created this builder.
 */
abstract class CharSequenceContainsAtLeastCheckerBuilderBase<T : CharSequence, S : ISearchBehaviour>(
    val times: Int,
    containsBuilder: CharSequenceContainsBuilder<T, S>,
    nameContainsNotFun: String,
    atLeastCall: (Int) ->String
) : CharSequenceContainsCheckerBuilder<T, S>(containsBuilder) {

    override val checkers: List<IChecker> =
        listOf(CharSequenceContainsAtLeastChecker(times, nameContainsNotFun, atLeastCall))
}
