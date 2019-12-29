package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.feature0
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented

class IterableFeatureAssertionsSpec : ch.tutteli.atrium.specs.integration.IterableFeatureAssertionsSpec(
    feature0<Iterable<Int>, Int>(Expect<Iterable<Int>>::min, "min"),
    fun1<Iterable<Int>, Expect<Int>.() -> Unit>(Expect<Iterable<Int>>::min),
    feature0<Iterable<Int>, Int>(Expect<Iterable<Int>>::max, "max"),
    fun1<Iterable<Int>, Expect<Int>.() -> Unit>(Expect<Iterable<Int>>::max)
) {
    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<Iterable<Int>> = notImplemented()
        var a2: Expect<out Iterable<Int>> = notImplemented()
        //nullable not supported by min/max or rather T : Comparable<T> does not exist for T? (one cannot implement an interface for the nullable type)
        //same for Iterable<*>

        a1.min()
        a2.min()
        a1.max()
        a2.max()

        a1 = a1.min { }
        a2 = a2.min { }
        a1 = a1.max { }
        a2 = a2.max { }
    }
}
