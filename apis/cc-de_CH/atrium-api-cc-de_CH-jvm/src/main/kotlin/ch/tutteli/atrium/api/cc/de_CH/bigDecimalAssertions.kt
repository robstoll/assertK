@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.builders.bigDecimal
import ch.tutteli.atrium.domain.builders.creating.PleaseUseReplacementException
import java.math.BigDecimal

@Deprecated("Verwende `istNumerischGleichWie` wenn du erwartest, dass die folgende Behauptung stimmt:\n" +
    "`esGilt(BigDecimal(\"10\").ist(BigDecimal(\"10.0\"))`\n" +
    "Erwartest du hingegen, dass die Behauptung falsch ist (da `BigDecimal.scale` anders ist), dann verwende `istGleichInklusiveScale`.",
    ReplaceWith("istNumerischGleichWie(expected) oder istGleichInklusiveScale(expected)"))
@Suppress("UNUSED_PARAMETER", "unused")
fun <T : BigDecimal> Assert<T>.ist(expected: T): Nothing
    = throw PleaseUseReplacementException("BigDecimal.equals() vergleicht auch BigDecimal.scale, was dir womöglich nicht bewusst war.\n" +
    "Falls doch und du möchtest dass `scale` verglichen wird, dann verwende `istGleichInklusiveScale`.")

@Deprecated("Verwende `istNichtNumerischGleichWie` wenn du erwartest, dass die folgende Behauptung falsch ist:\n" +
    "`esGilt(BigDecimal(\"10\").istNicht(BigDecimal(\"10.0\"))`\n" +
    "Erwartest du hingegen, dass die Behauptung stimmt (da `BigDecimal.scale` anders ist), dann verwende `istNichtGleichInklusiveScale`.",
    ReplaceWith("istNichtNumerischGleichWie(expected) or istNichtGleichInklusiveScale(expected)"))
@Suppress("UNUSED_PARAMETER", "unused")
fun <T : BigDecimal> Assert<T>.istNicht(expected: T): Nothing
    = throw PleaseUseReplacementException("BigDecimal.equals() vergleicht auch BigDecimal.scale, was dir womöglich nicht bewusst war.\n" +
    "Falls doch und du möchtest dass `scale` verglichen wird, dann verwende `istNichtGleichInklusiveScale`.")

@Deprecated("Verwende `istNichtNullAber { istNumerischGleichWie(...) }` wenn du erwartest, dass die folgende Behauptung stimmt:\n" +
    "`esGilt(BigDecimal(\"10\").ist(BigDecimal(\"10.0\"))`\n" +
    "Erwartest du hingegen, dass die Behauptung falsch ist (da `BigDecimal.scale` anders ist), dann verwende `istNichtNullAber { istGleichInklusiveScale(...) }`.",
    ReplaceWith("istNichtNullAber { istNumerischGleichWie(expected) } oder istNichtNullAber { istGleichInklusiveScale(expected) }"))
@Suppress("UNUSED_PARAMETER", "unused")
infix fun <T : BigDecimal> AssertionPlantNullable<T?>.istNichtNullAber(expected: T): Nothing
    = throw PleaseUseReplacementException("BigDecimal.equals() vergleicht auch BigDecimal.scale, was dir womöglich nicht bewusst war.\n" +
    "Falls doch und du möchtest dass `scale` verglichen wird, dann verwende `istGleichInklusiveScale`.")

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] is numerically equal to [expected].
 *
 * By numerically is meant that it will not compare [BigDecimal.scale] (or in other words,
 * it uses `compareTo(expected) == 0`)
 *
 * Most of the time you want to use this function instead of [istGleichInklusiveScale] because
 * [istGleichInklusiveScale] compares [BigDecimal.scale].
 * Following the two functions compared:
 * - `esGilt(BigDecimal("10")).istGleichInklusiveScale(BigDecimal("10.0"))` does not hold
 * - `esGilt(BigDecimal("10")).istNumerischGleichWie(BigDecimal("10.0"))` holds.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("api-cc-de_CH is discontinued, switch to api-fluent-en_GB; will be removed with 1.0.0")
fun <T : BigDecimal> Assert<T>.istNumerischGleichWie(expected: T)
    = addAssertion(AssertImpl.bigDecimal.isNumericallyEqualTo(this, expected))

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] is not numerically equal to [expected].
 *
 * By numerically is meant that it will not compare [BigDecimal.scale] (or in other words,
 * it uses `compareTo(expected) != 0`)
 *
 * Most of the time you want to use this function instead of [istNichtGleichInklusiveScale] because
 * [istNichtGleichInklusiveScale] compares [BigDecimal.scale].
 * Following the two functions compared:
 * - `esGilt(BigDecimal("10")).istNichtGleichInklusiveScale(BigDecimal("10.0"))` holds
 * - `esGilt(BigDecimal("10")).istNichtNumerischGleichWie(BigDecimal("10.0"))` does not hold.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("api-cc-de_CH is discontinued, switch to api-fluent-en_GB; will be removed with 1.0.0")
fun <T : BigDecimal> Assert<T>.istNichtNumerischGleichWie(expected: T)
    = addAssertion(AssertImpl.bigDecimal.isNotNumericallyEqualTo(this, expected))


/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] is equal to [expected] including [BigDecimal.scale].
 *
 * Most of the time you want to use [istNumerischGleichWie] which does not compare [BigDecimal.scale]
 * in contrast to this function.
 * Following the two functions compared:
 * - `esGilt(BigDecimal("10")).istGleichInklusiveScale(BigDecimal("10.0"))` does not hold
 * - `esGilt(BigDecimal("10")).istNumerischGleichWie(BigDecimal("10.0"))` holds.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("api-cc-de_CH is discontinued, switch to api-fluent-en_GB; will be removed with 1.0.0")
fun <T : BigDecimal> Assert<T>.istGleichInklusiveScale(expected: T)
    = addAssertion(AssertImpl.bigDecimal.isEqualIncludingScale(this, expected, this::istNumerischGleichWie.name))

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] is not equal to [expected] including [BigDecimal.scale].
 *
 * Most of the time you want to use [istNichtNumerischGleichWie] which does not compare [BigDecimal.scale]
 * in contrast to this function.
 * Following the two functions compared:
 * - `assert(BigDecimal("10")).istNichtGleichInklusiveScale(BigDecimal("10.0"))` holds.
 * - `assert(BigDecimal("10")).istNichtNumerischGleichWie(BigDecimal("10.0"))`  does not hold.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("api-cc-de_CH is discontinued, switch to api-fluent-en_GB; will be removed with 1.0.0")
fun <T : BigDecimal> Assert<T>.istNichtGleichInklusiveScale(expected: T)
    = addAssertion(AssertImpl.bigDecimal.isNotEqualIncludingScale(this, expected))
