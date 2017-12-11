package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.api.cc.en_UK.*
import ch.tutteli.atrium.assertions.DescriptionIterableAssertion.EXACTLY
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.prefixedDescribe
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.include

abstract class IterableContainsInAnyOrderExactlyObjectsAssertionSpec(
    verbs: IAssertionVerbFactory,
    containsExactlyTriple: Triple<String, (String, String) -> String, IAssertionPlant<Iterable<Double>>.(Int, Double, Array<out Double>) -> IAssertionPlant<Iterable<Double>>>,
    containsNotPair: Pair<String, (Int) -> String>,
    describePrefix: String = "[Atrium] "
) : IterableContainsSpecBase({

    include(object : ch.tutteli.atrium.spec.assertions.SubjectLessAssertionSpec<Iterable<Double>>(
        containsExactlyTriple.first to mapToCreateAssertion { containsExactlyTriple.third(this, 2, 2.3, arrayOf()) }
    ) {})

    include(object : ch.tutteli.atrium.spec.assertions.CheckingAssertionSpec<Iterable<Double>>(verbs,
        checkingTriple(containsExactlyTriple.first, { containsExactlyTriple.third(this, 2, 2.3, arrayOf()) }, listOf(2.3, 2.3) as Iterable<Double>, listOf(2.3))
    ) {})

    fun prefixedDescribe(description: String, body: SpecBody.() -> Unit) {
        prefixedDescribe(describePrefix, description, body)
    }

    val assert: (Iterable<Double>) -> IAssertionPlant<Iterable<Double>> = verbs::checkImmediately
    val expect = verbs::checkException
    val fluent = assert(oneToSeven)

    val (containsExactly, containsExactlyTest, containsExactlyFunArr) = containsExactlyTriple
    fun IAssertionPlant<Iterable<Double>>.containsExactlyFun(atLeast: Int, a: Double, vararg aX: Double)
        = containsExactlyFunArr(atLeast, a, aX.toTypedArray())

    val (containsNot, errorMsgContainsNot) = containsNotPair

    val exactly = EXACTLY.getDefault()

    prefixedDescribe("fun $containsExactly") {
        context("throws an $illegalArgumentException") {
            test("for exactly -1 -- only positive numbers") {
                expect {
                    fluent.containsExactlyFun(-1, 0.0)
                }.toThrow<IllegalArgumentException> { message { contains("positive number", -1) } }
            }
            test("for exactly 0 -- points to $containsNot") {
                expect {
                    fluent.containsExactlyFun(0, 0.0)
                }.toThrow<IllegalArgumentException> { message { toBe(errorMsgContainsNot(0)) } }
            }
        }

        context("text $oneToSeven") {

            group("happy case with $containsExactly once") {
                test("${containsExactlyTest("1.0", "once")} does not throw") {
                    fluent.containsExactlyFun(1, 1.0)
                }
                test("${containsExactlyTest("1.0 and 2.0 and 3.0", "once")} does not throw") {
                    fluent.containsExactlyFun(1, 1.0, 2.0, 3.0)
                }
                test("${containsExactlyTest("3.0 and 1.0 and 2.0", "once")} does not throw") {
                    fluent.containsExactlyFun(1, 3.0, 1.0, 2.0)
                }
            }

            group("failing assertions; search string at different positions with $containsExactly once") {
                test("${containsExactlyTest("4.0", "once")} throws AssertionError") {
                    expect {
                        fluent.containsExactlyFun(1, 4.0)
                    }.toThrow<AssertionError> { message { containsDefaultTranslationOf(EXACTLY) } }
                }

                test("${containsExactlyTest("1.0, 2.3", "once")} throws AssertionError") {
                    expect {
                        fluent.containsExactlyFun(1, 1.0, 2.3)
                    }.toThrow<AssertionError> { message { contains(exactly, 2.3) } }
                }

                test("${containsExactlyTest("2.3, 1.0", "once")} throws AssertionError") {
                    expect {
                        fluent.containsExactlyFun(1, 2.3, 1.0)
                    }.toThrow<AssertionError> { message { contains(exactly, 2.3) } }
                }

                test("${containsExactlyTest("1.0 and 2.3 and 3.1", "once")} throws AssertionError") {
                    expect {
                        fluent.containsExactlyFun(1, 1.0, 2.3, 3.1)
                    }.toThrow<AssertionError> { message { contains(exactly, 2.3, 3.1) } }
                }
            }

            group("multiple occurrences of the search string") {
                test("${containsExactlyTest("5.0", "once")} throws AssertionError") {
                    expect {
                        fluent.containsExactlyFun(1, 5.0)
                    }.toThrow<AssertionError> { message { containsDefaultTranslationOf(EXACTLY) } }
                }
                test("${containsExactlyTest("5.0", "twice")} does not throw") {
                    fluent.containsExactlyFun(2, 5.0)
                }

                test("${containsExactlyTest("5.0", "3 times")} throws AssertionError and message contains both, how many times we expected (3) and how many times it actually contained 5.0 (2)") {
                    expect {
                        fluent.containsExactlyFun(3, 5.0)
                    }.toThrow<AssertionError> {
                        message {
                            contains(
                                "$containsInAnyOrder: 5.0",
                                "$numberOfOccurrences: 2$separator"
                            )
                            endsWith("$exactly: 3")
                        }
                    }
                }

                test("${containsExactlyTest("5.0 and 4.0", "twice")} throws AssertionError") {
                    expect {
                        fluent.containsExactlyFun(2, 5.0, 4.0)
                    }.toThrow<AssertionError> {
                        message {
                            contains(
                                "$containsInAnyOrder: 4.0",
                                "$numberOfOccurrences: 3$separator"
                            )
                            endsWith("$exactly: 2")
                            containsNot("$containsInAnyOrder 5.0")
                        }
                    }
                }
                test("${containsExactlyTest("4.0", "3 times")} does not throw") {
                    fluent.containsExactlyFun(3, 4.0)
                }
                test("${containsExactlyTest("5.0 and 4.0", "3 times")} throws AssertionError and message contains both, how many times we expected (3) and how many times it actually contained 5.0 (2)") {
                    expect {
                        fluent.containsExactlyFun(3, 5.0, 4.0)
                    }.toThrow<AssertionError> {
                        message {
                            contains(
                                "$containsInAnyOrder: 5.0",
                                "$numberOfOccurrences: 2$separator"
                            )
                            endsWith("$exactly: 3")
                            containsNot("$containsInAnyOrder 4.0")
                        }
                    }
                }
            }
        }
    }
})
