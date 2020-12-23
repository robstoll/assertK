package ch.tutteli.atrium.logic.creating.iterable.contains.creators.impl

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.builderContainsInIterableLike
import ch.tutteli.atrium.logic.creating.iterable.contains.creators.entriesInAnyOrderOnly
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InOrderOnlyGroupedSearchBehaviour
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.kbox.identity

class InOrderOnlyGroupedEntriesExpectationCreator<E : Any, T : IterableLike>(
    converter: (T) -> Iterable<E?>,
    searchBehaviour: InOrderOnlyGroupedSearchBehaviour
) : InOrderOnlyGroupedExpectationCreator<E?, T, (Expect<E>.() -> Unit)?>(converter, searchBehaviour),
    InOrderOnlyMatcher<E?, (Expect<E>.() -> Unit)?> by InOrderOnlyEntriesMatcher() {

    override fun Expect<List<E?>>.addSublistAssertion(groupOfSearchCriteria: List<(Expect<E>.() -> Unit)?>) {
        _logic.builderContainsInIterableLike(::identity)._logic.inAnyOrder._logic.butOnly._logicAppend {
            entriesInAnyOrderOnly(groupOfSearchCriteria)
        }
    }
}
