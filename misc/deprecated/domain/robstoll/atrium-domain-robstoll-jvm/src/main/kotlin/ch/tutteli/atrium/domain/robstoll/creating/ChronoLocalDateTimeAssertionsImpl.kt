//TODO remove file with 1.0.0
@file:Suppress(
    "DEPRECATION",
    /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.ChronoLocalDateTimeAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating.*
import java.time.chrono.ChronoLocalDate
import java.time.chrono.ChronoLocalDateTime

class ChronoLocalDateTimeAssertionsImpl : ChronoLocalDateTimeAssertions {

    override fun <T : ChronoLocalDateTime<out ChronoLocalDate>> isBefore(
        expect: Expect<T>,
        expected: ChronoLocalDateTime<*>
    ): Assertion = _isBefore(expect, expected)

    override fun <T : ChronoLocalDateTime<out ChronoLocalDate>> isBeforeOrEquals(
        expect: Expect<T>,
        expected: ChronoLocalDateTime<*>
    ): Assertion = _isBeforeOrEquals(expect, expected)

    override fun <T : ChronoLocalDateTime<out ChronoLocalDate>> isAfter(
        expect: Expect<T>,
        expected: ChronoLocalDateTime<*>
    ): Assertion = _isAfter(expect, expected)

    override fun <T : ChronoLocalDateTime<out ChronoLocalDate>> isAfterOrEquals(
        expect: Expect<T>,
        expected: ChronoLocalDateTime<*>
    ): Assertion = _isAfterOrEquals(expect, expected)

    override fun <T : ChronoLocalDateTime<out ChronoLocalDate>> isEqual(
        expect: Expect<T>,
        expected: ChronoLocalDateTime<*>
    ): Assertion = _isEqual(expect, expected)
}
