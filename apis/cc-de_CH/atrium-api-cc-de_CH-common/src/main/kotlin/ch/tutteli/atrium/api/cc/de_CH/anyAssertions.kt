@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
@file:JvmMultifileClass
@file:JvmName("AnyAssertionsKt")
package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.checking.AssertionChecker
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.Reporter
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] is (equal to) [expected].
 *
 * This method might enforce in the future, that [expected] has to be the same type as [Assert.subject][SubjectProvider.subject].
 * Currently the following is possible: `esGilt(1).ist(1.0)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("api-cc-de_CH is discontinued, switch to api-fluent-en_GB; will be removed with 1.0.0")
fun <T : Any> Assert<T>.ist(expected: T)
    = addAssertion(AssertImpl.any.toBe(this, expected))

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] is not (equal to) [expected].
 *
 * This method might enforce in the future, that [expected] has to be the same type as [Assert.subject][SubjectProvider.subject].
 * Currently the following is possible: `esGilt(1).istNicht(1.0)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("api-cc-de_CH is discontinued, switch to api-fluent-en_GB; will be removed with 1.0.0")
fun <T : Any> Assert<T>.istNicht(expected: T)
    = addAssertion(AssertImpl.any.notToBe(this, expected))

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] is the same instance as [expected].
 *
 * This method might enforce in the future, that [expected] has to be the same type as [Assert.subject][SubjectProvider.subject].
 * Currently the following is possible: `esGilt(1).istSelbeInstanzWie(1.0)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("api-cc-de_CH is discontinued, switch to api-fluent-en_GB; will be removed with 1.0.0")
fun <T : Any> Assert<T>.istSelbeInstanzWie(expected: T)
    = addAssertion(AssertImpl.any.isSame(this, expected))

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] is not the same instance as [expected].
 *
 * This method might enforce in the future, that [expected] has to be the same type as [Assert.subject][SubjectProvider.subject].
 * Currently the following is possible: `esGilt(1).istNichtSelbeInstanzWie(1.0)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("api-cc-de_CH is discontinued, switch to api-fluent-en_GB; will be removed with 1.0.0")
fun <T : Any> Assert<T>.istNichtSelbeInstanzWie(expected: T)
    = addAssertion(AssertImpl.any.isNotSame(this, expected))

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] is [expected].
 *
 * @return Does not support a fluent API because: what else would you want to assert about `null` anyway?
 *
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("api-cc-de_CH is discontinued, switch to api-fluent-en_GB; will be removed with 1.0.0")
@Suppress("DEPRECATION")
inline fun <reified T : Any> AssertionPlantNullable<T?>.ist(expected: T?) {
    addAssertion(AssertImpl.any.isNullable(this, T::class, expected))
}

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] is either `null` if [assertionCreatorOrNull]
 * is null or is not `null` and holds all assertions [assertionCreatorOrNull] might create.
 *
 * It is a shortcut for
 * ```kotlin
 * if(nullOrExpected == null) ist(null)
 * else istNichtNull(assertionCreatorOrNull)
 * ```
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("api-cc-de_CH is discontinued, switch to api-fluent-en_GB; will be removed with 1.0.0")
@Suppress("DEPRECATION")
inline fun <reified T : Any> AssertionPlantNullable<T?>.istNullWennNullGegebenSonst(noinline assertionCreatorOrNull: (Assert<T>.() -> Unit)?) {
    addAssertion(AssertImpl.any.isNullIfNullGivenElse(this, T::class, assertionCreatorOrNull))
}

/**
 * Can be used to separate assertions when using the fluent API.
 *
 * For instance `esGilt(1).istKleinerAls(2).und.istGroesserAls(0)` creates
 * two assertions (not one assertion with two sub-assertions) - the first asserts that 1 is less than 2 and a second
 * asserts that 1 is greater than 0. If the first assertion fails, then usually (depending on the configured
 * [AssertionChecker]) the second assertion is not evaluated.
 *
 * @return This plant to support a fluent API.
 */
@Deprecated("api-cc-de_CH is discontinued, switch to api-fluent-en_GB; will be removed with 1.0.0")
val <T : Any> Assert<T>.und: Assert<T> get() = this

/**
 * Can be used to create a group of sub assertions when using the fluent API.
 *
 * For instance `esGilt(1).istKleinerAls(3).und { istGerade(); istKleinerAls(1) }` creates
 * two assertions where the second one consists of two sub-assertions. In case the first assertion holds, then the
 * second one is evaluated as a whole. Meaning, even though 1 is not even, it still evaluates that 1 is greater than 1.
 * Hence the reporting might (depending on the configured [Reporter]) contain both failing sub-assertions.
 *
 * @return This plant to support a fluent API.
 */
@Deprecated("api-cc-de_CH is discontinued, switch to api-fluent-en_GB; will be removed with 1.0.0")
infix fun <T : Any> AssertionPlant<T>.und(assertionCreator: Assert<T>.() -> Unit)
    = addAssertionsCreatedBy(assertionCreator)
