package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.include

abstract class IterableContainsInAnyOrderEntriesAssertionsSpec(
    verbs: AssertionVerbFactory,
    containsEntriesPair: Pair<String, Assert<Iterable<Double>>.(Assert<Double>.() -> Unit, Array<out Assert<Double>.() -> Unit>) -> Assert<Iterable<Double>>>,
    describePrefix: String = "[Atrium] "
) : IterableContainsEntriesSpecBase(verbs, {

    include(object : SubjectLessAssertionSpec<Iterable<Double>>(describePrefix,
        containsEntriesPair.first to mapToCreateAssertion { containsEntriesPair.second(this, { toBe(2.5) }, arrayOf()) }
    ) {})

    include(object : CheckingAssertionSpec<Iterable<Double>>(verbs, describePrefix,
        checkingTriple(containsEntriesPair.first, { containsEntriesPair.second(this, { toBe(2.5) }, arrayOf()) }, listOf(2.5).asIterable(), listOf())
    ) {})

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val assert: (Iterable<Double>) -> Assert<Iterable<Double>> = verbs::checkImmediately
    val expect = verbs::checkException
    val fluent = assert(oneToSeven)

    val (containsEntries, containsEntriesFunArr) = containsEntriesPair

    fun Assert<Iterable<Double>>.containsEntriesFun(t: Assert<Double>.() -> Unit, vararg tX: (Assert<Double>.() -> Unit))
        = containsEntriesFunArr(t, tX)

    describeFun(containsEntries) {
        context("empty collection") {
            val fluentEmpty = assert(setOf())
            test("$isLessThanFun(1.0) throws AssertionError") {
                expect {
                    fluentEmpty.containsEntriesFun({ isLessThan(1.0) })
                }.toThrow<AssertionError> {
                    messageContains(
                        "$containsInAnyOrder: $separator",
                        "$anEntryWhich: $separator",
                        "$isLessThanDescr: 1.0",
                        "$numberOfOccurrences: 0",
                        "$atLeast: 1"
                    )
                }
            }
            test("$isLessThanFun(1.0) and $isGreaterThanFun(2.0) throws AssertionError") {
                expect {
                    fluentEmpty.containsEntriesFun({ isLessThan(1.0) }, { isGreaterThan(2.0) })
                }.toThrow<AssertionError> {
                    message {
                        contains.exactly(2).values(
                            "$anEntryWhich: $separator",
                            "$numberOfOccurrences: 0",
                            "$atLeast: 1"
                        )
                        contains.exactly(1).values(
                            "$containsInAnyOrder: $separator",
                            "$isLessThanDescr: 1.0",
                            "$isGreaterThanDescr: 2.0"
                        )
                    }
                }
            }
            test("$returnValueOfFun(...) states warning that subject is not set") {
                expect {
                    fluentEmpty.containsEntriesFun({ returnValueOf(subject::dec).toBe(1.0) })
                }.toThrow<AssertionError> { message { containsDefaultTranslationOf(DescriptionIterableAssertion.CANNOT_EVALUATE_SUBJECT_EMPTY_ITERABLE) } }
            }
        }

        context("iterable $oneToSeven") {
            context("search for entry which $isGreaterThanFun(1.0) and $isLessThanFun(2.0)") {
                it("throws AssertionError containing both assumptions in one assertion") {
                    expect {
                        fluent.containsEntriesFun({ isGreaterThan(1.0); isLessThan(2.0) })
                    }.toThrow<AssertionError> {
                        message {
                            contains.exactly(1).values(
                                "$containsInAnyOrder: $separator",
                                "$anEntryWhich: $separator",
                                "$isGreaterThanDescr: 1.0",
                                "$isLessThanDescr: 2.0",
                                "$numberOfOccurrences: 0",
                                "$atLeast: 1"
                            )
                        }
                    }
                }
            }

            context("search for entry which $isGreaterThanFun(1.0) and $isLessThanFun(2.1)") {
                it("does not throw an exception") {
                    fluent.containsEntriesFun({ isGreaterThan(1.0); isLessThan(2.1) })
                }
            }

            context("search for entry which $isGreaterThanFun(1.0) and $isLessThanFun(2.1) and another entry which is $isLessThanFun(2.0)") {
                it("does not throw an exception") {
                    //finds twice the entry 1.0 but that is fine since we do not search for unique entries in this case
                    fluent.containsEntriesFun({ isGreaterThan(1.0); isLessThan(2.1) }, { isLessThan(2.0) })
                }
            }

        }

        context("search for entry where the lambda does not specify any assertion") {
            it("throws an ${IllegalArgumentException::class.simpleName}") {
                expect {
                    fluent.containsEntriesFun({})
                }.toThrow<IllegalArgumentException> { messageContains("not any assertion created") }
            }
        }
    }
})
