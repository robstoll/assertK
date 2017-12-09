package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.checking.IAssertionChecker
import ch.tutteli.atrium.reporting.IReporter
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable

/**
 * Makes the assertion that [IAssertionPlant.subject] is (equals) [expected].
 *
 * This method might enforce in the future, that [expected] has to be the same type as [IAssertionPlant.subject].
 * Currently the following is possible: `esGilt(1).ist(1.0)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : Any> IAssertionPlant<T>.ist(expected: T): IAssertionPlant<T>
    = addAssertion(_toBe(this, expected))

/**
 * Makes the assertion that [IAssertionPlant.subject] is not (does not equal) [expected].
 *
 * This method might enforce in the future, that [expected] has to be the same type as [IAssertionPlant.subject].
 * Currently the following is possible: `esGilt(1).istNicht(1.0)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : Any> IAssertionPlant<T>.istNicht(expected: T): IAssertionPlant<T>
    = addAssertion(_notToBe(this, expected))

/**
 * Makes the assertion that [IAssertionPlant.subject] is the same instance as [expected].
 *
 * This method might enforce in the future, that [expected] has to be the same type as [IAssertionPlant.subject].
 * Currently the following is possible: `esGilt(1).istSelbeInstanzWie(1.0)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : Any> IAssertionPlant<T>.istSelbeInstanzWie(expected: T): IAssertionPlant<T>
    = addAssertion(_isSame(this, expected))

/**
 * Makes the assertion that [IAssertionPlant.subject] is not the same instance as [expected].
 *
 * This method might enforce in the future, that [expected] has to be the same type as [IAssertionPlant.subject].
 * Currently the following is possible: `esGilt(1).istNichtSelbeInstanzWie(1.0)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : Any> IAssertionPlant<T>.istNichtSelbeInstanzWie(expected: T): IAssertionPlant<T>
    = addAssertion(_isNotSame(this, expected))

/**
 * Makes the assertion that [IAssertionPlant.subject] is `null`.
 *
 * @return Does not support a fluent API because: what else would you want to assert about `null` anyway?
 *
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : Any?> IAssertionPlantNullable<T>.istNull() {
    addAssertion(_isNull(this))
}

/**
 * Can be used to separate assertions when using the fluent API.
 *
 * For instance `esGilt(1).istKleinerAls(2).und.istGroesserAls(0)` creates
 * two assertions (not one assertion with two sub-assertions) - the first asserts that 1 is less than 2 and a second
 * asserts that 1 is greater than 0. If the first assertion fails, then usually (depending on the configured
 * [IAssertionChecker]) the second assertion is not evaluated.
 *
 * @return This plant to support a fluent API.
 */
val <T : Any> IAssertionPlant<T>.und: IAssertionPlant<T> get() = this

/**
 * Can be used to create a group of sub assertions when using the fluent API.
 *
 * For instance `esGilt(1).istKleinerAls(3).und { istGerade(); istKleinerAls(1) }` creates
 * two assertions where the second one consists of two sub-assertions. In case the first assertion holds, then the
 * second one is evaluated as a whole. Meaning, even though 1 is not even, it still evaluates that 1 is greater than 1.
 * Hence the reporting might (depending on the configured [IReporter]) contain both failing sub-assertions.
 *
 * @return This plant to support a fluent API.
 */
infix fun <T : Any> IAssertionPlant<T>.und(assertionCreator: IAssertionPlant<T>.() -> Unit)
    = addAssertionsCreatedBy(assertionCreator)

/**
 * Alias for [IAssertionPlant.subject] useful in property assertions.
 *
 * @see property
 */
val <T : Any> IAssertionPlant<T>.it get() : T = subject
