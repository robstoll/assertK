package ch.tutteli.atrium.creating.iterable.contains

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.basic.contains.Contains
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Defines the contract for sophisticated [Iterable] `contains` assertions.
 */
interface IterableContains {

    /**
     * Represents a search behaviour but leaves it up to the [Creator] how this behaviour is implemented -- yet, it
     * provides a method to decorate a description (a [Translatable]) in order that it reflects the search behaviour.
     */
    interface SearchBehaviour : Contains.SearchBehaviour

    /**
     * Represents the final step of a sophisticated `contains` assertion builder which creates the [AssertionGroup]
     * as such.
     *
     * @param T The type of the [AssertionPlant.subject].
     * @param S The type of the search criteria.
     */
    interface Creator<in T : Iterable<*>, in S> : Contains.Creator<T, S>

    /**
     * Represents a check for the search result such as: the object is contained exactly once in the input of the search.
     *
     * It provides the method [createAssertion] which creates an [Assertion] representing this check.
     */
    interface Checker : Contains.Checker
}
