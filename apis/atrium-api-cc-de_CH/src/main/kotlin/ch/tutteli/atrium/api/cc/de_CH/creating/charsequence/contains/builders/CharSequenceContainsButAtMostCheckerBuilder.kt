package ch.tutteli.atrium.api.cc.de_CH.creating.charsequence.contains.builders

import ch.tutteli.atrium.api.cc.de_CH.aberHoechstens
import ch.tutteli.atrium.api.cc.de_CH.genau
import ch.tutteli.atrium.api.cc.de_CH.hoechstens
import ch.tutteli.atrium.api.cc.de_CH.zumindest
import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains.SearchBehaviour
import ch.tutteli.atrium.creating.charsequence.contains.builders.CharSequenceContainsButAtMostCheckerBuilderBase

/**
 * Represents the builder of the second step of a `contains at least but at most` check within the
 * fluent API of a sophisticated `contains` assertion for [CharSequence].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied to the input of the search.
 *
 * @constructor Represents the builder of the second step of a `contains at least but at most` check within the
 *   fluent API of a sophisticated `contains` assertion for [CharSequence].
 * @param times The number which the check will compare against the actual number of times an expected object is
 *   found in the input of the search.
 * @param containsBuilder The previously used [CharSequenceContains.Builder].
 */
open class CharSequenceContainsButAtMostCheckerBuilder<out T : CharSequence, out S : SearchBehaviour>(
    times: Int,
    atLeastBuilder: CharSequenceContainsAtLeastCheckerBuilder<T, S>,
    containsBuilder: CharSequenceContains.Builder<T, S>
) : CharSequenceContainsButAtMostCheckerBuilderBase<T, S>(
    times,
    atLeastBuilder,
    containsBuilder,
    nameContainsNotValuesFun(),
    { l, u -> "${containsBuilder::zumindest.name}($l).${atLeastBuilder::aberHoechstens.name}($u)" },
    { "${containsBuilder::hoechstens.name}($it)" },
    { "${containsBuilder::zumindest.name}($it)" },
    { "${atLeastBuilder::aberHoechstens.name}($it)" },
    { "${containsBuilder::genau.name}($it)" }
)
