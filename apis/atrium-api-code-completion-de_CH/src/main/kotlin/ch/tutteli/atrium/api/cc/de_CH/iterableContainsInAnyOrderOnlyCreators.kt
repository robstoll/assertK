package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.assertions._containsEntriesInAnyOrderOnly
import ch.tutteli.atrium.assertions._containsObjectsInAnyOrderOnly
import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours.IterableContainsInAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.creating.IAssertionPlant

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] needs to contain only the
 * [expected] value.
 *
 * Delegates to `objekte(expected)`.
 *
 * @param expected The value which is expected to be contained within the [Iterable].
 *
 * @return The [IAssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E, T : Iterable<E>> IterableContainsBuilder<E, T, IterableContainsInAnyOrderOnlySearchBehaviour>.wert(expected: E): IAssertionPlant<T>
    = objekte(expected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] value as well as the
 * [otherExpected] values need to be contained in [Iterable] where it does not matter in which order.
 *
 * Delegates to `objekte(expected, otherExpected)`.
 *
 * @param expected The value which is expected to be contained within the [Iterable].
 * @param otherExpected Additional values which are expected to be contained within [Iterable].
 *
 * @return The [IAssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E, T : Iterable<E>> IterableContainsBuilder<E, T, IterableContainsInAnyOrderOnlySearchBehaviour>.werte(expected: E, vararg otherExpected: E): IAssertionPlant<T>
    = objekte(expected, *otherExpected)


/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] needs to contain only the
 * [expected] object.
 *
 * Delegate to `objekte(expected)`.
 *
 * @param expected The object which is expected to be contained within the [Iterable].
 *
 * @return The [IAssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E, T : Iterable<E>> IterableContainsBuilder<E, T, IterableContainsInAnyOrderOnlySearchBehaviour>.objekt(expected: E): IAssertionPlant<T>
    = objekte(expected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] object as well as the
 * [otherExpected] objects need to be contained in [Iterable] where it does not matter in which order.
 *
 * @param expected The value which is expected to be contained within the [Iterable].
 * @param otherExpected Additional values which are expected to be contained within [Iterable].
 *
 * @return The [IAssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E, T : Iterable<E>> IterableContainsBuilder<E, T, IterableContainsInAnyOrderOnlySearchBehaviour>.objekte(expected: E, vararg otherExpected: E): IAssertionPlant<T>
    = plant.addAssertion(_containsObjectsInAnyOrderOnly(this, expected, otherExpected))


/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] needs to contain only one
 * entry which holds all assertions created by the given [assertionCreator].
 *
 * Delegates to `eintraege(assertionCreator)`.
 *
 * @param assertionCreator The identification lambda.
 *
 * @return The [IAssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E>> IterableContainsBuilder<E, T, IterableContainsInAnyOrderOnlySearchBehaviour>.eintrag(assertionCreator: IAssertionPlant<E>.() -> Unit): IAssertionPlant<T>
    = eintraege(assertionCreator)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the entry needs to be contained in the
 * [Iterable] which holds all assertions [assertionCreator] might create -- equally an entry for each further
 * [otherAssertionCreators] needs to be contained in the [Iterable]  where it does not matter in which order the
 * entries appear.
 *
 * Notice, that a first-wins strategy applies which means your [assertionCreator] functions -- which kind of serve as
 * identification functions -- should be ordered in such a way that the most specific identification function appears
 * first, not that a less specific function wins. For instance, given a `setOf(1, 2)` you should not search for
 * `entries({ isGreaterThan(0) }, { toBe(1) })` but for `entries({ toBe(1) }, { isGreaterThan(0) })` otherwise
 * `isGreaterThan(0)` matches `1` before `toBe(1)` would match it. As a consequence `toBe(1)` could only match the
 * entry which is left -- in this case `2` -- and of course this would fail.
 *
 * @param assertionCreator The lambda function which creates the assertions which the entry we are looking for
 *        has to hold; or in other words, the function which defines whether an entry is the one we are looking for.
 * @param otherAssertionCreators Additional lambda functions which each kind of identify (separately) an entry
 *        which we are looking for.
 *
 * @return The [IAssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E>> IterableContainsBuilder<E, T, IterableContainsInAnyOrderOnlySearchBehaviour>.eintraege(assertionCreator: IAssertionPlant<E>.() -> Unit, vararg otherAssertionCreators: IAssertionPlant<E>.() -> Unit): IAssertionPlant<T>
    = plant.addAssertion(_containsEntriesInAnyOrderOnly(this, assertionCreator, otherAssertionCreators))
