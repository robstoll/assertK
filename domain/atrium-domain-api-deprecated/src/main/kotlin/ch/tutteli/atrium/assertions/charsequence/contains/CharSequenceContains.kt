@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
package ch.tutteli.atrium.assertions.charsequence.contains

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.basic.contains.Contains
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Defines the *deprecated* contract for sophisticated [CharSequence] `contains` assertions.
 */
@Deprecated(
    "Use the interface from package domain.creating; will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains")
)
interface CharSequenceContains {

    /**
     * Represents a *deprecated* search behaviour but leaves it up to the [Searcher] how this behaviour is implemented -- yet, it
     * provides a method to decorate a description (a [Translatable]) in order that it reflects the search behaviour.
     */
    @Deprecated(
        "Use the interface from package domain.creating; will be removed with 1.0.0",
        ReplaceWith("ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains.SearchBehaviour")
    )
    interface SearchBehaviour : Contains.SearchBehaviour,
        ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains.SearchBehaviour

    /**
     * Represents the *deprecated* final step of a sophisticated `contains` assertion builder which creates the [AssertionGroup]
     * as such.
     *
     * @param T The type of the [AssertionPlant.subject][SubjectProvider.subject].
     * @param SC The type of the search criteria.
     */
    @Deprecated(
        "Use the interface from package domain.creating; will be removed with 1.0.0",
        ReplaceWith("ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains.Creator")
    )
    interface Creator<in T : CharSequence, in SC> : Contains.Creator<T, SC>,
        ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains.Creator<T, SC>

    /**
     * Represents a *deprecated* check for the search result such as: the object is contained exactly once in the input of the
     * search.
     *
     * It provides the method [createAssertion] which creates an [Assertion] representing this check.
     */
    @Deprecated(
        "Use the interface from package domain.creating; will be removed with 1.0.0",
        ReplaceWith("ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains.Checker")
    )
    interface Checker : Contains.Checker,
        ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains.Checker

    /**
     * Represents a *deprecated* searcher which supports the search behaviour [S] for a given input [CharSequence] of the search.
     *
     * @param S The search behaviour which should be applied to the input [CharSequence] in which the [Searcher]
     *   will look for something -- the actual implementation of the search behaviour happens in the
     *   [Searcher]; [SearchBehaviour] only decorates the [Translatable] for reporting.
     * @param SC Represents the search criterion; use it for searchFor
     */
    @Deprecated(
        "Use the interface from package domain.creating; will be removed with 1.0.0",
        ReplaceWith("ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains.Searcher")
    )
    interface Searcher<S : SearchBehaviour, SC> :
        ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains.Searcher<S>
}
