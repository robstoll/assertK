@file:Suppress(
    "FINAL_UPPER_BOUND" /* remove once https://youtrack.jetbrains.com/issue/KT-34257 is fixed */,
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */
)

package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.*
import java.time.DayOfWeek
import java.time.ZonedDateTime

/**
 * Creates an [Expect] for the property [ZonedDateTime.year][ZonedDateTime.getYear] of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @since 0.12.0
 */
val Expect<ZonedDateTime>.year: Expect<Int>
    get() = _logic.year().getExpectOfFeature()

/**
 * Expects that the property [ZonedDateTime.year][ZonedDateTime.getYear] of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of the assertion.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.12.0
 */
infix fun Expect<ZonedDateTime>.year(assertionCreator: Expect<Int>.() -> Unit): Expect<ZonedDateTime> =
    _logic.year().addToInitial(assertionCreator)

/**
 * Creates an [Expect] for the property [ZonedDateTime.monthValue][ZonedDateTime.getMonthValue]
 * of the subject of the assertion, so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @since 0.12.0
 */
val Expect<ZonedDateTime>.month: Expect<Int>
    get() = _logic.month().getExpectOfFeature()

/**
 * Expects that the property [ZonedDateTime.monthValue][ZonedDateTime.getMonthValue] of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of the assertion.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.12.0
 */
infix fun Expect<ZonedDateTime>.month(assertionCreator: Expect<Int>.() -> Unit): Expect<ZonedDateTime> =
    _logic.month().addToInitial(assertionCreator)

/**
 * Creates an [Expect] for the property [ZonedDatetime.dayOfWeek][ZonedDateTime.getDayOfWeek]
 * of the subject of the assertion, so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @since 0.12.0
 */
val Expect<ZonedDateTime>.dayOfWeek: Expect<DayOfWeek>
    get() = _logic.dayOfWeek().getExpectOfFeature()

/**
 * Expects that the property [ZonedDatetime.dayOfWeek][ZonedDateTime.getDayOfWeek] of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of the assertion.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.12.0
 */
infix fun Expect<ZonedDateTime>.dayOfWeek(assertionCreator: Expect<DayOfWeek>.() -> Unit): Expect<ZonedDateTime> =
    _logic.dayOfWeek().addToInitial(assertionCreator)

/**
 * Creates an [Expect] for the property [ZonedDateTime.dayOfMonth][ZonedDateTime.getDayOfMonth]
 * of the subject of the assertion, so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @since 0.12.0
 */
val Expect<ZonedDateTime>.day: Expect<Int>
    get() = _logic.day().getExpectOfFeature()

/**
 * Expects that the property [ZonedDateTime.dayOfMonth][ZonedDateTime.getDayOfMonth] of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of the assertion.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.12.0
 */
infix fun Expect<ZonedDateTime>.day(assertionCreator: Expect<Int>.() -> Unit): Expect<ZonedDateTime> =
    _logic.day().addToInitial(assertionCreator)
