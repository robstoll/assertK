package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion.EXACTLY
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.include

abstract class CharSequenceContainsExactlyAssertionSpec(
    verbs: AssertionVerbFactory,
    containsExactlyTriple: Triple<String, (String, String) -> String, Assert<CharSequence>.(Int, Any, Array<out Any>) -> Assert<CharSequence>>,
    containsExactlyIgnoringCaseTriple: Triple<String, (String, String) -> String, Assert<CharSequence>.(Int, Any, Array<out Any>) -> Assert<CharSequence>>,
    containsNotPair: Pair<String, (Int) -> String>,
    describePrefix: String = "[Atrium] "
) : CharSequenceContainsSpecBase({

    include(object : SubjectLessAssertionSpec<CharSequence>(describePrefix,
        containsExactlyTriple.first to mapToCreateAssertion { containsExactlyTriple.third(this, 2, 2.3, arrayOf()) },
        containsExactlyIgnoringCaseTriple.first to mapToCreateAssertion { containsExactlyIgnoringCaseTriple.third(this, 2, 2.3, arrayOf()) }
    ) {})

    include(object : CheckingAssertionSpec<String>(verbs, describePrefix,
        checkingTriple(containsExactlyTriple.first, { containsExactlyTriple.third(this, 2, 2.3, arrayOf()) }, "2.3 / 2.3", "2.3"),
        checkingTriple(containsExactlyIgnoringCaseTriple.first, { containsExactlyIgnoringCaseTriple.third(this, 2, 2.3, arrayOf()) }, "2.3 / 2.3", "2.3")
    ) {})

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val assert: (CharSequence) -> Assert<CharSequence> = verbs::checkImmediately
    val expect = verbs::checkException
    val fluent = assert(text)
    val fluentHelloWorld = assert(helloWorld)

    val (containsExactly, containsExactlyTest, containsExactlyFunArr) = containsExactlyTriple
    fun Assert<CharSequence>.containsExactlyFun(atLeast: Int, a: Any, vararg aX: Any)
        = containsExactlyFunArr(atLeast, a, aX)

    val (_, containsExactlyIgnoringCase, containsExactlyIgnoringCaseFunArr) = containsExactlyIgnoringCaseTriple
    fun Assert<CharSequence>.containsExactlyIgnoringCaseFun(atLeast: Int, a: Any, vararg aX: Any)
        = containsExactlyIgnoringCaseFunArr(atLeast, a, aX)

    val (containsNot, errorMsgContainsNot) = containsNotPair

    val exactly = EXACTLY.getDefault()

    describeFun(containsExactly) {
        context("throws an $illegalArgumentException") {
            test("for exactly -1 -- only positive numbers") {
                expect {
                    fluent.containsExactlyFun(-1, "")
                }.toThrow<IllegalArgumentException> { messageContains("positive number", -1) }
            }
            test("for exactly 0 -- points to $containsNot") {
                expect {
                    fluent.containsExactlyFun(0, "")
                }.toThrow<IllegalArgumentException> { message { toBe(errorMsgContainsNot(0)) } }
            }
            test("if an object is passed as first expected") {
                expect {
                    fluent.containsExactlyFun(1, fluent)
                }.toThrow<IllegalArgumentException> { messageContains("CharSequence", "Number", "Char") }
            }
            test("if an object is passed as second expected") {
                expect {
                    fluent.containsExactlyFun(1, "that's fine", fluent)
                }.toThrow<IllegalArgumentException> { messageContains("CharSequence", "Number", "Char") }
            }
        }

        context("text '$helloWorld'") {

            group("happy case with $containsExactly once") {
                test("${containsExactlyTest("'H'", "once")} does not throw") {
                    fluentHelloWorld.containsExactlyFun(1, 'H')
                }
                test("${containsExactlyTest("'H' and 'e' and 'W'", "once")} does not throw") {
                    fluentHelloWorld.containsExactlyFun(1, 'H', 'e', 'W')
                }
                test("${containsExactlyTest("'W' and 'H' and 'e'", "once")} does not throw") {
                    fluentHelloWorld.containsExactlyFun(1, 'W', 'H', 'e')
                }
            }

            group("failing assertions; search string at different positions with $containsExactly once") {
                test("${containsExactlyTest("'h'", "once")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsExactlyFun(1, 'h')
                    }.toThrow<AssertionError> { message { containsDefaultTranslationOf(EXACTLY) } }
                }
                test("${containsExactlyIgnoringCase("'h'", "once")} throws AssertionError") {
                    fluentHelloWorld.containsExactlyIgnoringCaseFun(1, 'h')
                }

                test("${containsExactlyTest("'H', 'E'", "once")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsExactlyFun(1, 'H', 'E')
                    }.toThrow<AssertionError> { messageContains(exactly, 'E') }
                }
                test("${containsExactlyIgnoringCase("'H', 'E'", "once")} throws AssertionError") {
                    fluentHelloWorld.containsExactlyIgnoringCaseFun(1, 'H', 'E')
                }

                test("${containsExactlyTest("'E', 'H'", "once")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsExactlyFun(1, 'E', 'H')
                    }.toThrow<AssertionError> { messageContains(exactly, 'E') }
                }
                test("${containsExactlyIgnoringCase("'E', 'H'", "once")} throws AssertionError") {
                    fluentHelloWorld.containsExactlyIgnoringCaseFun(1, 'E', 'H')
                }

                test("${containsExactlyTest("'H' and 'E' and 'w'", "once")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsExactlyFun(1, 'H', 'E', 'w')
                    }.toThrow<AssertionError> { messageContains(exactly, 'E', 'w') }
                }
                test("${containsExactlyIgnoringCase("'H' and 'E' and 'w'", "once")} throws AssertionError") {
                    fluentHelloWorld.containsExactlyIgnoringCaseFun(1, 'H', 'E', 'w')
                }
            }

            group("multiple occurrences of the search string") {
                test("${containsExactlyTest("'o'", "once")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsExactlyFun(1, 'o')
                    }.toThrow<AssertionError> { message { containsDefaultTranslationOf(EXACTLY) } }
                }
                test("${containsExactlyTest("'o'", "twice")} does not throw") {
                    fluentHelloWorld.containsExactlyFun(2, 'o')
                }
                test("${containsExactlyIgnoringCase("'o'", "twice")} throws") {
                    expect {
                        fluentHelloWorld.containsExactlyIgnoringCaseFun(2, 'o')
                    }.toThrow<AssertionError> {
                        message {
                            contains(
                                "$containsIgnoringCase: 'o'",
                                "$numberOfOccurrences: 3$separator"
                            )
                            endsWith("$exactly: 2")
                        }
                    }
                }

                test("${containsExactlyTest("'o'", "3 times")} throws AssertionError and message contains both, how many times we expected (3) and how many times it actually contained 'o' (2)") {
                    expect {
                        fluentHelloWorld.containsExactlyFun(3, 'o')
                    }.toThrow<AssertionError> {
                        message {
                            contains(
                                "$containsDescr: 'o'",
                                "$numberOfOccurrences: 2$separator"
                            )
                            endsWith("$exactly: 3")
                        }
                    }
                }
                test("${containsExactlyIgnoringCase("'o'", "3 times")} does not throw") {
                    fluentHelloWorld.containsExactlyIgnoringCaseFun(3, 'o')
                }
                test("${containsExactlyIgnoringCase("'o' and 'o'", "3 times")} does not throw") {
                    fluentHelloWorld.containsExactlyIgnoringCaseFun(3, 'o', 'o')
                }

                test("${containsExactlyTest("'o' and 'l'", "twice")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsExactlyFun(2, 'o', 'l')
                    }.toThrow<AssertionError> {
                        message {
                            contains(
                                "$containsDescr: 'l'",
                                "$numberOfOccurrences: 3$separator"
                            )
                            endsWith("$exactly: 2")
                            containsNot("$containsDescr 'o'")
                        }
                    }
                }
                test("${containsExactlyTest("'l'", "3 times")} does not throw") {
                    fluentHelloWorld.containsExactlyFun(3, 'l')
                }
                test("${containsExactlyTest("'o' and 'l'", "3 times")} throws AssertionError and message contains both, how many times we expected (3) and how many times it actually contained 'o' (2)") {
                    expect {
                        fluentHelloWorld.containsExactlyFun(3, 'o', 'l')
                    }.toThrow<AssertionError> {
                        message {
                            contains(
                                "$containsDescr: 'o'",
                                "$numberOfOccurrences: 2$separator"
                            )
                            endsWith("$exactly: 3")
                            containsNot("$containsDescr 'l'")
                        }
                    }
                }
            }
        }
    }
})
