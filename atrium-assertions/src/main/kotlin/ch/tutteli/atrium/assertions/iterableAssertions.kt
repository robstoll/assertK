package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.assertions.iterable.contains.IIterableContains
import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsCheckerBuilder
import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsNoOpCheckerBuilder
import ch.tutteli.atrium.assertions.iterable.contains.creators.*
import ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours.IterableContainsInAnyOrderSearchBehaviour
import ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours.IterableContainsInAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours.IterableContainsInOrderOnlySearchBehaviour
import ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours.IterableContainsNoOpSearchBehaviour
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.RawString

fun <E, T : Iterable<E>> _containsBuilder(plant: IAssertionPlant<T>)
    = IterableContainsBuilder(plant, IterableContainsNoOpSearchBehaviour)

fun <E, T : Iterable<E>> _containsNot(plant: IAssertionPlant<T>, expected: E, otherExpected: Array<out E>): IAssertion {
    val assertions = mutableListOf<IAssertion>()
    listOf(expected, *otherExpected).forEach {
        assertions.add(LazyThreadUnsafeBasicAssertion {
            BasicAssertion(DescriptionIterableAssertion.CONTAINS_NOT, it ?: RawString.NULL, { !plant.subject.contains(it) })
        })
    }
    return InvisibleAssertionGroup(assertions)
}

fun <E, T : Iterable<E>> _containsObjectsInAnyOrder(
    checker: IterableContainsCheckerBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>,
    expected: E,
    otherExpected: Array<out E>
): IAssertion
    = createAssertionGroup(checker, expected, otherExpected, ::IterableContainsInAnyOrderObjectsAssertionCreator)

fun <E : Any, T : Iterable<E>> _containsEntriesInAnyOrder(
    checker: IterableContainsCheckerBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>,
    assertionCreator: IAssertionPlant<E>.() -> Unit,
    otherAssertionCreators: Array<out IAssertionPlant<E>.() -> Unit>
): IAssertion
    = createAssertionGroup(checker, assertionCreator, otherAssertionCreators, ::IterableContainsInAnyOrderEntriesAssertionCreator)

fun <E, T : Iterable<E>> _containsObjectsInAnyOrderOnly(
    builder: IterableContainsBuilder<E, T, IterableContainsInAnyOrderOnlySearchBehaviour>,
    expected: E,
    otherExpected: Array<out E>
): IAssertion {
    val checker = IterableContainsNoOpCheckerBuilder(builder)
    return createAssertionGroupWithoutChecker(checker, expected, otherExpected, ::IterableContainsInAnyOrderOnlyObjectsAssertionCreator)
}

fun <E : Any, T : Iterable<E>> _containsEntriesInAnyOrderOnly(
    builder: IterableContainsBuilder<E, T, IterableContainsInAnyOrderOnlySearchBehaviour>,
    assertionCreator: IAssertionPlant<E>.() -> Unit,
    otherAssertionCreators: Array<out IAssertionPlant<E>.() -> Unit>
): IAssertion {
    val checker = IterableContainsNoOpCheckerBuilder(builder)
    return createAssertionGroupWithoutChecker(checker, assertionCreator, otherAssertionCreators, ::IterableContainsInAnyOrderOnlyEntriesAssertionCreator)
}

fun <E, T : Iterable<E>> _containsObjectsInOrderOnly(
    builder: IterableContainsBuilder<E, T, IterableContainsInOrderOnlySearchBehaviour>,
    expected: E,
    otherExpected: Array<out E>
): IAssertion {
    val checker = IterableContainsNoOpCheckerBuilder(builder)
    return createAssertionGroupWithoutChecker(checker, expected, otherExpected, ::IterableContainsInOrderOnlyObjectsAssertionCreator)
}

fun <E : Any, T : Iterable<E>> _containsEntriesInOrderOnly(
    builder: IterableContainsBuilder<E, T, IterableContainsInOrderOnlySearchBehaviour>,
    assertionCreator: IAssertionPlant<E>.() -> Unit,
    otherAssertionCreators: Array<out IAssertionPlant<E>.() -> Unit>
): IAssertion {
    val checker = IterableContainsNoOpCheckerBuilder(builder)
    return createAssertionGroupWithoutChecker(checker, assertionCreator, otherAssertionCreators, ::IterableContainsInOrderOnlyEntriesAssertionCreator)
}

private fun <E, T : Iterable<E>, S, B : IIterableContains.ISearchBehaviour> createAssertionGroupWithoutChecker(
    checker: IterableContainsCheckerBuilder<E, T, B>,
    expected: S,
    otherExpected: Array<out S>,
    factory: (B) -> IIterableContains.ICreator<T, S>
): IAssertionGroup {
    val creator = factory(checker.containsBuilder.searchBehaviour)
    return creator.createAssertionGroup(checker.containsBuilder.plant, expected, otherExpected)
}

/**
 * @param S The type of the search criteria
 */
private fun <E, T : Iterable<E>, S, B : IIterableContains.ISearchBehaviour> createAssertionGroup(
    checker: IterableContainsCheckerBuilder<E, T, B>,
    expected: S,
    otherExpected: Array<out S>,
    factory: (B, List<IIterableContains.IChecker>) -> IIterableContains.ICreator<T, S>
): IAssertionGroup {
    val creator = factory(checker.containsBuilder.searchBehaviour, checker.checkers)
    return creator.createAssertionGroup(checker.containsBuilder.plant, expected, otherExpected)
}
