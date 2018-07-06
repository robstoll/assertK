@file:JvmMultifileClass
@file:JvmName("floatingPointAssertionsKt")

package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionPlant
import java.math.BigDecimal

fun <T : BigDecimal> _toBeWithErrorTolerance(plant: AssertionPlant<T>, expected: T, tolerance: T): Assertion {
    val absDiff = { (plant.subject - expected).abs() }
    return toBeWithErrorTolerance(expected, tolerance, absDiff) {
        listOf(
            createToBeWithErrorToleranceExplained(plant, expected, absDiff, tolerance)
        )
    }
}
