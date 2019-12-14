@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
import ch.tutteli.atrium.api.cc.de_CH.ist
import ch.tutteli.atrium.api.cc.de_CH.messageEnthaelt
import ch.tutteli.atrium.api.cc.de_CH.wirft
import ch.tutteli.atrium.api.cc.de_CH.wirftNichts
import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.builders.migration.asExpect
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.verbs.expect
import kotlin.test.Test

class SmokeTest {
    @Test
    fun toBe_canBeUsed() {
        expect(1).ist(1)
        expect {
            expect(1).asExpect().toBe(2)
        }.wirft<AssertionError> {
            //check that correct translation is used
            messageEnthaelt("ist: 2")
        }
    }

    @Test
    fun assertionFunctionWithoutI18nCanBeUsed() {
        expect(2).isEven()
    }

    @Test
    fun assertionFunctionWithI18nCanBeUsed() {
        expect(4).isMultipleOf(2)
    }


    @Test
    fun assertAnExceptionOccurred() {
        expect {
            throw IllegalArgumentException()
        }.wirft<IllegalArgumentException>{}
    }

    @Test
    fun assertAnExceptionWithAMessageOccurred() {
        expect {
            throw IllegalArgumentException("oho... hello btw")
        }.wirft<IllegalArgumentException> {
            messageEnthaelt("hello")
        }
    }

    @Test
    fun assertNotToThrow() {
        expect {

        }.wirftNichts()
    }
}

@Suppress("DEPRECATION")
fun Assert<Int>.isEven() =
    createAndAddAssertion(DescriptionBasic.IS, RawString.create("an even number")) { subject % 2 == 0 }

fun Assert<Int>.isMultipleOf(base: Int) = addAssertion(_isMultipleOf(this, base))

fun _isMultipleOf(plant: AssertionPlant<Int>, base: Int): Assertion =
    AssertImpl.builder.createDescriptive(plant, DescriptionIntAssertions.IS_MULTIPLE_OF, base) { it % base == 0 }

enum class DescriptionIntAssertions(override val value: String) : StringBasedTranslatable {
    IS_MULTIPLE_OF("is multiple of")
}
