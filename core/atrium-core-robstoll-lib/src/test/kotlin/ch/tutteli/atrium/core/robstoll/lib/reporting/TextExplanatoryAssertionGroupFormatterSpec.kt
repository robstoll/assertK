package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.assertions.DefaultExplanatoryAssertionGroupType
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.translating.Translator
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include

class TextExplanatoryAssertionGroupFormatterSpec : Spek({

    include(AtriumsTextExplanatoryAssertionFormatterSpec)
    include(AtriumsTextWarningAssertionFormatterSpec)
    include(AtriumsSingleAssertionGroupTypeFormatterSpec)
    include(AtriumsAssertionFormatterSpec)

}) {
    object AtriumsTextExplanatoryAssertionFormatterSpec : ch.tutteli.atrium.spec.reporting.TextExplanatoryAssertionGroupFormatterSpec(
        AssertionVerbFactory,
        factory(), "[Atrium's TextExplanatory...Spec] ")

    object AtriumsTextWarningAssertionFormatterSpec : ch.tutteli.atrium.spec.reporting.TextWarningAssertionGroupFormatterSpec(
        AssertionVerbFactory,
        factory(), "[Atrium's TextWarning...Spec] ")

    object AtriumsEmptyNameAndSubjectAssertionGroupFormatterSpec : ch.tutteli.atrium.spec.reporting.EmptyNameAndSubjectAssertionGroupFormatterSpec<ExplanatoryAssertionGroupType>(
        AssertionVerbFactory,
        factoryWithoutBulletPoint(),
        ExplanatoryAssertionGroupType::class.java,
        DefaultExplanatoryAssertionGroupType,
        object : ExplanatoryAssertionGroupType {},
        "[Atrium's EmptyNameAndSubject...Spec] ")

    object AtriumsSingleAssertionGroupTypeFormatterSpec : ch.tutteli.atrium.spec.reporting.SingleAssertionGroupTypeFormatterSpec<ExplanatoryAssertionGroupType>(
        AssertionVerbFactory,
        factoryWithObjectFormatter(),
        ExplanatoryAssertionGroupType::class.java,
        object : ExplanatoryAssertionGroupType {},
        DefaultExplanatoryAssertionGroupType,
        "[Atrium's SingleAssertionGroupType...Spec] "
    )

    object AtriumsAssertionFormatterSpec : ch.tutteli.atrium.spec.reporting.AssertionFormatterSpec(
        AssertionVerbFactory,
        factoryWithObjectFormatter(), "[Atrium's AssertionFormatterSpec] "
    )

    companion object {
        private fun factory() = { bulletPoints: Map<Class<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController ->
            TextExplanatoryAssertionGroupFormatter(
                bulletPoints,
                assertionFormatterController
            )
        }

        private fun factoryWithoutBulletPoint() = { assertionFormatterController: AssertionFormatterController ->
            factory()(mapOf(ExplanatoryAssertionGroupType::class.java to "*"), assertionFormatterController)
        }

        private fun factoryWithObjectFormatter() = { bulletPoints: Map<Class<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, _: ObjectFormatter, _: Translator ->
            factory()(bulletPoints, assertionFormatterController)
        }
    }
}
