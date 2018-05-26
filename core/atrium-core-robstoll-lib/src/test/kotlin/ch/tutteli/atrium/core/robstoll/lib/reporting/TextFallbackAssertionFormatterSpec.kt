package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.api.cc.en_GB.toBe
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.assertions.RootAssertionGroupType
import ch.tutteli.atrium.assertions.builders.root
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.verbs.internal.AssertionVerb.ASSERT
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.verbs.internal.assert
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.translating.Translator
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import ch.tutteli.atrium.spec.reporting.ToStringObjectFormatter
import ch.tutteli.atrium.spec.reporting.alwaysTrueAssertionFilter
import ch.tutteli.atrium.translations.DescriptionAnyAssertion.NOT_TO_BE
import ch.tutteli.atrium.translations.DescriptionAnyAssertion.TO_BE
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.include

class TextFallbackAssertionFormatterSpec : Spek({

    include(AtriumsTextFallbackAssertionFormatterSpec)
    include(AtriumsAssertionFormatterSpec)

    val squarePoint = "▪"

    val facade = coreFactory.newAssertionFormatterFacade(coreFactory.newAssertionFormatterController())
    facade.register({
        TextFallbackAssertionFormatter(
            mapOf(RootAssertionGroupType::class.java to "$squarePoint "),
            it,
            TextSameLineAssertionPairFormatter(
                ToStringObjectFormatter,
                UsingDefaultTranslator()
            ), ToStringObjectFormatter
        )
    })

    var sb = StringBuilder()
    afterEachTest {
        sb = StringBuilder()
    }
    val separator = System.getProperty("line.separator")!!

    describe("fun ${TextFallbackAssertionFormatter::format.name}") {
        context("a ${AssertionGroup::class.simpleName} of type ${RootAssertionGroupType::class.simpleName}") {
            it("includes the group ${AssertionGroup::name.name}, its ${AssertionGroup::representation.name} as well as the ${AssertionGroup::assertions.name}") {
                val assertionGroup = with(AssertImpl.builder) {
                    root.withDescriptionAndRepresentation(ASSERT, "subject")
                        .withAssertions(
                            descriptive.failing.create(TO_BE, "bli"),
                            descriptive.failing.create(NOT_TO_BE, "bye")
                        )
                        .build()
                }
                assert(mapOf("1" to 2).entries)
                facade.format(assertionGroup, sb, alwaysTrueAssertionFilter)
                assert(sb.toString()).toBe("assert: subject$separator" +
                    "$squarePoint ${TO_BE.getDefault()}: bli$separator" +
                    "$squarePoint ${NOT_TO_BE.getDefault()}: bye")
            }
        }
    }
}) {
    object AtriumsTextFallbackAssertionFormatterSpec : ch.tutteli.atrium.spec.reporting.TextFallbackAssertionFormatterSpec(
        AssertionVerbFactory,
        factory(), "[Atrium's TextFallback..Spec] "
    )

    object AtriumsAssertionFormatterSpec : ch.tutteli.atrium.spec.reporting.AssertionFormatterSpec(
        AssertionVerbFactory,
        factory(), "[Atrium's AssertionFormatterSpec] "
    )

    companion object {
        internal fun factory() = { bulletPoints: Map<Class<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: Translator ->
            TextFallbackAssertionFormatter(
                bulletPoints,
                assertionFormatterController,
                TextSameLineAssertionPairFormatter(objectFormatter, translator),
                objectFormatter
            )
        }
    }
}
