package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.api.cc.en_UK.containsDefaultTranslationOf
import ch.tutteli.atrium.api.cc.en_UK.message
import ch.tutteli.atrium.api.cc.en_UK.toThrow
import ch.tutteli.atrium.assertions.DescriptionCharSequenceAssertion.AT_MOST
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.include
import java.util.regex.PatternSyntaxException

abstract class CharSequenceContainsRegexAssertionSpec(
    verbs: IAssertionVerbFactory,
    containsRegex: String,
    containsAtLeastTriple: Triple<String, (String, String) -> String, AssertionPlant<CharSequence>.(Int, String, Array<out String>) -> AssertionPlant<CharSequence>>,
    containsAtMostTriple: Triple<String, (String, String) -> String, AssertionPlant<CharSequence>.(Int, String, Array<out String>) -> AssertionPlant<CharSequence>>,
    containsAtMostIgnoringCaseTriple: Triple<String, (String, String) -> String, AssertionPlant<CharSequence>.(Int, String, Array<out String>) -> AssertionPlant<CharSequence>>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : ch.tutteli.atrium.spec.assertions.SubjectLessAssertionSpec<CharSequence>(describePrefix,
        containsAtLeastTriple.first to mapToCreateAssertion { containsAtLeastTriple.third(this, 2, "a|b", arrayOf()) },
        containsAtMostTriple.first to mapToCreateAssertion { containsAtMostTriple.third(this, 2, "a|b", arrayOf()) },
        containsAtMostIgnoringCaseTriple.first to mapToCreateAssertion { containsAtMostIgnoringCaseTriple.third(this, 2, "a|b", arrayOf()) }
    ) {})

    include(object : ch.tutteli.atrium.spec.assertions.CheckingAssertionSpec<String>(verbs, describePrefix,
        checkingTriple(containsAtLeastTriple.first, { containsAtLeastTriple.third(this, 2, "a|b", arrayOf()) }, "a, b", "a"),
        checkingTriple(containsAtMostTriple.first, { containsAtMostTriple.third(this, 2, "a|b", arrayOf()) }, "a", "a,ba"),
        checkingTriple(containsAtMostIgnoringCaseTriple.first, { containsAtMostIgnoringCaseTriple.third(this, 2, "a|b", arrayOf()) }, "a", "bbb")
    ) {})

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val assert: (CharSequence) -> AssertionPlant<CharSequence> = verbs::checkImmediately
    val expect = verbs::checkException

    val text = "Hello my name is Robert"
    val hello = "[hH][ea]llo"
    val robert = "Roberto?"
    val fluent = assert(text)

    val (_, containsAtLeastTest, containsAtLeastFunArr) = containsAtLeastTriple
    fun AssertionPlant<CharSequence>.containsAtLeastFun(atLeast: Int, a: String, vararg aX: String)
        = containsAtLeastFunArr(atLeast, a, aX)

    val (_, containsAtMostTest, containsAtMostFunArr) = containsAtMostTriple
    fun AssertionPlant<CharSequence>.containsAtMostFun(atLeast: Int, a: String, vararg aX: String)
        = containsAtMostFunArr(atLeast, a, aX)

    val (_, containsAtMostIgnoringCase, containsAtMostIgnoringCaseFunArr) = containsAtMostIgnoringCaseTriple
    fun AssertionPlant<CharSequence>.containsAtMostIgnoringCaseFun(atLeast: Int, a: String, vararg aX: String)
        = containsAtMostIgnoringCaseFunArr(atLeast, a, aX)

    describeFun(containsRegex) {
        context("throws an ${PatternSyntaxException::class.simpleName}") {
            test("if an erroneous pattern is passed") {
                expect {
                    assert("a").containsAtLeastFun(1, "notA(validPattern")
                }.toThrow<PatternSyntaxException>()
            }
            test("if an erroneous pattern is passed as second regex") {
                expect {
                    assert("a").containsAtLeastFun(1, "h(a|e)llo", "notA(validPattern")
                }.toThrow<PatternSyntaxException>()
            }
        }

        context("text $text") {
            test("${containsAtLeastTest("'$hello'", "once")} does not throw") {
                fluent.containsAtLeastFun(1, hello)
            }
            test("${containsAtLeastTest("'$hello', '$hello' and '$hello'", "once")} does not throw") {
                fluent.containsAtLeastFun(1, hello, hello, hello)
            }

            test("${containsAtLeastTest("'$hello' and '$robert'", "once")} does not throw") {
                fluent.containsAtLeastFun(1, hello, robert)
            }

            test("${containsAtMostTest("'[a-z]'", "17 times")} does not throw") {
                fluent.containsAtMostFun(17, "[a-z]")
            }
            test("${containsAtMostIgnoringCase("'[a-z]'", "19 times")} does not throw") {
                fluent.containsAtMostIgnoringCaseFun(19, "[a-z]")
            }
            test("${containsAtMostIgnoringCase("'[a-z]' and '[A-Z]'", "19 times")} does not throw") {
                fluent.containsAtMostIgnoringCaseFun(19, "[a-z]", "[A-Z]")
            }

            test("${containsAtMostTest("'[a-z]'", "16 times")} throws AssertionError") {
                expect {
                    fluent.containsAtMostFun(16, "[a-z]")
                }.toThrow<AssertionError> { message { containsDefaultTranslationOf(AT_MOST) } }
            }
            test("${containsAtMostIgnoringCase("'[a-z]'", "18 times")} throws AssertionError") {
                expect {
                    fluent.containsAtMostIgnoringCaseFun(18, "[a-z]")
                }.toThrow<AssertionError> { message { containsDefaultTranslationOf(AT_MOST) } }
            }
        }
    }
})
