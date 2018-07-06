
package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.contains
import ch.tutteli.atrium.api.cc.en_GB.containsNot
import ch.tutteli.atrium.api.cc.en_GB.message
import ch.tutteli.atrium.api.cc.en_GB.toThrow
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.translations.DescriptionFloatingPointAssertion
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.include
import java.math.BigDecimal
import java.text.DecimalFormat
import kotlin.math.absoluteValue

abstract class FloatingPointWithErrorToleranceAssertionsSpec(
    verbs: AssertionVerbFactory,
    toBeWithErrorToleranceFloatPair: Pair<String, Assert<Float>.(Float, Float) -> Assert<Float>>,
    toBeWithErrorToleranceDoublePair: Pair<String, Assert<Double>.(Double, Double) -> Assert<Double>>,
    toBeWithErrorToleranceBigDecimalPair: Pair<String, Assert<BigDecimal>.(BigDecimal, BigDecimal) -> Assert<BigDecimal>>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessAssertionSpec<Float>("$describePrefix[Float] ",
        toBeWithErrorToleranceFloatPair.first to mapToCreateAssertion { toBeWithErrorToleranceFloatPair.second(this, 1.0f, 0.01f) }) {})
    include(object : SubjectLessAssertionSpec<Double>("$describePrefix[Double] ",
        toBeWithErrorToleranceDoublePair.first to mapToCreateAssertion { toBeWithErrorToleranceDoublePair.second(this, 1.0, 0.01) }) {})
    include(object : SubjectLessAssertionSpec<BigDecimal>("$describePrefix[BigDecimal] ",
        toBeWithErrorToleranceBigDecimalPair.first to mapToCreateAssertion { toBeWithErrorToleranceBigDecimalPair.second(this, BigDecimal.TEN, BigDecimal("0.00001")) }
    ) {})

    include(object : CheckingAssertionSpec<Float>(verbs, "$describePrefix[Float] ",
        checkingTriple(toBeWithErrorToleranceFloatPair.first, { toBeWithErrorToleranceFloatPair.second(this, 1.0f, 0.01f) }, 0.99f, 0.98f)) {})
    include(object : CheckingAssertionSpec<Double>(verbs, "$describePrefix[Double] ",
        checkingTriple(toBeWithErrorToleranceDoublePair.first, { toBeWithErrorToleranceDoublePair.second(this, 1.0, 0.5) }, 1.5, 1.6)) {})
    include(object : CheckingAssertionSpec<BigDecimal>(verbs, "$describePrefix[BigDecimal] ",
        checkingTriple(toBeWithErrorToleranceBigDecimalPair.first, { toBeWithErrorToleranceBigDecimalPair.second(this, BigDecimal.TEN, BigDecimal("0.00001")) }, BigDecimal("9.99999999"), BigDecimal("9.999"))
    ) {})

    val expect = verbs::checkException

    data class TestData<out T : Any>(val subject: T, val tolerance: T, val holding: List<T>, val failing: List<T>)

    val df = DecimalFormat("###,##0.0")
    df.maximumFractionDigits = 340

    fun <T : Any> SpecBody.describeFun(pair: Pair<String, Assert<T>.(T, T) -> Assert<T>>, withFailureNotice: Boolean, absDiff: (T, T) -> T, testData: List<TestData<T>>) {
        val (name, toBeWithErrorTolerance) = pair
        group("$describePrefix $name") {
            testData.forEach { (subject, tolerance, holding, failing) ->
                context("tolerance is $tolerance and subject is $subject ...") {
                    test("... compare to $subject does not throw") {
                        verbs.checkImmediately(subject).toBeWithErrorTolerance(subject, tolerance)
                    }

                    holding.forEach { num ->
                        test("... compare to $num does not throw") {
                            verbs.checkImmediately(subject).toBeWithErrorTolerance(num, tolerance)
                        }
                    }

                    val toBeInclErrorTolerance = String.format(DescriptionFloatingPointAssertion.TO_BE_WITH_ERROR_TOLERANCE.getDefault(), tolerance)
                    val failureNotice = String.format(DescriptionFloatingPointAssertion.FAILURE_DUE_TO_FLOATING_POINT_NUMBER.getDefault(), subject::class.fullName)
                    failing.forEach { num ->
                        test("... compare to $num throws AssertionError") {
                            expect {
                                verbs.checkImmediately(subject).toBeWithErrorTolerance(num, tolerance)
                            }.toThrow<AssertionError> {
                                message {
                                    val exactCheck = String.format(DescriptionFloatingPointAssertion.TO_BE_WITH_ERROR_TOLERANCE_EXPLAINED.getDefault(), df.format(subject), df.format(num), df.format(absDiff(subject, num)), df.format(tolerance))
                                    contains(subject,
                                        "$toBeInclErrorTolerance: $num",
                                        exactCheck)
                                    if (withFailureNotice) {
                                        contains(failureNotice)
                                    } else {
                                        containsNot(failureNotice)
                                    }
                                }
                            }

                        }
                    }
                }
            }
        }
    }

    describeFun(toBeWithErrorToleranceFloatPair, true, { a, b -> (a - b).absoluteValue }, listOf(
        TestData(0.001f, 0.001f, listOf(0.002f, 0.0f, -0.0f, 0.00001f), listOf(0.0021f, -0.0000001f)),
        TestData(9.999f, 0.001f, listOf(9.998f, 9.9989f, 9.9988f), listOf(1.1f, 1.001f, 1.001f, 9.997f, /* due to precision */ 10.0f)),
        //should give out scientific notation
        TestData(0.000_000_01f, 0.000_000_002f, listOf(0.000_000_011f, 0.000_000_009f), listOf(0.000_000_013f, 0.000_000_007f, /* due to precision */ 0.000_000_012f, 0.000_000_008f))
    ))
    describeFun(toBeWithErrorToleranceDoublePair, true, { a, b -> (a - b).absoluteValue }, listOf(
        TestData(1.0, 0.01, listOf(1.009), listOf(0.98, 1.02, 1.011, /* due to precision */ 1.01, 0.99)),
        TestData(0.001, 0.001, listOf(0.002, 0.0, -0.0, 0.00001), listOf(0.0021, -0.0000001)),
        TestData(9.99999, 0.00001, listOf(10.0, 9.99998), listOf(1.1, 1.001, 1.001, 9.99997)),
        //should give out scientific notation
        TestData(0.000_000_01, 0.000_000_002, listOf(0.000_000_011, 0.000_000_012, 0.000_000_009, 0.000_000_008), listOf(0.000_000_013, 0.000_000_007))
    ))
    describeFun(toBeWithErrorToleranceBigDecimalPair, false, { a, b -> (a - b).abs() }, listOf(
        TestData(BigDecimal("9.99999999999999"), BigDecimal("0.00000000000001"),
            listOf(BigDecimal.TEN, BigDecimal("9.999999999999999999999999"), BigDecimal("9.99999999999998")),
            listOf(BigDecimal("10.0000000000000000001"), BigDecimal("9.99999999999997"), BigDecimal("9.9999999999999799999999999999999999"))),
        TestData(BigDecimal("10.0"), BigDecimal("0.001"),
            listOf(BigDecimal.TEN, BigDecimal("10"), BigDecimal("10.000"), BigDecimal("10.001"), BigDecimal("10.0000000000000000001")),
            listOf(BigDecimal("10.001000000001"), BigDecimal("9.99899999")))
    ))
})

