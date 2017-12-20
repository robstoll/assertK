package ch.tutteli.atrium.spec.creating

import ch.tutteli.atrium.api.cc.en_UK.*
import ch.tutteli.atrium.assertions.BasicAssertion
import ch.tutteli.atrium.assertions.DescriptionAnyAssertion
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IBasicAssertion
import ch.tutteli.atrium.creating.CheckingAssertionPlant
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import ch.tutteli.atrium.spec.inCaseOf
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.it

abstract class CheckingAssertionPlantSpec(
    verbs: IAssertionVerbFactory,
    testeeFactory: (Int) -> CheckingAssertionPlant<Int>,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val subject = 10
    val testee = testeeFactory(subject)

    describeFun(testee::allAssertionsHold.name) {
        inCaseOf("no assertion has been added so far") {
            it("throws an ${IllegalStateException::class.simpleName}") {
                verbs.checkException {
                    testee.allAssertionsHold()
                }.toThrow<IllegalStateException>()
            }
        }
    }

    describeFun(testee::createAndAddAssertion.name) {

        val a = subject
        inCaseOf("an assertion which holds") {
            testee.createAndAddAssertion(DescriptionAnyAssertion.TO_BE, a, { a == subject })
            test("${testee::allAssertionsHold.name} returns `true` and does not throw an Exception") {
                val result = testee.allAssertionsHold()
                verbs.checkImmediately(result).isTrue()
            }
        }

        inCaseOf("an assertion which fails") {
            testee.createAndAddAssertion(DescriptionAnyAssertion.TO_BE, -12, { a == 0 })
            test("${testee::allAssertionsHold.name} returns `false` and does not throw an Exception") {
                val result = testee.allAssertionsHold()
                verbs.checkImmediately(result).isFalse()
            }

            test("re-checking the assertions (calling ${testee::allAssertionsHold.name} twice) throws an  ${IllegalStateException::class.simpleName}") {
                verbs.checkException {
                    testee.allAssertionsHold()
                }.toThrow<IllegalStateException> { message { contains("create assertions first") } }
            }
        }

    }

    describeFun(testee::addAssertion.name) {
        inCaseOf("a custom assertion which holds") {
            testee.addAssertion(object : IAssertion {
                override fun holds() = true
            })
            test("${testee::allAssertionsHold.name} returns `true` and does not throw an Exception") {
                val result = testee.allAssertionsHold()
                verbs.checkImmediately(result).isTrue()
            }
        }

        inCaseOf("a custom ${IBasicAssertion::class.java.simpleName} which fails") {
            testee.addAssertion(BasicAssertion(DescriptionAnyAssertion.TO_BE, "my expected result", false))
            test("${testee::allAssertionsHold.name} returns `false` and does not throw an Exception") {
                val result = testee.allAssertionsHold()
                verbs.checkImmediately(result).isFalse()
            }

            test("re-checking the assertions (calling ${testee::allAssertionsHold.name} twice) throws an ${IllegalStateException::class.simpleName}") {
                verbs.checkException {
                    testee.allAssertionsHold()
                }.toThrow<IllegalStateException>()
            }
        }
    }
})
