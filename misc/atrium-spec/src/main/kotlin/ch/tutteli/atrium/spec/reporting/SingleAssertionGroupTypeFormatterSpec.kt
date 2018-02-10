package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.CoreFactory
import ch.tutteli.atrium.api.cc.en_UK.*
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.assertions.builders.AssertionBuilder
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.AssertionFormatterMethodObject
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.translating.Translator
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.it

abstract class SingleAssertionGroupTypeFormatterSpec<out T : AssertionGroupType>(
    verbs: AssertionVerbFactory,
    testeeFactory: (Map<Class<out BulletPointIdentifier>, String>, AssertionFormatterController, ObjectFormatter, Translator) -> AssertionFormatter,
    supportedAssertionGroupTypeClass: Class<T>,
    supportedAssertionGroupType: T,
    supportedAnonymousAssertionGroupType: T,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val testee = testeeFactory(mapOf(), CoreFactory.newAssertionFormatterController(), ToStringObjectFormatter, UsingDefaultTranslator())

    val unsupportedAssertion = object : Assertion {
        override fun holds() = false
    }
    val unsupportedAssertionGroup = AssertionBuilder.withType(object : AssertionGroupType {}).create( Untranslatable.EMPTY, 1, listOf())
    val supportedAssertionGroupWithAnonymousType = AssertionBuilder.withType(supportedAnonymousAssertionGroupType).create( Untranslatable.EMPTY, 1, listOf())
    val supportedAnonymousAssertionGroupWithAnonymousType = object : AssertionGroup {
        override val name = Untranslatable("test")
        override val type = supportedAnonymousAssertionGroupType
        override val subject = 1
        override val assertions: List<Assertion> = emptyList()
    }
    val supportedAssertionGroup = AssertionBuilder.withType(supportedAssertionGroupType).create(Untranslatable.EMPTY, 1, listOf())
    val supportedAnonymousAssertionGroup = object : AssertionGroup {
        override val name = Untranslatable("test")
        override val type = supportedAssertionGroupType
        override val subject = 1
        override val assertions: List<Assertion> = emptyList()
    }

    var sb = StringBuilder()
    var methodObject = AssertionFormatterMethodObject.new(sb, alwaysTrueAssertionFilter)
    afterEachTest {
        sb = StringBuilder()
        methodObject = AssertionFormatterMethodObject.new(sb, alwaysTrueAssertionFilter)
    }

    describeFun(testee::canFormat.name) {
        it("returns true for an anonymous ${AssertionGroup::class.simpleName} with type object: ${supportedAssertionGroupTypeClass.simpleName}") {
            val result = testee.canFormat(supportedAnonymousAssertionGroupWithAnonymousType)
            verbs.checkImmediately(result).isTrue()
        }
        it("returns true for an ${AssertionGroup::class.simpleName} with type object: ${supportedAssertionGroupTypeClass.simpleName}") {
            val result = testee.canFormat(supportedAssertionGroupWithAnonymousType)
            verbs.checkImmediately(result).isTrue()
        }
        it("returns true for an anonymous ${AssertionGroup::class.simpleName} with type ${supportedAssertionGroupType::class.simpleName}") {
            val result = testee.canFormat(supportedAnonymousAssertionGroup)
            verbs.checkImmediately(result).isTrue()
        }
        it("returns true for an ${AssertionGroup::class.simpleName} with type ${supportedAssertionGroupType::class.simpleName}") {
            val result = testee.canFormat(supportedAssertionGroup)
            verbs.checkImmediately(result).isTrue()
        }

        it("returns false for an ${AssertionGroup::class.simpleName} with type object: ${AssertionGroupType::class.simpleName}") {
            val result = testee.canFormat(unsupportedAssertionGroup)
            verbs.checkImmediately(result).isFalse()
        }

        it("returns false for an object : ${Assertion::class.simpleName}") {
            val result = testee.canFormat(unsupportedAssertion)
            verbs.checkImmediately(result).isFalse()
        }
    }

    describeFun(testee::formatNonGroup.name) {
        it("throws an UnsupportedOperationException") {
            verbs.checkException {
                testee.formatNonGroup(unsupportedAssertion, methodObject)
            }.toThrow<UnsupportedOperationException> { message { contains(supportedAssertionGroupTypeClass.name) } }
            verbs.checkImmediately(sb).isEmpty()
        }
    }

    describeFun(testee::formatGroup.name) {
        val doNotFormatChildren: (AssertionFormatterMethodObject, (Assertion) -> Unit) -> Unit = { _, _ -> }

        it("throws an UnsupportedOperationException for an ${AssertionGroup::class.simpleName} with type object: ${AssertionGroupType::class.simpleName}") {
            verbs.checkException {
                testee.formatGroup(unsupportedAssertionGroup, methodObject, doNotFormatChildren)
            }.toThrow<UnsupportedOperationException> { message { contains(supportedAssertionGroupTypeClass.name) } }
            verbs.checkImmediately(sb).isEmpty()
        }

        it("does not throw if an anonymous ${AssertionGroup::class.simpleName} of type object: ${supportedAssertionGroupTypeClass.simpleName} is passed") {
            testee.formatGroup(supportedAnonymousAssertionGroupWithAnonymousType, methodObject, doNotFormatChildren)
        }

        it("does not throw if an anonymous ${AssertionGroup::class.simpleName} of type ${supportedAssertionGroup::class.simpleName} is passed") {
            testee.formatGroup(supportedAnonymousAssertionGroup, methodObject, doNotFormatChildren)
        }

        it("does not throw if an ${AssertionGroup::class.simpleName} of type object: ${supportedAssertionGroupTypeClass.simpleName} is passed") {
            testee.formatGroup(supportedAssertionGroupWithAnonymousType, methodObject, doNotFormatChildren)
        }

        it("does not throw if an ${AssertionGroup::class.simpleName} of type  type ${supportedAssertionGroup::class.simpleName}  is passed") {
            testee.formatGroup(supportedAssertionGroup, methodObject, doNotFormatChildren)
        }
    }
})
