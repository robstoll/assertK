package ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours

import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains

/**
 * Represents still the default search behaviour but a [CharSequenceContains.Checker] should be used which verifies
 * that the [CharSequenceContains.Searcher] could not find the expected object.
 */
interface CharSequenceContainsNotSearchBehaviour : CharSequenceContainsNoOpSearchBehaviour
