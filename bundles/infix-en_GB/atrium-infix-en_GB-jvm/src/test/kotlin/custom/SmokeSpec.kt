package custom

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import ch.tutteli.atrium.translations.DescriptionBasic
import org.jetbrains.spek.api.Spek

object SmokeSpec : Spek({
    test("see if `toBe` can be used") {
        //TODO #233 uncomment once toBe is implemented
        //assertThat(1) toBe 1
    }

    test("see if own assertion function without i18n can be used") {
        //TODO #233 uncomment once toBe is implemented
        //assertThat(2) toBe even
    }

    test("see if own assertion function with i18n can be used") {
        //TODO #233 uncomment once toBe is implemented
        //assertThat(4) toBe mutlitpeOf 2
    }
})

@Suppress("DEPRECATION")
fun Expect<Int>.isEven() =
    createAndAddAssertion(DescriptionBasic.IS, RawString.create("an even number")) { subject % 2 == 0 }

fun Expect<Int>.isMultipleOf(base: Int) = addAssertion(_isMultipleOf(this, base))

fun _isMultipleOf(assertionContainer: Expect<Int>, base: Int): Assertion =
    ExpectImpl.builder.createDescriptive(assertionContainer, DescriptionIntAssertions.IS_MULTIPLE_OF, base) {
        it % base == 0
    }

enum class DescriptionIntAssertions(override val value: String) : StringBasedTranslatable {
    IS_MULTIPLE_OF("is multiple of")
}
