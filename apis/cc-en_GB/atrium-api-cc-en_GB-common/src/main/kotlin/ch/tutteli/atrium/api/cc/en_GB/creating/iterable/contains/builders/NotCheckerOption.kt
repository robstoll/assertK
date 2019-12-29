package ch.tutteli.atrium.api.cc.en_GB.creating.iterable.contains.builders

import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains

/**
 * Represents the extension point for another option after a `contains not at all`-check within
 * a sophisticated `contains` assertion building process for [Iterable].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 */
@Deprecated("Switch from api-cc-en_GB to api-fluent-en_GB; will be removed with 1.0.0")
interface NotCheckerOption<out E, out T : Iterable<E>, out S : IterableContains.SearchBehaviour>
    : IterableContains.CheckerOption<E, T, S>
