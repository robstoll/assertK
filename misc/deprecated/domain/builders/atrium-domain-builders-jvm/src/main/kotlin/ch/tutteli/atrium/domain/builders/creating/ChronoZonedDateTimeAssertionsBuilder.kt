//TODO remove file with 1.0.0
@file:Suppress(
    "DEPRECATION",
    "OVERRIDE_BY_INLINE",
    "NOTHING_TO_INLINE",
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */
)

package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.ChronoZonedDateTimeAssertions
import ch.tutteli.atrium.domain.creating.chronoZonedDateTimeAssertions
import java.time.chrono.ChronoLocalDate
import java.time.chrono.ChronoZonedDateTime

/**
 * Delegates inter alia to the implementation of [ChronoZonedDateTimeAssertionsBuilder].
 * In detail, it implements [ChronoZonedDateTimeAssertions] by delegating to [chronoZonedDateTimeAssertions]
 * which in turn delegates to the implementation via [loadSingleService].
 */
object ChronoZonedDateTimeAssertionsBuilder : ChronoZonedDateTimeAssertions {

    override inline fun <T : ChronoZonedDateTime<out ChronoLocalDate>> isBefore(
        expect: Expect<T>,
        expected: ChronoZonedDateTime<*>
    ) = chronoZonedDateTimeAssertions.isBefore(expect, expected)

    override inline fun <T : ChronoZonedDateTime<out ChronoLocalDate>> isBeforeOrEqual(
        expect: Expect<T>,
        expected: ChronoZonedDateTime<*>
    ) = chronoZonedDateTimeAssertions.isBeforeOrEqual(expect, expected)

    override inline fun <T : ChronoZonedDateTime<out ChronoLocalDate>> isAfter(
        expect: Expect<T>,
        expected: ChronoZonedDateTime<*>
    ) = chronoZonedDateTimeAssertions.isAfter(expect, expected)

    override inline fun <T : ChronoZonedDateTime<out ChronoLocalDate>> isAfterOrEqual(
        expect: Expect<T>,
        expected: ChronoZonedDateTime<*>
    ) = chronoZonedDateTimeAssertions.isAfterOrEqual(expect, expected)

    override inline fun <T : ChronoZonedDateTime<out ChronoLocalDate>> isEqual(
        expect: Expect<T>,
        expected: ChronoZonedDateTime<*>
    ) = chronoZonedDateTimeAssertions.isEqual(expect, expected)
}
