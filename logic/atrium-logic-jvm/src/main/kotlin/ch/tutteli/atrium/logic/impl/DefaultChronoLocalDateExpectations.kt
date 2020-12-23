@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.*
import ch.tutteli.atrium.translations.DescriptionDateTimeLikeAssertion.*
import java.time.LocalDate
import java.time.chrono.ChronoLocalDate

class DefaultChronoLocalDateExpectations : ChronoLocalDateExpectations {

    override fun <T : ChronoLocalDate> isBefore(
        container: AssertionContainer<T>,
        expected: ChronoLocalDate
    ): Assertion = container.createDescriptiveAssertion(IS_BEFORE, expected) {
        it.isBefore(expected)
    }

    override fun <T : ChronoLocalDate> isBeforeOrEqual(
        container: AssertionContainer<T>,
        expected: ChronoLocalDate
    ): Assertion = container.createDescriptiveAssertion(IS_BEFORE_OR_EQUAL, expected) {
        it.isBefore(expected) || it.isEqual(expected)
    }

    override fun <T : ChronoLocalDate> isAfter(
        container: AssertionContainer<T>,
        expected: ChronoLocalDate
    ): Assertion = container.createDescriptiveAssertion(IS_AFTER, expected) {
        it.isAfter(expected)
    }

    override fun <T : ChronoLocalDate> isAfterOrEqual(
        container: AssertionContainer<T>,
        expected: ChronoLocalDate
    ): Assertion = container.createDescriptiveAssertion(IS_AFTER_OR_EQUAL, expected) {
        it.isAfter(expected) || it.isEqual(expected)
    }

    override fun <T : ChronoLocalDate> isEqual(
        container: AssertionContainer<T>,
        expected: ChronoLocalDate
    ): Assertion = container.createDescriptiveAssertion(SAME_DAY, expected) { it.isEqual(expected) }

    override fun <T : ChronoLocalDate> isBefore(
        container: AssertionContainer<T>,
        expected: String
    ): Assertion = container.isBefore(LocalDate.parse(expected))

    override fun <T : ChronoLocalDate> isBeforeOrEqual(
        container: AssertionContainer<T>,
        expected: String
    ): Assertion = container.isBeforeOrEqual(LocalDate.parse(expected))

    override fun <T : ChronoLocalDate> isAfter(
        container: AssertionContainer<T>,
        expected: String
    ): Assertion = container.isAfter(LocalDate.parse(expected))

    override fun <T : ChronoLocalDate> isAfterOrEqual(
        container: AssertionContainer<T>,
        expected: String
    ): Assertion = container.isAfterOrEqual(LocalDate.parse(expected))

    override fun <T : ChronoLocalDate> isEqual(
        container: AssertionContainer<T>,
        expected: String
    ): Assertion = container.isEqual(LocalDate.parse(expected))
}
