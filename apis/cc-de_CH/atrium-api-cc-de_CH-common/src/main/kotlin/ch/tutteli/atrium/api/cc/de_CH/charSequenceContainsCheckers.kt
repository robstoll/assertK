@file:JvmMultifileClass
@file:JvmName("CharSequenceContainsCheckersKt")
package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.api.cc.de_CH.creating.charsequence.contains.builders.*
import ch.tutteli.atrium.api.cc.de_CH.creating.charsequence.contains.builders.impl.*
import ch.tutteli.atrium.core.polyfills.JvmMultifileClass
import ch.tutteli.atrium.core.polyfills.JvmName
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains.SearchBehaviour

/**
 * Restricts a `contains` assertion by specifying that the number of occurrences of the value which we are looking
 * for occurs `at least` number of [times] within the search input.
 *
 * @param times The number which the check will compare against the number of times an expected value is
 *   found in the input of the search.
 *
 * @return The newly created builder.
 * @throws IllegalArgumentException In case [times] is smaller than zero.
 * @throws IllegalArgumentException In case [times] equals to zero; use [enthaeltNicht] instead.
 */
fun <T : CharSequence, S : SearchBehaviour> CharSequenceContains.Builder<T, S>.zumindest(times: Int): AtLeastCheckerOption<T, S>
    = AtLeastCheckerOptionImpl(times, this)

/**
 * Restricts a `contains at least` assertion by specifying that the number of occurrences of the value which we
 * are looking for occurs `at most` number of [times] within the search input.
 *
 * The resulting restriction will be a `contains at least but at most` assertion.
 *
 * @param times The number which the check will compare against the number of times an expected value is
 *   found in the input of the search.
 *
 * @return The newly created builder.
 * @throws IllegalArgumentException In case [times] is smaller than zero.
 * @throws IllegalArgumentException In case [times] equals to zero; use [enthaeltNicht] instead.
 * @throws IllegalArgumentException In case [times] of this `at most` restriction equals to the number of the
 *   `at least` restriction; use the [genau] restriction instead.
 */
fun <T : CharSequence, S : SearchBehaviour> AtLeastCheckerOption<T, S>.aberHoechstens(times: Int): ButAtMostCheckerOption<T, S>
    = ButAtMostCheckerOptionImpl(times, this, containsBuilder)

/**
 * Restricts a `contains` assertion by specifying that the number of occurrences of the value which we
 * are looking for occurs `exactly` number of [times] within the search input.
 *
 * @param times The number which the check will compare against the number of times an expected value is
 *   found in the input of the search.
 *
 * @return The newly created builder.
 * @throws IllegalArgumentException In case [times] is smaller than zero.
 * @throws IllegalArgumentException In case [times] equals to zero; use [enthaeltNicht] instead.
 */
fun <T : CharSequence, S : SearchBehaviour> CharSequenceContains.Builder<T, S>.genau(times: Int): ExactlyCheckerOption<T, S>
    = ExactlyCheckerOptionImpl(times, this)

/**
 * Restricts a `contains` assertion by specifying that the number of occurrences of the value which we
 * are looking for occurs `at least` once but `at most` number of [times] within the search input.
 *
 * If you want to use a higher lower bound than one, then use `zumindest(2).aberHoechstens(3)` instead of
 * `hoechstens(3)`. And in case you want to state that it is either not contained at all or at most a certain
 * number of times, then use `nichtOderHoechstens(2)` instead.
 *
 * @param times The number which the check will compare against the number of times an expected value is
 *   found in the input of the search.
 *
 * @return The newly created builder.
 * @throws IllegalArgumentException In case [times] is smaller than zero.
 * @throws IllegalArgumentException In case [times] equals to zero; use [enthaeltNicht] instead.
 * @throws IllegalArgumentException In case [times] equals to one; use [genau] instead.
 */
fun <T : CharSequence, S : SearchBehaviour> CharSequenceContains.Builder<T, S>.hoechstens(times: Int): AtMostCheckerOption<T, S>
    = AtMostCheckerOptionImpl(times, this)

/**
 * Restricts a `contains` assertion by specifying that the number of occurrences of the value which we
 * are looking for occurs `not at all or at most` number of [times] within the search input.
 *
 * @param times The number which the check will compare against the number of times an expected value is
 *   found in the input of the search.
 *
 * @return The newly created builder.
 * @throws IllegalArgumentException In case [times] is smaller than zero.
 * @throws IllegalArgumentException In case [times] equals to zero; use [enthaeltNicht] instead.
 */
fun <T : CharSequence, S : SearchBehaviour> CharSequenceContains.Builder<T, S>.nichtOderHoechstens(times: Int): NotOrAtMostCheckerOption<T, S>
    = NotOrAtMostCheckerOptionImpl(times, this)
