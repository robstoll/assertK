package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.reporting.*
import ch.tutteli.atrium.reporting.translating.Translator
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.Spec
import org.jetbrains.spek.api.include

abstract class AssertionFormatterSpecBase(spec: Spec.() -> Unit) : Spek({
    include(wrap(spec))

    afterEachTest {
        sb = StringBuilder()
        parameterObject = AssertionFormatterParameterObject.new(sb, alwaysTrueAssertionFilter)
    }
}) {
    companion object {
        val separator = System.getProperty("line.separator")!!
        var sb: StringBuilder = StringBuilder()
        var parameterObject = AssertionFormatterParameterObject.new(sb, alwaysTrueAssertionFilter)
        const val bulletPoint = "*"
        const val listBulletPoint = "--"
        val indentListBulletPoint = " ".repeat(listBulletPoint.length + 1)
        const val arrow = "->"
        val indentArrow = " ".repeat(arrow.length + 1)
        const val featureBulletPoint = "+++"
        val indentFeatureBulletPoint = " ".repeat(featureBulletPoint.length + 1)
        val bulletPoints
            get() = mapOf(
                RootAssertionGroupType::class.java to "$bulletPoint ",
                ListAssertionGroupType::class.java to "$listBulletPoint ",
                PrefixFeatureAssertionGroupHeader::class.java to "$arrow ",
                FeatureAssertionGroupType::class.java to "$featureBulletPoint "
            )

        fun createFacade() = coreFactory.newAssertionFormatterFacade(coreFactory.newAssertionFormatterController())

        fun createFacade(testeeFactory: (Map<Class<out BulletPointIdentifier>, String>, AssertionFormatterController, ObjectFormatter, Translator) -> AssertionFormatter): AssertionFormatterFacade
            = createFacade(mapOf(), testeeFactory)

        fun createFacade(bulletPoint: Pair<Class<out BulletPointIdentifier>, String>, testeeFactory: (Map<Class<out BulletPointIdentifier>, String>, AssertionFormatterController, ObjectFormatter, Translator) -> AssertionFormatter): AssertionFormatterFacade
            = createFacade(mapOf(bulletPoint), testeeFactory)

        fun createFacade(extendedBulletPoints: Map<Class<out BulletPointIdentifier>, String>, testeeFactory: (Map<Class<out BulletPointIdentifier>, String>, AssertionFormatterController, ObjectFormatter, Translator) -> AssertionFormatter): AssertionFormatterFacade {
            val facade = createFacade()
            facade.register { testeeFactory(extendedBulletPoints, it, ToStringObjectFormatter, UsingDefaultTranslator()) }
            facade.register { coreFactory.newTextListAssertionGroupFormatter(bulletPoints, it, ToStringObjectFormatter, UsingDefaultTranslator()) }
            facade.register { coreFactory.newTextFeatureAssertionGroupFormatter(bulletPoints, it, ToStringObjectFormatter, UsingDefaultTranslator()) }
            facade.register { coreFactory.newTextFallbackAssertionFormatter(bulletPoints, it, ToStringObjectFormatter, UsingDefaultTranslator()) }
            return facade
        }
    }
}
