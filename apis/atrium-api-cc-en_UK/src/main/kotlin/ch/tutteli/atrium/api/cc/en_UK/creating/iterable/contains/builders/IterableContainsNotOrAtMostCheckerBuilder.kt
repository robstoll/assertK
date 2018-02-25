package ch.tutteli.atrium.api.cc.en_UK.creating.iterable.contains.builders

import ch.tutteli.atrium.api.cc.en_UK.notOrAtMost
import ch.tutteli.atrium.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.creating.iterable.contains.builders.IterableContainsNotOrAtMostCheckerBuilderBase
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsInAnyOrderSearchBehaviour

/**
 * Represents the builder of a `contains not or at most` check within the fluent API of a
 * sophisticated `contains` assertion for [Iterable].
 *
 * @param T The input type of the search.
 *
 * @constructor Represents the builder of a `contains at least once but at most` check within the fluent API of a
 *   sophisticated `contains` assertion for [Iterable].
 * @param times The number which the check will compare against the actual number of times an expected entry is
 *   found in the [Iterable].
 * @param containsBuilder The previously used [IterableContains.Builder].
 */
open class IterableContainsNotOrAtMostCheckerBuilder<out E, out T : Iterable<E>>(
    times: Int,
    containsBuilder: IterableContains.Builder<E, T, IterableContainsInAnyOrderSearchBehaviour>
) : IterableContainsNotOrAtMostCheckerBuilderBase<E, T, IterableContainsInAnyOrderSearchBehaviour>(
    times,
    containsBuilder,
    nameContainsNotValuesFun(),
    { "${containsBuilder::notOrAtMost.name}($it)" }
)
