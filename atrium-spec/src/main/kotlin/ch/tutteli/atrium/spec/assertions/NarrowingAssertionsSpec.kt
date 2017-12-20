package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.api.cc.en_UK.*
import ch.tutteli.atrium.assertions.DescriptionNarrowingAssertion
import ch.tutteli.atrium.assertions.DescriptionNumberAssertion
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.include

abstract class NarrowingAssertionsSpec(
    verbs: IAssertionVerbFactory,
    isNotNullPair: Pair<String, AssertionPlantNullable<Int?>.(assertionCreator: AssertionPlant<Int>.() -> Unit) -> Unit>,
    isNotNullLessFun: AssertionPlantNullable<Int?>.(Int) -> Unit,
    isNotNullGreaterAndLessFun: AssertionPlantNullable<Int?>.(Int, Int) -> Unit,
    nameIsA: String,
    isAIntFun: AssertionPlant<String>.(assertionCreator: AssertionPlant<Int>.() -> Unit) -> Unit,
    isAStringFun: AssertionPlant<String>.(assertionCreator: AssertionPlant<String>.() -> Unit) -> Unit,
    isACharSequenceFun: AssertionPlant<String>.(assertionCreator: AssertionPlant<CharSequence>.() -> Unit) -> Unit,
    isASubTypeFun: AssertionPlant<SuperType>.(assertionCreator: AssertionPlant<SubType>.() -> Unit) -> Unit,
    isAIntLessFun: AssertionPlant<Number>.(Int) -> Unit,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : ch.tutteli.atrium.spec.assertions.CheckingAssertionSpec<SuperType>(verbs, describePrefix,
        checkingTriple(nameIsA, { isASubTypeFun(this, {}) }, SubType(), SuperType())
    ) {})

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val expect = verbs::checkException
    val (nameIsNotNull, isNotNullFun) = isNotNullPair

    describeFun(nameIsNotNull) {

        val assert: (Int?) -> AssertionPlantNullable<Int?> = verbs::checkNullable

        context("subject is null") {
            it("throws an AssertionError") {
                expect {
                    val i: Int? = null
                    assert(i).isNotNullFun {}
                }.toThrow<AssertionError> {
                    message {
                        containsDefaultTranslationOf(DescriptionNarrowingAssertion.IS_A)
                        contains(Integer::class.java.name)
                    }
                }
            }

            context("it still allows to define assertion on the subject even if it is null") {
                it("throws an AssertionError and contains the additional assertion as explanation") {
                    expect {
                        val i: Int? = null
                        assert(i).isNotNullLessFun(2)
                    }.toThrow<AssertionError> {
                        message {
                            containsDefaultTranslationOf(DescriptionNarrowingAssertion.IS_A)
                            contains(Integer::class.java.name)
                            containsDefaultTranslationOf(DescriptionNumberAssertion.IS_LESS_THAN)
                        }
                    }
                }
            }
        }

        context("subject is not null") {

            it("does not throw") {
                val i: Int? = 1
                assert(i).isNotNullFun {}
            }

            context("it allows to define an assertion for the subject") {
                it("does not throw if the assertion holds") {
                    val i: Int? = 1
                    assert(i).isNotNullLessFun(2)
                }

                it("throws an AssertionError if the assertion does not hold") {
                    expect {
                        val i: Int? = 1
                        assert(i).isNotNullLessFun(0)
                    }.toThrow<AssertionError>()
                }
            }
            context("it allows to define multiple assertions for the subject") {
                it("does not throw if the assertions hold") {
                    val i: Int? = 1
                    assert(i).isNotNullGreaterAndLessFun(0, 2)
                }

                it("throws an AssertionError if one assertion does not hold") {
                    expect {
                        val i: Int? = 1
                        assert(i).isNotNullGreaterAndLessFun(2, 5)
                    }.toThrow<AssertionError> {
                        message {
                            containsDefaultTranslationOf(DescriptionNumberAssertion.IS_GREATER_THAN)
                            containsNotDefaultTranslationOf(DescriptionNumberAssertion.IS_LESS_THAN)
                        }
                    }
                }

                it("throws an AssertionError if both assertions do not hold and contains both messages") {
                    expect {
                        val i: Int? = 1
                        assert(i).isNotNullGreaterAndLessFun(2, 0)
                    }.toThrow<AssertionError> {
                        message {
                            containsDefaultTranslationOf(
                                DescriptionNumberAssertion.IS_GREATER_THAN,
                                DescriptionNumberAssertion.IS_LESS_THAN
                            )
                        }
                    }
                }
            }
        }

        context("in a feature assertion and subject is null") {
            it("throws an AssertionError") {
                class A(val i: Int? = null)
                expect {
                    verbs.checkLazily(A()) { property(subject::i).isNotNull {} }
                }.toThrow<AssertionError> {
                    message {
                        contains(A::class.simpleName!!)
                        containsDefaultTranslationOf(DescriptionNarrowingAssertion.IS_A)
                        contains(Integer::class.java.name)
                    }
                }
            }

            it("throws an AssertionError which contains subsequent assertions") {
                class A(val i: Int? = null)
                expect {
                    verbs.checkLazily(A()) { property(subject::i).isNotNullLessFun(1) }
                }.toThrow<AssertionError> {
                    message {
                        contains(A::class.simpleName!!)
                        containsDefaultTranslationOf(DescriptionNarrowingAssertion.IS_A, DescriptionNumberAssertion.IS_LESS_THAN)
                        contains(Integer::class.java.name)
                    }
                }
            }
        }
    }

    describeFun(nameIsA) {

        val assert: (String) -> AssertionPlant<String> = verbs::checkImmediately

        context("subject is not in type hierarchy") {
            it("throws an AssertionError") {
                expect {
                    assert("hello").isAIntFun {}
                }.toThrow<AssertionError> {
                    message {
                        containsDefaultTranslationOf(DescriptionNarrowingAssertion.IS_A)
                        contains(Integer::class.java.name)
                    }
                }
            }
        }
        context("subject is the same type") {
            it("does not throw an AssertionError") {
                assert("hello").isAStringFun {}
            }

            group("it allows to perform an assertion specific for the subtype...") {

                test("... which holds -- does not throw") {
                    verbs.checkImmediately(1).isAIntLessFun(2)
                }

                val expectedLessThan = 2
                val actualValue = 5
                test("... which fails -- throws an AssertionError") {
                    expect {
                        verbs.checkImmediately(actualValue).isAIntLessFun(expectedLessThan)
                    }.toThrow<AssertionError> {
                        message { contains(actualValue, DescriptionNumberAssertion.IS_LESS_THAN.getDefault(), expectedLessThan) }
                    }
                }
            }
        }

        context("subject is a subtype") {
            it("does not throw an AssertionError if the subject is a subtype") {
                assert("hello").isACharSequenceFun {}
            }

            group("it allows to perform an assertion specific for the subtype...") {

                test("... which holds -- does not throw") {
                    verbs.checkImmediately(1).isAIntLessFun(2)
                }

                val expectedLessThan = 2
                val actualValue = 5
                test("... which fails -- throws an AssertionError") {
                    expect {
                        verbs.checkImmediately(actualValue).isAIntLessFun(expectedLessThan)
                    }.toThrow<AssertionError> {
                        message { contains(actualValue, DescriptionNumberAssertion.IS_LESS_THAN.getDefault(), expectedLessThan) }
                    }
                }
            }
        }

        context("subject is a supertype") {
            it("throws an AssertionError") {
                expect {
                    verbs.checkImmediately(SuperType()).isASubTypeFun {}
                }.toThrow<AssertionError> {
                    message { contains(SuperType::class.java.name, "is type or sub-type of", SubType::class.java.name) }
                }
            }
        }
    }

}) {
    open class SuperType
    class SubType : SuperType()
}
