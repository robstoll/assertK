package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.feature1
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.testutils.WithAsciiReporter
import ch.tutteli.atrium.specs.withNullableSuffix
import kotlin.jvm.JvmName

class ListFeatureAssertionsSpec : ch.tutteli.atrium.specs.integration.ListFeatureAssertionsSpec(
    feature1<List<Int>, Int, Int>(Expect<List<Int>>::get),
    fun2<List<Int>, Int, Expect<Int>.() -> Unit>(Companion::get),
    feature1<List<Int?>, Int, Int?>(Expect<List<Int?>>::get).withNullableSuffix(),
    fun2<List<Int?>, Int, Expect<Int?>.() -> Unit>(Companion::get).withNullableSuffix()
) {
    companion object : WithAsciiReporter() {

        private fun get(expect: Expect<List<Int>>, index: Int, assertionCreator: Expect<Int>.() -> Unit) =
            expect get index(index) { assertionCreator() }

        @JvmName("getNullable")
        private fun get(
            expect: Expect<List<Int?>>,
            index: Int,
            assertionCreator: Expect<Int?>.() -> Unit
        ) = expect get index(index) { assertionCreator() }

    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<AbstractList<Int>> = notImplemented()
        var a1b: Expect<MutableList<Int?>> = notImplemented()

        var star: Expect<List<*>> = notImplemented()

        a1 get 1
        a1 = a1 get index(1) { }

        a1b get 1
        a1b = a1b get index(1) { }

        star get 1
        star = star get index(1) { }
    }
}
