@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.case
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.contain
import ch.tutteli.atrium.creating.Assert

//TODO remove with 1.0.0, no need to migrate to Spek 2
class CharSequenceContainsAtLeastAssertionsSpec : ch.tutteli.atrium.spec.integration.CharSequenceContainsAtLeastAssertionsSpec(
    AssertionVerbFactory,
    getAtLeastTriple(),
    getAtLeastIgnoringCaseTriple(),
    getAtLeastButAtMostTriple(),
    getAtLeastBustAtMostIgnoringCaseTriple(),
    getContainsNotPair(),
    getExactlyPair(),
    Companion::getErrorMsgAtLeastButAtMost,
    "◆ ", "⚬ "
) {

    companion object : CharSequenceContainsSpecBase() {

        internal fun getAtLeastTriple() = Triple(
            "$toContain $atLeast",
            { what: String, times: String -> "$toContain $what $atLeast $times" },
            Companion::containsAtLeast
        )

        private fun containsAtLeast(plant: Assert<CharSequence>, atLeast: Int, a: Any, aX: Array<out Any>): Assert<CharSequence> {
            return if (aX.isEmpty()) {
                plant to contain atLeast atLeast value a
            } else {
                plant to contain atLeast atLeast the Values(a, *aX)
            }
        }

        private fun getAtLeastIgnoringCaseTriple() = Triple(
            "$toContain $ignoringCase $atLeast",
            { what: String, times: String -> "$toContain $ignoringCase $what $atLeast $times" },
            Companion::containsAtLeastIgnoringCase
        )

        private fun containsAtLeastIgnoringCase(plant: Assert<CharSequence>, atLeast: Int, a: Any, aX: Array<out Any>): Assert<CharSequence> {
            return if (aX.isEmpty()) {
                if(atLeast == 1) plant to contain ignoring case value a
                else plant to contain ignoring case atLeast atLeast value a
            } else {
                if(atLeast == 1) plant to contain ignoring case the Values(a, *aX)
                else plant to contain ignoring case atLeast atLeast the Values(a, *aX)
            }
        }

        private fun getAtLeastButAtMostTriple() = Triple(
            "$toContain $atLeast $butAtMost",
            { what: String, timesAtLeast: String, timesAtMost: String -> "$toContain $what $atLeast $timesAtLeast $butAtMost $timesAtMost" },
            Companion::containsAtLeastButAtMost
        )

        private fun containsAtLeastButAtMost(plant: Assert<CharSequence>, atLeast: Int, butAtMost: Int, a: Any, aX: Array<out Any>)
            = plant to contain atLeast atLeast butAtMost butAtMost the Values(a, *aX)

        private fun getAtLeastBustAtMostIgnoringCaseTriple() = Triple(
            "$toContain $ignoringCase $atLeast $butAtMost",
            { what: String, timesAtLeast: String, timesAtMost: String -> "$toContain $ignoringCase $what $atLeast $timesAtLeast $butAtMost $timesAtMost" },
            Companion::containsAtLeastButAtMostIgnoringCase
        )

        private fun containsAtLeastButAtMostIgnoringCase(plant: Assert<CharSequence>, atLeast: Int, butAtMost: Int, a: Any, aX: Array<out Any>)
            = plant to contain ignoring case atLeast atLeast butAtMost butAtMost the Values(a, *aX)

        private fun getContainsNotPair() = containsNotValues to Companion::getErrorMsgContainsNot

        private fun getErrorMsgContainsNot(times: Int)
            = "use $containsNotValues instead of `$atLeast $times`"

        private fun getExactlyPair() = exactly to Companion::getErrorMsgExactly

        private fun getErrorMsgExactly(times: Int) = "use `$exactly $times` instead of `$atLeast $times $butAtMost $times`"

        private fun getErrorMsgAtLeastButAtMost(timesAtLeast: Int, timesButAtMost: Int)
            = "specifying `$butAtMost $timesButAtMost` does not make sense if `$atLeast $timesAtLeast` was used before"
    }
}
