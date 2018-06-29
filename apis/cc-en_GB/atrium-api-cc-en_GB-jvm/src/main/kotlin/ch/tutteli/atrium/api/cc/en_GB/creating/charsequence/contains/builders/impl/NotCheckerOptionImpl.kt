package ch.tutteli.atrium.api.cc.en_GB.creating.charsequence.contains.builders.impl

import ch.tutteli.atrium.api.cc.en_GB.creating.charsequence.contains.builders.NotCheckerOption
import ch.tutteli.atrium.domain.builders.creating.charsequence.contains.builders.NotCheckerOptionBase
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains

/**
 *  Represents the builder of a `contains not at all` check within the fluent API of a sophisticated
 * `contains` assertion for [CharSequence].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 *
 * @constructor Represents the builder of a `contains not at all` check within the fluent API of a sophisticated
 *   `contains` assertion for [CharSequence].
 * @param containsBuilder The previously used [CharSequenceContains.Builder].
 */
internal class NotCheckerOptionImpl<out T : CharSequence, out S : CharSequenceContains.SearchBehaviour>(
    containsBuilder: CharSequenceContains.Builder<T, S>
) : NotCheckerOptionBase<T, S>(containsBuilder),
    NotCheckerOption<T, S>
