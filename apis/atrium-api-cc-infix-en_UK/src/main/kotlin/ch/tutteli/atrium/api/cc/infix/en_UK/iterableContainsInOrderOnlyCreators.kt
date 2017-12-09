package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.assertions._containsEntriesInOrderOnly
import ch.tutteli.atrium.assertions._containsObjectsInOrderOnly
import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours.IterableContainsInOrderOnlySearchBehaviour
import ch.tutteli.atrium.creating.IAssertionPlant

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] needs to contain only the
 * [expected] value.
 *
 * Delegates to `the Objects(expected)`.
 *
 * @param expected The value which is expected to be contained within the [Iterable].
 *
 * @return The [IAssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E, T : Iterable<E>> IterableContainsBuilder<E, T, IterableContainsInOrderOnlySearchBehaviour>.value(expected: E): IAssertionPlant<T>
    = this the Objects(expected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the expected [values]
 * need to be contained in [Iterable] in the specified order.
 *
 * Delegates to `the Objects(values)`.
 *
 * @param values The values which are expected to be contained within the [Iterable].
 *
 * @return The [IAssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E, T : Iterable<E>> IterableContainsBuilder<E, T, IterableContainsInOrderOnlySearchBehaviour>.the(values: Values<E>): IAssertionPlant<T>
    = this the Objects(values)


/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] needs to contain only the
 * [expected] object.
 *
 * Delegate to `the Objects(expected)`.
 *
 * @param expected The object which is expected to be contained within the [Iterable].
 *
 * @return The [IAssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E, T : Iterable<E>> IterableContainsBuilder<E, T, IterableContainsInOrderOnlySearchBehaviour>.`object`(expected: E): IAssertionPlant<T>
    = this the Objects(expected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the expected [objects]
 * need to be contained in [Iterable] in the specified order.
 *
 * @param objects The value which are expected to be contained within the [Iterable].
 *
 * @return The [IAssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E, T : Iterable<E>> IterableContainsBuilder<E, T, IterableContainsInOrderOnlySearchBehaviour>.the(objects: Objects<E>): IAssertionPlant<T>
    = plant.addAssertion(_containsObjectsInOrderOnly(this, objects.expected, objects.otherExpected))


/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] needs to contain only one
 * entry which holds all assertions created by the given [assertionCreator].
 *
 * Delegates to `the Entries(assertionCreator)`
 *
 * @param assertionCreator The identification lambda.
 *
 * @return The [IAssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> IterableContainsBuilder<E, T, IterableContainsInOrderOnlySearchBehaviour>.entry(assertionCreator: IAssertionPlant<E>.() -> Unit): IAssertionPlant<T>
    = this the Entries(assertionCreator)


/**
 * Finishes the specification of the sophisticated `contains` assertion where the entry needs to be contained in the
 * [Iterable] which holds all assertions [Entries.assertionCreator] might create -- equally an entry for each further
 * [Entries.otherAssertionCreators], following the specified order, needs to be contained in the [Iterable]
 *
 * @param entries The method object containing the identification lambdas.
 *
 * @return The [IAssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> IterableContainsBuilder<E, T, IterableContainsInOrderOnlySearchBehaviour>.the(entries: Entries<E>): IAssertionPlant<T>
    = plant.addAssertion(_containsEntriesInOrderOnly(this, entries.assertionCreator, entries.otherAssertionCreators))
