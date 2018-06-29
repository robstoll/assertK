package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown

/**
 * Makes the assertion that the thrown [Throwable] is of type [TExpected] and it [assertionCreator]
 * which are checked additionally as well.
 *
 * @return Notice, that this assertion function cannot provide a fluent API because it depends on whether the first
 *   assertion (a [Throwable] was thrown) holds or not.
 * Define subsequent assertions via the [assertionCreator] lambda.
 *
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
inline infix fun <reified TExpected : Throwable> ThrowableThrown.Builder.toThrow(noinline assertionCreator: Assert<TExpected>.() -> Unit) {
    AssertImpl.throwable.thrown.toBe(this, TExpected::class, assertionCreator)
}

/**
 * Creates an [AssertionPlantNullable] for the [message][Throwable.message] of the plant's
 * [subject][AssertionPlant.subject] (which is a [Throwable]) and makes the assertion that the message ought
 * [notToBeNull] and uses [assertionCreator] which might create further [Assertion]s which are lazily evaluated at the end.
 *
 * @return Notice, that this assertion function cannot provide a fluent API because it depends on whether the first
 *   assertion [message][Throwable.message] is not null) holds or not.
 * Define subsequent assertions via the [assertionCreator] lambda.
 *
 * @throws AssertionError Might throw an [AssertionError] in case [message][Throwable.message] is `null`
 *   or if an additionally created [Assertion]s (by calling [assertionCreator]) does not hold.
 */
infix fun <T : Throwable> Assert<T>.message(assertionCreator: Assert<String>.() -> Unit) {
    property(subject::message).notToBeNull(assertionCreator)
}

/**
 * Creates the assertion that the [Throwable]'s [message][Throwable.message] is not null (see [message]) and contains
 * the [toString] representation of the given [expected] using a non disjoint search.
 *
 * It is a shortcut for `message { this to contain atLeast 1 value expected }`
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed (this
 * function expects `Any` for your convenience.
 *
 * @return Notice, that this assertion function cannot provide a fluent API because it depends on whether the first
 *   assertion [message][Throwable.message] is not null) holds or not.
 *
 * @throws AssertionError Might throw an [AssertionError] in case [message][Throwable.message] is `null`
 *   or does not contain the [expected] object.
 */
infix fun <T : Throwable> Assert<T>.messageContains(expected: Any) {
    this messageContains Values(expected)
}

/**
 * Creates the assertion that the [Throwable]'s [message][Throwable.message] is not null (see [message]) and contains
 * the [toString] representation of the given [values] using a non disjoint search.
 *
 * It is a shortcut for `message { this to contain atLeast 1 the values }`
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed as
 * [values] (this function expects `Any` for your convenience, so that you can mix [String] and [Int] for instance).
 *
 * @return Notice, that this assertion function cannot provide a fluent API because it depends on whether the first
 *   assertion [message][Throwable.message] is not null) holds or not.
 *
 * @throws AssertionError Might throw an [AssertionError] in case [message][Throwable.message] is `null`
 *   or does not contain all the [values].
 */
infix fun <T : Throwable> Assert<T>.messageContains(values: Values<Any>) {
    message { contains(values) }
}
