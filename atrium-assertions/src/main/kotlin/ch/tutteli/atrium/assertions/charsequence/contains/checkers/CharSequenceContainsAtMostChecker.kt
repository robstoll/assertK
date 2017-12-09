package ch.tutteli.atrium.assertions.charsequence.contains.checkers

import ch.tutteli.atrium.assertions.DescriptionCharSequenceAssertion
import ch.tutteli.atrium.assertions.IAssertion

/**
 * Represents a check that an expected object is contained at most [times] in the search input.
 *
 * @param times The number which the check uses to compare against the actual number of times an expected object is
 *              found in the input of the search.
 * @param nameContainsNotFun The function which should be used instead of `atMostCall` when [times] equals to zero.
 * @param atMostCall The function which was used and should not be used if [times] equals to zero.
 *
 * @throws IllegalArgumentException In case [times] is smaller than 1.
 */
class CharSequenceContainsAtMostChecker(
    times: Int,
    nameContainsNotFun: String,
    atMostCall: (Int) -> String
) : CharSequenceContainsChecker(times, nameContainsNotFun, atMostCall) {

    override fun createAssertion(foundNumberOfTimes: Int): IAssertion
        = createBasicAssertion(DescriptionCharSequenceAssertion.AT_MOST, foundNumberOfTimes <= times)
}
