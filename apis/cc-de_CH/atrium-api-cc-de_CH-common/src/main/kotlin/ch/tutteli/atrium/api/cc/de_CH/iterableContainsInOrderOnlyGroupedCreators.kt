@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
@file:JvmMultifileClass
@file:JvmName("IterableContainsInOrderOnlyGroupedCreatorsKt")
package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.builders.utils.Group
import ch.tutteli.atrium.domain.builders.utils.groupsToList
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlyGroupedWithinSearchBehaviour
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

/**
 * Finishes the specification of the sophisticated `contains` assertion where the expected [firstGroup] as well as
 * the [secondGroup] and optionally [otherExpectedGroups] of values need to be contained in [Iterable] as
 * only elements and in the specified order whereas the values within the groups can occur in any order.
 *
 * @param firstGroup A group of values which have to appear at first within the [Iterable].
 * @param secondGroup A group of values which have to appear after the values of the [firstGroup] within the [Iterable].
 * @param otherExpectedGroups Additional groups of values which are expected to appear after the [secondGroup] within
 *   [Iterable] whereas the groups have to appear in the given order.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("api-cc-de_CH is discontinued, switch to api-fluent-en_GB; will be removed with 1.0.0")
fun <E, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlyGroupedWithinSearchBehaviour>.inBeliebigerReihenfolge(
    firstGroup: Group<E>,
    secondGroup: Group<E>,
    vararg otherExpectedGroups: Group<E>
): AssertionPlant<T> = addAssertion(
    AssertImpl.iterable.contains.valuesInOrderOnlyGrouped(
        this,
        groupsToList(firstGroup, secondGroup, otherExpectedGroups)
    )
)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the expected [firstGroup] as well as
 * the [secondGroup] and optionally [otherExpectedGroups] of identification lambdas, identifying an entry,
 * need to be contained in [Iterable] as only elements and in the specified order whereas
 * the identification lambdas within the groups can occur in any order.
 *
 * An identification lambda can also be defined with `null` in which case it matches an entry which is `null` as well.
 * Have a look at [eintraege] for more information about identification lambdas.
 *
 * @param firstGroup A group of identification lambdas which have to appear at first within the [Iterable].
 * @param secondGroup A group of identification lambdas which have to appear after the values of the [firstGroup] within the [Iterable].
 * @param otherExpectedGroups Additional groups of values which are expected to appear after the [secondGroup] within
 *   [Iterable] whereas the groups have to appear in the given order.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("api-cc-de_CH is discontinued, switch to api-fluent-en_GB; will be removed with 1.0.0")
@Suppress("DEPRECATION")
@JvmName("inBeliebigerReihenfolgeEintraege")
fun <E : Any, T : Iterable<E?>> IterableContains.Builder<E?, T, InOrderOnlyGroupedWithinSearchBehaviour>.inBeliebigerReihenfolge(
    firstGroup: Group<(Assert<E>.() -> Unit)?>,
    secondGroup: Group<(Assert<E>.() -> Unit)?>,
    vararg otherExpectedGroups: Group<(Assert<E>.() -> Unit)?>
): AssertionPlant<T> = addAssertion(
    AssertImpl.iterable.contains.entriesInOrderOnlyGroupedWithAssert(
        this,
        groupsToList(firstGroup, secondGroup, otherExpectedGroups)
    )
)
