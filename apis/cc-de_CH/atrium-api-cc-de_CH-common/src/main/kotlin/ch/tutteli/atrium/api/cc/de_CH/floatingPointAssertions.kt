package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant

/**
 * Makes the assertion that [AssertionPlant.subject] is equal to [expected] with an error [tolerance]
 * (range including bounds).
 *
 * It effectively compares the absolute difference between [AssertionPlant.subject] and [expected] and compares it with
 * the [tolerance]. As long as it is less than or equal the [tolerance] the assertion holds; otherwise it fails.
 * A more mathematical way of expressing the assertion is the following inequality:
 *
 * | [AssertionPlant.subject] - [expected] | ≤ [tolerance]
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
expect fun Assert<Float>.istMitFehlerToleranz(expected: Float, tolerance: Float): AssertionPlant<Float>

/**
 * Makes the assertion that [AssertionPlant.subject] is equal to [expected] with an error [tolerance]
 * (range including bounds).
 *
 * It effectively compares the absolute difference between [AssertionPlant.subject] and [expected] and compares it with
 * the [tolerance]. As long as it is less than or equal the [tolerance] the assertion holds; otherwise it fails.
 * A more mathematical way of expressing the assertion is the following inequality:
 *
 * | [AssertionPlant.subject] - [expected] | ≤ [tolerance]
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
expect fun Assert<Double>.istMitFehlerToleranz(expected: Double, tolerance: Double): AssertionPlant<Double>
