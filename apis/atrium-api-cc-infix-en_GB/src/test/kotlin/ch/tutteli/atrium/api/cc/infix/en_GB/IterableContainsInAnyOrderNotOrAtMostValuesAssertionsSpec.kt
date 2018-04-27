package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.contain
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.order
import ch.tutteli.atrium.creating.Assert

class IterableContainsInAnyOrderNotOrAtMostValuesAssertionsSpec : ch.tutteli.atrium.spec.integration.IterableContainsInAnyOrderNotOrAtMostValuesAssertionSpec(
    AssertionVerbFactory,
    getNotOrAtMostTriple(),
    getContainsNotPair()
) {

    companion object : IterableContainsSpecBase() {

        private fun getNotOrAtMostTriple() = Triple(
            "$toContain $notOrAtMost",
            { what: String, times: String -> "$toContain $what $notOrAtMost $times" },
            Companion::containsNotOrAtMost
        )

        private fun containsNotOrAtMost(plant: Assert<Iterable<Double>>, atMost: Int, a: Double, aX: Array<out Double>)
            = plant to contain inAny order notOrAtMost atMost the Values(a, *aX)

        private fun getContainsNotPair() = containsNotValues to Companion::getErrorMsgContainsNot

        private fun getErrorMsgContainsNot(times: Int)
            = "use $containsNotValues instead of `$notOrAtMost $times`"

    }
}
