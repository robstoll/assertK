package ch.tutteli.atrium.api.cc.en_UK.assertions.iterable.contains.builders

import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsCheckerBuilder
import ch.tutteli.atrium.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsInAnyOrderSearchBehaviour

/**
 *  Represents the *deprecated* builder of a `contains at least` check within the fluent API of a sophisticated
 * `contains` assertion for [Iterable].
 *
 * @param T The input type of the search.
 *
 * @constructor Represents the builder of a `contains at least` check within the fluent API of a sophisticated
 *   `contains` assertion for [Iterable].
 * @param times The number which the check will compare against the actual number of times an expected entry is
 *   found in the [Iterable].
 * @param containsBuilder The previously used [IterableContains.Builder].
 */
@Deprecated(
    "use the builder from the package creating, will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.api.cc.en_UK.creating.iterable.contains.builders.IterableContainsAtLeastCheckerBuilder")
)
open class IterableContainsAtLeastCheckerBuilder<out E, out T : Iterable<E>>(
    times: Int,
    containsBuilder: IterableContains.Builder<E, T, IterableContainsInAnyOrderSearchBehaviour>
) : ch.tutteli.atrium.api.cc.en_UK.creating.iterable.contains.builders.AtLeastCheckerBuilderImpl<E, T, IterableContainsInAnyOrderSearchBehaviour>(
    times, containsBuilder
), IterableContainsCheckerBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>
