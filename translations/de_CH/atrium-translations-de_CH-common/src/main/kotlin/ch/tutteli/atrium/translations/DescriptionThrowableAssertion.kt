package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Throwable].
 */
enum class DescriptionThrowableAssertion(override val value: String) : StringBasedTranslatable {
    IS_A("ist eine"),
    NO_EXCEPTION_OCCURRED("keine Exception wurde geworfen"),
    OCCURRED_EXCEPTION_PROPERTIES("Eigenschaften der unerwarteten %s"),
    OCCURRED_EXCEPTION_MESSAGE("message"),
    OCCURRED_EXCEPTION_STACKTRACE("stacktrace"),
}
