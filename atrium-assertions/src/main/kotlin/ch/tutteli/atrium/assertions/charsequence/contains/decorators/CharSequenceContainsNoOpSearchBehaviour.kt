package ch.tutteli.atrium.assertions.charsequence.contains.decorators

import ch.tutteli.atrium.assertions.charsequence.contains.ICharSequenceContains
import ch.tutteli.atrium.assertions.charsequence.contains.decorators.CharSequenceContainsNoOpSearchBehaviour.decorateDescription
import ch.tutteli.atrium.reporting.translating.ITranslatable

/**
 * Represents the default search behaviour or rather does not define a search behaviour at all.
 *
 * It furthermore implements a no operation for [decorateDescription], meaning it passes back the description which
 * is passed in (identity function).
 */
object CharSequenceContainsNoOpSearchBehaviour : ICharSequenceContains.ISearchBehaviour {
    /**
     * Returns the given [description].
     * @return the given [description].
     */
    override fun decorateDescription(description: ITranslatable) = description
}
