package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_UK.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion.CONTAINS
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion.CONTAINS_NOT
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.include

abstract class CharSequenceContainsContainsNotAssertionSpec(
    verbs: AssertionVerbFactory,
    containsPair: Pair<String, Assert<CharSequence>.(String, Array<out String>) -> Assert<CharSequence>>,
    containsNotPair: Pair<String, Assert<CharSequence>.(String, Array<out String>) -> Assert<CharSequence>>,
    featureArrow: String,
    describePrefix: String = "[Atrium] "
) : CharSequenceContainsSpecBase({

    include(object : SubjectLessAssertionSpec<CharSequence>(describePrefix,
        containsPair.first to mapToCreateAssertion { containsPair.second(this, "hello", arrayOf()) },
        containsNotPair.first to mapToCreateAssertion { containsNotPair.second(this, "hello", arrayOf()) }
    ) {})

    include(object : CheckingAssertionSpec<String>(verbs, describePrefix,
        checkingTriple(containsPair.first, { containsPair.second(this, "hello", arrayOf()) }, "hello robert", "by robert"),
        checkingTriple(containsNotPair.first, { containsNotPair.second(this, "hello", arrayOf()) }, "by robert", "hello robert")
    ) {})

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val assert: (CharSequence) -> Assert<CharSequence> = verbs::checkImmediately
    val expect = verbs::checkException
    val fluent = assert(text)

    val (containsFunName, containsFunArr) = containsPair
    fun Assert<CharSequence>.containsFun(t: String, vararg tX: String)
        = containsFunArr(t, tX)

    val (containsNot, containsNotFunArr) = containsNotPair
    fun Assert<CharSequence>.containsNotFun(t: String, vararg tX: String)
        = containsNotFunArr(t, tX)

    describeFun(containsFunName, containsNot) {
        context("empty string") {
            val fluentEmptyString = assert("")
            test("$containsFunName 'Hello' throws AssertionError") {
                expect {
                    fluentEmptyString.containsFun("Hello")
                }.toThrow<AssertionError> {
                    messageContains(
                        "$numberOfOccurrences: 0",
                        "$atLeast: 1"
                    )
                }
            }
            test("$containsNot 'Hello' does not throw") {
                fluentEmptyString.containsNotFun("Hello")
            }
        }

        context("text '$text'") {

            context("search for 'Hello' and 'Robert'") {
                test("$containsFunName 'Hello' does not throw") {
                    fluent.containsFun("Hello")
                }
                test("$containsNot 'Hello' throws AssertionError") {
                    expect {
                        fluent.containsNotFun("Hello")
                    }.toThrow<AssertionError> { message { containsDefaultTranslationOf(CONTAINS_NOT) } }
                }

                test("$containsFunName 'Hello' and 'Robert' does not throw") {
                    fluent.containsFun("Hello", "Robert")
                }
                test("$containsNot 'Hello' and 'Robert' throws AssertionError") {
                    expect {
                        fluent.containsNotFun("Hello", "Robert")
                    }.toThrow<AssertionError> { message { containsDefaultTranslationOf(CONTAINS_NOT) } }
                }
            }

            context("search for 'notInThere' and 'neitherInThere'") {
                test("$containsFunName 'notInThere' and 'neitherInThere' throws AssertionError") {
                    expect {
                        fluent.containsFun("notInThere", "neitherInThere")
                    }.toThrow<AssertionError> { message { containsDefaultTranslationOf(CONTAINS) } }
                }
                test("$containsNot 'notInThere' and 'neitherInThere' does not throw") {
                    fluent.containsNotFun("notInThere", "neitherInThere")
                }
            }

            context("search for 'hello' and 'robert'") {
                test("$containsFunName 'hello' and 'robert' throws AssertionError") {
                    expect {
                        fluent.containsFun("hello", "robert")
                    }.toThrow<AssertionError> {
                        message {
                            contains.exactly(2).values(
                                containsDescr,
                                "$numberOfOccurrences: 0",
                                "$atLeast: 1"
                            )
                        }
                    }
                }
                test("$containsNot 'hello' and 'robert' does not throw") {
                    fluent.containsNotFun("hello", "robert")
                }
            }

            context("search for 'Hello' and 'notInThere'") {
                test("$containsFunName 'notInThere' throws AssertionError") {
                    expect {
                        fluent.containsFun("notInThere")
                    }.toThrow<AssertionError> { message { containsDefaultTranslationOf(CONTAINS) } }
                }
                test("$containsNot 'notInThere' does not throw") {
                    fluent.containsNotFun("notInThere")
                }

                test("$containsFunName 'Hello' and 'notInThere' throws AssertionError") {
                    expect {
                        fluent.containsFun("Hello", "notInThere")
                    }.toThrow<AssertionError> { messageContains(containsDescr, "notInThere") }
                }
                test("$containsNot 'Hello' and 'notInThere' throws AssertionError") {
                    expect {
                        fluent.containsNotFun("Hello", "notInThere")
                    }.toThrow<AssertionError> { messageContains(CONTAINS_NOT.getDefault(), "Hello") }
                }
            }

            test("$containsFunName 'Hello' and 'Hello' (searching twice in the same assertion) does not throw") {
                fluent.containsFun("Hello", "Hello")
            }

            test("$containsNot 'notInThere' and 'notInThere' does not throw") {
                fluent.containsNotFun("notInThere", "notInThere")
            }
        }

        context("error message") {
            context("feature assertion about a Person's name 'Robert Stoll'") {
                data class Person(val name: String)

                val person = Person("Robert Stoll")

                val nameWithArrow = "${featureArrow}name"
                test("$containsFunName 'treboR' and 'llotS' - error message contains '$nameWithArrow' exactly once") {
                    expect {
                        verbs.checkLazily(person) {
                            property(subject::name).containsFun("treboR", "llotS")
                        }
                    }.toThrow<AssertionError> {
                        message { contains.exactly(1).value(nameWithArrow) }
                    }
                }
                test("$containsNot 'Robert' and 'Stoll' - error message contains '$nameWithArrow' exactly once") {
                    expect {
                        verbs.checkLazily(person) {
                            property(subject::name).containsNotFun("Robert", "Stoll")
                        }
                    }.toThrow<AssertionError> {
                        message { contains.exactly(1).value(nameWithArrow) }
                    }
                }
            }
        }
    }
})
