package ch.tutteli.atrium.logic.creating.iterable.contains.creators.impl

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.builderContainsInIterableLike
import ch.tutteli.atrium.logic.creating.iterable.contains.creators.valuesInAnyOrderOnly
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InOrderOnlyGroupedSearchBehaviour
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.kbox.identity

class InOrderOnlyGroupedValuesExpectationCreator<E, T : IterableLike>(
    converter: (T) -> Iterable<E>,
    searchBehaviour: InOrderOnlyGroupedSearchBehaviour
) : InOrderOnlyGroupedExpectationCreator<E, T, E>(converter, searchBehaviour),
    InOrderOnlyMatcher<E, E> by InOrderOnlyValueMatcher() {

    override fun Expect<List<E>>.addSublistAssertion(groupOfSearchCriteria: List<E>) {
        _logic.builderContainsInIterableLike(::identity)._logic.inAnyOrder._logic.butOnly._logicAppend {
            valuesInAnyOrderOnly(groupOfSearchCriteria)
        }
    }
}
