package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert

class IterableContainsInAnyOrderExactlyObjectsAssertionsSpec : ch.tutteli.atrium.spec.integration.IterableContainsInAnyOrderExactlyObjectsAssertionSpec(
    AssertionVerbFactory,
    getExactlyTriple(),
    getContainsNotPair()
) {

    companion object : IterableContainsSpecBase() {

        private fun getExactlyTriple() = Triple(
            "$contains.$inAnyOrder.$exactly",
            { what: String, times: String -> "$contains $what $exactly $times" },
            Companion::containsExactly
        )

        private fun containsExactly(plant: Assert<Iterable<Double>>, exactly: Int, a: Double, aX: Array<out Double>): Assert<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant.contains.inAnyOrder.exactly(exactly).`object`(a)
            } else {
                plant.contains.inAnyOrder.exactly(exactly).objects(a, *aX)
            }
        }

        private fun getContainsNotPair() = containsNot to Companion::getErrorMsgContainsNot

        private fun getErrorMsgContainsNot(times: Int)
            = "use $containsNot instead of $exactly($times)"

    }
}
