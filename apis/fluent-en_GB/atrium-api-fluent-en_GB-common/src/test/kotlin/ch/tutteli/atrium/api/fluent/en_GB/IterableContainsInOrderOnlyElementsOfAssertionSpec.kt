package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import org.spekframework.spek2.Spek

class IterableContainsInOrderOnlyElementsOfAssertionSpec : Spek({
    include(BuilderSpec)
}) {
    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableContainsInOrderOnlyValuesAssertionsSpec(
        getContainsPair(),
        getContainsNullablePair(),
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "▶ ", "◾ ",
        "[Atrium][Builder] "
    )

    companion object : IterableContainsSpecBase() {
        fun getContainsPair() = "$contains.$inOrder.$inOrderElementsOf" to Companion::containsInOrderOnlyValues

        private fun containsInOrderOnlyValues(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> = expect.contains.inOrder.only.elementsOf(listOf(a, *aX))

        fun getContainsNullablePair() =
            "$contains.$inOrder.$inOrderElementsOf" to Companion::containsInOrderOnlyNullableValues

        private fun containsInOrderOnlyNullableValues(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> = expect.contains.inOrder.only.elementsOf(listOf(a, *aX))
    }
}

