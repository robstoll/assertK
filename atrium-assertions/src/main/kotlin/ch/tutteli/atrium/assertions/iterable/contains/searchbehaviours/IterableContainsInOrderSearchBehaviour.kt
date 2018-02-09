package ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours

import ch.tutteli.atrium.assertions.DescriptionIterableAssertion
import ch.tutteli.atrium.assertions.iterable.contains.IterableContains
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs

/**
 * Represents the search behaviour that expected entries have to appear in the given order within the [Iterable].
 */
@Deprecated("use the search behaviour from package creating, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsInOrderSearchBehaviour"))
open class IterableContainsInOrderSearchBehaviour : IterableContains.SearchBehaviour {
    override fun decorateDescription(description: Translatable): Translatable
        = TranslatableWithArgs(DescriptionIterableAssertion.IN_ORDER, description)
}
