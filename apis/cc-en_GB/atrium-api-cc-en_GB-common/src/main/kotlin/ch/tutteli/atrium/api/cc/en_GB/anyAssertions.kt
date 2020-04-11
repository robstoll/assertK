@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
@file:JvmMultifileClass
@file:JvmName("AnyAssertionsKt")

package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.checking.AssertionChecker
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.Reporter
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] is (equal to) [expected].
 *
 * This method might enforce in the future, that [expected] has to be the same type as [Assert.subject][SubjectProvider.subject].
 * Currently the following is possible: `assert(1).toBe(1.0)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0",
    ReplaceWith(
        "this.asExpect().toBe(expected).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.fluent.en_GB.toBe"
    )
)
fun <T : Any> Assert<T>.toBe(expected: T) = addAssertion(AssertImpl.any.toBe(this, expected))

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] is not (equal to) [expected].
 *
 * This method might enforce in the future, that [expected] has to be the same type as [Assert.subject][SubjectProvider.subject].
 * Currently the following is possible: `assert(1).notToBe(1.0)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0",
    ReplaceWith(
        "this.asExpect().notToBe(expected).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.fluent.en_GB.notToBe"
    )
)
fun <T : Any> Assert<T>.notToBe(expected: T) = addAssertion(AssertImpl.any.notToBe(this, expected))

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] is the same instance as [expected].
 *
 * This method might enforce in the future, that [expected] has to be the same type as [Assert.subject][SubjectProvider.subject].
 * Currently the following is possible: `assert(1).isSameAs(1.0)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0",
    ReplaceWith(
        "this.asExpect().isSameAs(expected).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.fluent.en_GB.isSameAs"
    )
)
fun <T : Any> Assert<T>.isSameAs(expected: T) = addAssertion(AssertImpl.any.isSame(this, expected))

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] is not the same instance as [expected].
 *
 * This method might enforce in the future, that [expected] has to be the same type as [Assert.subject][SubjectProvider.subject].
 * Currently the following is possible: `assert(1).isNotSameAs(1.0)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0",
    ReplaceWith(
        "this.asExpect().isNotSameAs(expected).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.fluent.en_GB.isNotSameAs"
    )
)
fun <T : Any> Assert<T>.isNotSameAs(expected: T) = addAssertion(AssertImpl.any.isNotSame(this, expected))

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] is [expected].
 *
 * @return Does not support a fluent API because: what else would you want to assert about `null` anyway?
 *
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0",
    ReplaceWith(
        "this.asExpect().toBe(expected).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.fluent.en_GB.toBe"
    )
)
inline fun <reified T : Any> AssertionPlantNullable<T?>.toBe(expected: T?) {
    addAssertion(AssertImpl.any.isNullable(this, T::class, expected))
}

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] is either `null` if [assertionCreatorOrNull]
 * is `null` or is not `null` and holds all assertions [assertionCreatorOrNull] might create.
 *
 * It is a shortcut for
 * ```kotlin
 * if (assertionCreatorOrNull == null) toBe(null)
 * else notToBeNull(assertionCreatorOrNull)
 * ```
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0",
    ReplaceWith(
        "this.asExpect().toBeNullIfNullGivenElse(assertionCreatorOrNull).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.fluent.en_GB.toBeNullIfNullGivenElse"
    )
)
inline fun <reified T : Any> AssertionPlantNullable<T?>.toBeNullIfNullGivenElse(
    noinline assertionCreatorOrNull: (Assert<T>.() -> Unit)?
) {
    addAssertion(AssertImpl.any.isNullIfNullGivenElse(this, T::class, assertionCreatorOrNull))
}

/**
 * Can be used to separate assertions when using the fluent API.
 *
 * For instance `assert(1).isLessThan(2).and.isGreaterThan(0)` creates
 * two assertions (not one assertion with two sub-assertions) - the first asserts that 1 is less than 2 and a second
 * asserts that 1 is greater than 0. If the first assertion fails, then usually (depending on the configured
 * [AssertionChecker]) the second assertion is not evaluated.
 *
 * @return This plant to support a fluent API.
 */
val <T : Any> AssertionPlant<T>.and: AssertionPlant<T> get() = this

/**
 * Can be used to create a group of sub assertions when using the fluent API.
 *
 * For instance `assert(1).isLessThan(3).and { isEven(); isGreaterThan(1) }` creates
 * two assertions where the second one consists of two sub-assertions. In case the first assertion holds, then the
 * second one is evaluated as a whole. Meaning, even though 1 is not even, it still evaluates that 1 is greater than 1.
 * Hence the reporting might (depending on the configured [Reporter]) contain both failing sub-assertions.
 *
 * @return This plant to support a fluent API.
 */
infix fun <T : Any> AssertionPlant<T>.and(assertionCreator: Assert<T>.() -> Unit) =
    addAssertionsCreatedBy(assertionCreator)
