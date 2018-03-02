package ch.tutteli.atrium.creating.charsequence.contains.builders

import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains

interface CharSequenceContainsWithTimesCheckerBuilder<out T : CharSequence, out S : CharSequenceContains.SearchBehaviour>
    : CharSequenceContains.CheckerBuilder<T, S> {
    val times: Int
}
