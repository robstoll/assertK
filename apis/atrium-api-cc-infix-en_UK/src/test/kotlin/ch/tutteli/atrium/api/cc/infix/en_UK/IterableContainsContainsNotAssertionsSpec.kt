package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import kotlin.reflect.KFunction2

class IterableContainsContainsNotAssertionsSpec : ch.tutteli.atrium.spec.integration.IterableContainsContainsNotAssertionSpec(
    AssertionVerbFactory,
    getContainsPair(),
    getContainsNotPair()
) {
    companion object {
        private val containsFun: KFunction2<Assert<Iterable<Double>>, Values<Double>, Assert<Iterable<Double>>> = Assert<Iterable<Double>>::contains
        fun getContainsPair() = containsFun.name to Companion::contains

        private fun contains(plant: Assert<Iterable<Double>>, a: Double, aX: Array<out Double>): Assert<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant contains a
            } else {
                plant contains Objects(a, *aX)
            }
        }

        private val containsNotFun: KFunction2<Assert<Iterable<Double>>, Values<Double>, Assert<Iterable<Double>>> = Assert<Iterable<Double>>::containsNot
        fun getContainsNotPair() = containsNotFun.name to Companion::containsNot

        private fun containsNot(plant: Assert<Iterable<Double>>, a: Double, aX: Array<out Double>): Assert<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant containsNot a
            } else {
                plant containsNot Objects(a, *aX)
            }
        }
    }
}
