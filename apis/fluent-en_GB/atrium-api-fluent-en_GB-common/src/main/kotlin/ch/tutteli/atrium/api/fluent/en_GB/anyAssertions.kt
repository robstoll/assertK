package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.logic.*
import ch.tutteli.atrium.logic.utils.iterableLikeToIterable
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.kbox.glue

/**
 * Expects that the subject of the assertion is (equal to) [expected].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.AnyAssertionSamples.toBe
 */
fun <T> Expect<T>.toBe(expected: T): Expect<T> = _logicAppend { toBe(expected) }

/**
 * Expects that the subject of the assertion is not (equal to) [expected].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.AnyAssertionSamples.notToBe
 */
fun <T> Expect<T>.notToBe(expected: T): Expect<T> = _logicAppend { notToBe(expected) }

/**
 * Expects that the subject of the assertion is the same instance as [expected].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.AnyAssertionSamples.isSameAs
 */
fun <T> Expect<T>.isSameAs(expected: T): Expect<T> = _logicAppend { isSameAs(expected) }

/**
 * Expects that the subject of the assertion is not the same instance as [expected].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.AnyAssertionSamples.isNotSameAs
 */
fun <T> Expect<T>.isNotSameAs(expected: T): Expect<T> = _logicAppend { isNotSameAs(expected) }

/**
 * Expects that the subject of the assertion is either `null` in case [assertionCreatorOrNull]
 * is `null` or is not `null` and holds all assertions [assertionCreatorOrNull] creates.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.AnyAssertionSamples.toBeNullIfNullGivenElse
 */
inline fun <reified T : Any> Expect<T?>.toBeNullIfNullGivenElse(
    noinline assertionCreatorOrNull: (Expect<T>.() -> Unit)?
): Expect<T?> = _logicAppend { toBeNullIfNullGivenElse(T::class, assertionCreatorOrNull) }

/**
 * Expects that the subject of the assertion is not null and changes the subject to the non-nullable version.
 *
 * @return An [Expect] with the non-nullable type [T] (was `T?` before).
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.AnyAssertionSamples.notToBeNullFeature
 */
inline fun <reified T : Any> Expect<T?>.notToBeNull(): Expect<T> =
    _logic.notToBeNullButOfType(T::class).transform()

/**
 * Expects that the subject of the assertion is not null and
 * that it holds all assertions the given [assertionCreator] creates.
 *
 * @return An [Expect] with the non-nullable type [T] (was `T?` before)
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.AnyAssertionSamples.notToBeNull
 */
inline fun <reified T : Any> Expect<T?>.notToBeNull(noinline assertionCreator: Expect<T>.() -> Unit): Expect<T> =
    _logic.notToBeNullButOfType(T::class).transformAndAppend(assertionCreator)

/**
 * Expects that the subject of the assertion *is a* [TSub] (the same type or a sub-type)
 * and changes the subject to this type.
 *
 * Notice, that asserting a function type is [flawed](https://youtrack.jetbrains.com/issue/KT-27846).
 * The actual types are ignored as function types erase to Function0,
 * Function1 etc. on byte code level, which means the assertion holds as long as the subject is a
 * function and has the same amount of arguments regardless if the types differ. For instance
 * `assert({x: Int -> "hello"}).isA<String -> Unit>{}` holds, even though `(Int) -> String` is clearly not
 * a `(String) -> Unit`.
 *
 * More generally speaking, the [flaw](https://youtrack.jetbrains.com/issue/KT-27826) applies to all generic types.
 * For instance `isA<List<String>>` would only check if the subject is a `List` without checking if
 * the element type is actually `String`. Or in other words
 * `assert(listOf(1, 2)).isA<List<String>>{}` holds, even though `List<Int>` is clearly not a `List<String>`.
 *
 * @return An [Expect] with the new type [TSub].
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.AnyAssertionSamples.isAFeature
 */
inline fun <reified TSub : Any> Expect<*>.isA(): Expect<TSub> =
    _logic.isA(TSub::class).transform()

/**
 * Expects that the subject of the assertion *is a* [TSub] (the same type or a sub-type) and
 * that it holds all assertions the given [assertionCreator] creates.
 *
 * Notice, in contrast to other assertion functions which expect an [assertionCreator], this function returns not
 * [Expect] of the initial type, which was some type `T `, but an [Expect] of the specified type [TSub].
 * This has the side effect that a subsequent call has only assertion functions available which are suited for [TSub].
 * Since [Expect] is invariant it especially means that an assertion function which was not written in a generic way
 * will not be available. Fixing such a function is easy (in most cases),
 * you need to transform it into a generic from. Following an example:
 *
 * ```
 * interface Person
 * class Student: Person
 * fun Expect<Person>.foo()        = "dummy"  // limited only to Person, not recommended
 * fun <T: Person> Expect<T>.bar() = "dummy"  // available to Person and all subtypes, the way to go
 * fun Expect<Student>.baz()       = "dummy"  // specific only for Student, ok since closed class
 *
 * val p: Person = Student()
 * expect(p)               // subject of type Person
 *   .isA<Student> { ... } // subject now refined to Student
 *   .baz()                // available via Student
 *   .foo()                // not available to Student, only to Person, results in compilation error
 *   .bar()                // available via T : Person
 * ```
 *
 * Notice, that asserting a function type is [flawed](https://youtrack.jetbrains.com/issue/KT-27846).
 * The actual types are ignored as function types erase to Function0,
 * Function1 etc. on byte code level, which means the assertion holds as long as the subject is a
 * function and has the same amount of arguments regardless if the types differ. For instance
 * `assert({x: Int -> "hello"}).isA<String -> Unit>{}` holds, even though `(Int) -> String` is clearly not
 * a `(String) -> Unit`.
 *
 * More generally speaking, the [flaw](https://youtrack.jetbrains.com/issue/KT-27826) applies to all generic types.
 * For instance `isA<List<String>>` would only check if the subject is a `List` without checking if
 * the element type is actually `String`. Or in other words
 * `assert(listOf(1, 2)).isA<List<String>>{}` holds, even though `List<Int>` is clearly not a `List<String>`.
 *
 * @return An [Expect] with the new type [TSub].
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.AnyAssertionSamples.isA
 */
inline fun <reified TSub : Any> Expect<*>.isA(noinline assertionCreator: Expect<TSub>.() -> Unit): Expect<TSub> =
    _logic.isA(TSub::class).transformAndAppend(assertionCreator)

/**
 * Can be used to separate single assertions.
 *
 * For instance `expect(1).isLessThan(2).and.isGreaterThan(0)` creates
 * two assertions (not one assertion with two sub-assertions) - the first asserts that 1 is less than 2 and the second
 * asserts that 1 is greater than 0. If the first assertion fails, then the second assertion is not evaluated.
 *
 * @return An [Expect] for the current subject of the assertion.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.AnyAssertionSamples.andFeature
 */
inline val <T> Expect<T>.and: Expect<T> get() = this

/**
 * Can be used to create a group of sub assertions when using the fluent API.
 *
 * For instance `assert(1).isLessThan(3).and { isEven(); isGreaterThan(1) }` creates
 * two assertions where the second one consists of two sub-assertions. In case the first assertion holds, then the
 * second one is evaluated as a whole. Meaning, even though 1 is not even, it still evaluates that 1 is greater than 1.
 * Hence the reporting might (depending on the configured [Reporter]) contain both failing sub-assertions.
 *
 * @return An [Expect] for the current subject of the assertion.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.AnyAssertionSamples.and
 */
infix fun <T> Expect<T>.and(assertionCreator: Expect<T>.() -> Unit): Expect<T> =
    addAssertionsCreatedBy(assertionCreator)

/**
 * Expects that the subject of the assertion is not (equal to) [expected] and [otherValues].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.13.0
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.AnyAssertionSamples.isNoneOf
 */
fun <T> Expect<T>.isNoneOf(expected: T, vararg otherValues: T): Expect<T> =
    _logicAppend { isNotIn(expected glue otherValues) }

/**
 * Expects that the subject of the assertion is not (equal to) any value of [expected].
 *
 * Notice that a runtime check applies which assures that only [Iterable], [Sequence] or one of the [Array] types
 * are passed. This function expects [IterableLike] (which is a typealias for [Any]) to avoid cluttering the API.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case the iterable is empty.
 *
 * @since 0.13.0
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.AnyAssertionSamples.isNotIn
 */
inline fun <reified T> Expect<T>.isNotIn(expected: IterableLike): Expect<T> {
    val iterable = _logic.iterableLikeToIterable<T>(expected)
    require(iterable.iterator().hasNext()) { "IterableLike without elements are not allowed for this function." }
    return _logicAppend { isNotIn(iterable) }
}


