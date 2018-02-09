package ch.tutteli.atrium.creating.charsequence.contains.builders

import ch.tutteli.atrium.creating.basic.contains.builders.ContainsCheckerBuilder
import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains.Checker
import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains.SearchBehaviour

/**
 * The base class for builders which create [Checker]s within the fluent API of a sophisticated
 * `contains` assertion which was started with the given [containsBuilder].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 *
 * @constructor The base class for builders which create [Checker]s within the fluent API of a sophisticated
 *   `contains` assertion which was started with the given [containsBuilder].
 * @param containsBuilder The previously used [CharSequenceContainsBuilder].
 */
abstract class CharSequenceContainsCheckerBuilder<out T : CharSequence, out S : SearchBehaviour>(
    containsBuilder: CharSequenceContainsBuilder<T, S>
) : ContainsCheckerBuilder<T, S, Checker, CharSequenceContainsBuilder<T, S>>(containsBuilder)
