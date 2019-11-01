package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.Expect

/**
 * @since 0.9.0
 *
 * The access point to an implementation of [ChronoLocalDateAssertions].
 *
 * It loads the implementation lazily via [loadSingleService].
 */

val chronoLocalDateAssertions by lazy { loadSingleService(ChronoLocalDateAssertions::class) }

/**
 * @since 0.9.0
 *
 * Defines the minimum set of assertion functions and builders applicable to [ChronoLocalDate],
 * which an implementation of the domain of Atrium has to provide.
 */
interface ChronoLocalDateAssertions {
    fun <T: ChronoLocalDate> isBeforeOrEquals(assertionContainer: Expect<T>, expected: T): Assertion
}
