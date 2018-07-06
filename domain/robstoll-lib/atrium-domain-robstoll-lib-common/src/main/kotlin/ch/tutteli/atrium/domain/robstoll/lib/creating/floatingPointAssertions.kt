@file:JvmMultifileClass
@file:JvmName("floatingPointAssertionsKt")

package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.ExplanatoryAssertion
import ch.tutteli.atrium.core.polyfills.JvmMultifileClass
import ch.tutteli.atrium.core.polyfills.JvmName
import ch.tutteli.atrium.core.polyfills.formatNumber
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.builders.assertions.builders.withFailureHint
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionFloatingPointAssertion.*
import kotlin.math.absoluteValue

fun _toBeWithErrorTolerance(plant: AssertionPlant<Float>, expected: Float, tolerance: Float): Assertion
    = toBeWithErrorToleranceOfFloatOrDouble(plant, expected, tolerance) { (plant.subject - expected).absoluteValue }

fun _toBeWithErrorTolerance(plant: AssertionPlant<Double>, expected: Double, tolerance: Double): Assertion
    = toBeWithErrorToleranceOfFloatOrDouble(plant, expected, tolerance) { (plant.subject - expected).absoluteValue }

private fun <T> toBeWithErrorToleranceOfFloatOrDouble(
    plant: AssertionPlant<T>,
    expected: T,
    tolerance: T,
    absDiff: () -> T
): Assertion where T : Comparable<T>, T : Number {
    return toBeWithErrorTolerance(expected, tolerance, absDiff) {
        listOf(
            AssertImpl.builder.explanatory
                .withDescription(FAILURE_DUE_TO_FLOATING_POINT_NUMBER, plant.subject::class.fullName)
                .build(),
            createToBeWithErrorToleranceExplained(plant, expected, absDiff, tolerance)
        )
    }
}

internal fun <T> createToBeWithErrorToleranceExplained(
    plant: AssertionPlant<T>,
    expected: T,
    absDiff: () -> T,
    tolerance: T
): ExplanatoryAssertion where T : Comparable<T>, T : Number = AssertImpl.builder.explanatory
    .withDescription(
        TO_BE_WITH_ERROR_TOLERANCE_EXPLAINED,
        formatNumber(plant.subject), formatNumber(expected), formatNumber(absDiff()), formatNumber(tolerance)
    )
    .build()

internal fun <T : Comparable<T>> toBeWithErrorTolerance(
    expected: T,
    tolerance: T,
    absDiff: () -> T,
    explanatoryAssertionCreator: () -> List<Assertion>
): Assertion = AssertImpl.builder.descriptive
    .withTest { absDiff() <= tolerance }
    .withFailureHint {
        //TODO that's not nice in case we use it in an Iterable contains assertion, for instance contains...entry { toBeWithErrorTolerance(x, 0.01) }
        //we do not want to see the failure nor the exact check in the 'an entry which...' part
        //same problematic applies to feature assertions within an identification lambda
        AssertImpl.builder.explanatoryGroup
            .withDefaultType
            .withAssertions(explanatoryAssertionCreator())
            .build()
    }
    .showForAnyFailure
    .withDescriptionAndRepresentation(TranslatableWithArgs(TO_BE_WITH_ERROR_TOLERANCE, tolerance), expected)
    .build()
