// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertionimport ch.tutteli.atrium.core.ExperimentalNewExpectTypesimport ch.tutteli.atrium.creating.AssertionContainerimport ch.tutteli.atrium.logic.impl.DefaultChronoLocalDateTimeAssertionsimport java.time.chrono.ChronoLocalDateimport java.time.chrono.ChronoLocalDateTime

fun <T : ChronoLocalDateTime<out ChronoLocalDate>> AssertionContainer<T>.isBefore(expected: ChronoLocalDateTime<*>): Assertion = impl.isBefore(this, expected)

fun <T : ChronoLocalDateTime<out ChronoLocalDate>> AssertionContainer<T>.isBeforeOrEqual(expected: ChronoLocalDateTime<*>): Assertion = impl.isBeforeOrEqual(this, expected)

fun <T : ChronoLocalDateTime<out ChronoLocalDate>> AssertionContainer<T>.isAfter(expected: ChronoLocalDateTime<*>): Assertion = impl.isAfter(this, expected)

fun <T : ChronoLocalDateTime<out ChronoLocalDate>> AssertionContainer<T>.isAfterOrEqual(expected: ChronoLocalDateTime<*>): Assertion = impl.isAfterOrEqual(this, expected)

fun <T : ChronoLocalDateTime<out ChronoLocalDate>> AssertionContainer<T>.isEqual(expected: ChronoLocalDateTime<*>): Assertion = impl.isEqual(this, expected)

fun <T : ChronoLocalDateTime<out ChronoLocalDate>> AssertionContainer<T>.isBefore(expected: String): Assertion = impl.isBefore(this, expected)

fun <T : ChronoLocalDateTime<out ChronoLocalDate>> AssertionContainer<T>.isBeforeOrEqual(expected: String): Assertion = impl.isBeforeOrEqual(this, expected)

fun <T : ChronoLocalDateTime<out ChronoLocalDate>> AssertionContainer<T>.isAfter(expected: String): Assertion = impl.isAfter(this, expected)

fun <T : ChronoLocalDateTime<out ChronoLocalDate>> AssertionContainer<T>.isAfterOrEqual(expected: String): Assertion = impl.isAfterOrEqual(this, expected)

fun <T : ChronoLocalDateTime<out ChronoLocalDate>> AssertionContainer<T>.isEqual(expected: String): Assertion = impl.isEqual(this, expected)


@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: ChronoLocalDateTimeAssertions
    get() = getImpl(ChronoLocalDateTimeAssertions::class) { DefaultChronoLocalDateTimeAssertions() }
