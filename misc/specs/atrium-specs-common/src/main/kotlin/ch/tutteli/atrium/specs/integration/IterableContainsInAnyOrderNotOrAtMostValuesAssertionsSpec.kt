package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import org.spekframework.spek2.style.specification.Suite

abstract class IterableContainsInAnyOrderNotOrAtMostValuesAssertionsSpec(
    containsNotOrAtMostPair: Pair<(String, String) -> String, Fun3<Iterable<Double>, Int, Double, Array<out Double>>>,
    containsNotPair: Pair<String, (Int) -> String>,
    rootBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : IterableContainsSpecBase({

    val containsNotOrAtMost = containsNotOrAtMostPair.second

    include(object : SubjectLessSpec<Iterable<Double>>(
        describePrefix,
        containsNotOrAtMost.forSubjectLess(2, 2.3, arrayOf())
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    fun Expect<Iterable<Double>>.containsNotOrAtMostFun(atLeast: Int, a: Double, vararg aX: Double) =
        containsNotOrAtMost(this, atLeast, a, aX.toTypedArray())

    val (containsNot, errorMsgContainsNot) = containsNotPair

    describeFun(containsNotOrAtMost.name) {

        context("throws an $illegalArgumentException") {
            it("for not at all or at most -1 -- only positive numbers") {
                expect {
                    expect(oneToSeven()).containsNotOrAtMostFun(-1, 0.0)
                }.toThrow<IllegalArgumentException> { messageContains("positive number", -1) }
            }
            it("for not at all or at most 0 -- points to $containsNot") {
                expect {
                    expect(oneToSeven()).containsNotOrAtMostFun(0, 0.0)
                }.toThrow<IllegalArgumentException> { message { toBe(errorMsgContainsNot(0)) } }
            }
        }

        context("iterable ${oneToSeven().toList()}") {
            context("happy case with $containsNotOrAtMost once") {
                it("${containsNotOrAtMostPair.first("1.0", "once")} does not throw") {
                    expect(oneToSeven()).containsNotOrAtMostFun(1, 1.0)
                }
                it("${containsNotOrAtMostPair.first("1.0 and 2.0 and 3.0", "once")} does not throw") {
                    expect(oneToSeven()).containsNotOrAtMostFun(1, 1.0, 2.0, 3.0)
                }
                it("${containsNotOrAtMostPair.first("3.0 and 1.0 and 2.0", "once")} does not throw") {
                    expect(oneToSeven()).containsNotOrAtMostFun(1, 3.0, 1.0, 2.0)
                }
                it("${containsNotOrAtMostPair.first("21.1 and 34.0 and 11.23", "twice")}  does not throw") {
                    expect(oneToSeven()).containsNotOrAtMostFun(2, 21.1, 34.0, 11.23)
                }
            }

            context("failing cases; search string at different positions") {
                it("${containsNotOrAtMostPair.first("4.0", "once")} throws AssertionError") {
                    expect {
                        expect(oneToSeven()).containsNotOrAtMostFun(1, 4.0)
                    }.toThrow<AssertionError> { messageContains("$atMost: 1", "$anEntryWhichIs: 4.0") }
                }
                it("${containsNotOrAtMostPair.first("1.0, 4.0", "once")} throws AssertionError mentioning only 4.0") {
                    expect {
                        expect(oneToSeven()).containsNotOrAtMostFun(1, 1.0, 4.0)
                    }.toThrow<AssertionError> {
                        message {
                            contains("$atMost: 1", "$anEntryWhichIs: 4.0")
                            containsNot("$anEntryWhichIs: 1.0")
                        }
                    }
                }
                it(
                    "${containsNotOrAtMostPair.first(
                        "4.0, 1.0",
                        "once"
                    )} once throws AssertionError mentioning only 4.0"
                ) {
                    expect {
                        expect(oneToSeven()).containsNotOrAtMostFun(1, 4.0, 1.0)
                    }.toThrow<AssertionError> {
                        message {
                            contains("$atMost: 1", "$anEntryWhichIs: 4.0")
                            containsNot("$anEntryWhichIs: 1.0")
                        }
                    }
                }
                it("${containsNotOrAtMostPair.first("5.0, 3.1, 3.0, 4.0", "once")} throws AssertionError") {
                    expect {
                        expect(oneToSeven()).containsNotOrAtMostFun(1, 5.0, 3.1, 3.0, 4.0)
                    }.toThrow<AssertionError> {
                        message {
                            contains.exactly(2).values(
                                "$atMost: 1"
                            )
                            contains.exactly(1).values(
                                "$rootBulletPoint$containsInAnyOrder: $separator",
                                "$anEntryWhichIs: 5.0",
                                "$numberOfOccurrences: 2",
                                "$anEntryWhichIs: 4.0",
                                "$numberOfOccurrences: 3"
                            )
                        }
                    }
                }
            }

            context("multiple occurrences of the search string") {
                it(
                    "${containsNotOrAtMostPair.first(
                        "5.0",
                        "once"
                    )} throws AssertionError and message contains both, how many times we expected (1) and how many times it actually contained 5.0 (2)"
                ) {
                    expect {
                        expect(oneToSeven()).containsNotOrAtMostFun(1, 5.0)
                    }.toThrow<AssertionError> {
                        message {
                            contains(
                                "$rootBulletPoint$containsInAnyOrder: $separator",
                                "$anEntryWhichIs: 5.0",
                                "$numberOfOccurrences: 2$separator"
                            )
                            endsWith("$atMost: 1")
                        }
                    }
                }

                it("${containsNotOrAtMostPair.first("5.0", "twice")} does not throw") {
                    expect(oneToSeven()).containsNotOrAtMostFun(2, 5.0)
                }


                it("${containsNotOrAtMostPair.first("5.0", "3 times")} does not throw") {
                    expect(oneToSeven()).containsNotOrAtMostFun(3, 5.0)
                }
                it(
                    "${containsNotOrAtMostPair.first(
                        "5.0 and 4.0",
                        "twice"
                    )} throws AssertionError and message contains both, how many times we expected (2) and how many times it actually contained 4.0 (3)"
                ) {
                    expect {
                        expect(oneToSeven()).containsNotOrAtMostFun(2, 5.0, 4.0)
                    }.toThrow<AssertionError> {
                        message {
                            contains(
                                "$rootBulletPoint$containsInAnyOrder: $separator",
                                "$anEntryWhichIs: 4.0",
                                "$numberOfOccurrences: 3$separator"
                            )
                            endsWith("$atMost: 2")
                            containsNot("$anEntryWhichIs: 5.0")
                        }
                    }
                }
                it("${containsNotOrAtMostPair.first("4.0", "3 times")} does not throw") {
                    expect(oneToSeven()).containsNotOrAtMostFun(3, 4.0)
                }
                it("${containsNotOrAtMostPair.first("5.0 and 4.0", "3 times")} does not throw") {
                    expect(oneToSeven()).containsNotOrAtMostFun(3, 5.0, 4.0)
                }

            }
        }
    }
})
