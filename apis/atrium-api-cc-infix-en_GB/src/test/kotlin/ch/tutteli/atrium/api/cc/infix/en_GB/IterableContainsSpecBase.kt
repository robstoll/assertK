package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.entries
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.group
import ch.tutteli.atrium.api.cc.infix.en_GB.creating.iterable.contains.builders.AtLeastCheckerOption
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.contain
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.only
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.order
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.utils.GroupWithoutNullableEntries
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.*
import kotlin.reflect.KFunction2

abstract class IterableContainsSpecBase {
    private val Values = Values::class.simpleName
    private val Entries = Entries::class.simpleName

    private val containsNotFun: KFunction2<Assert<Iterable<Double>>, Double, Assert<Iterable<Double>>> = Assert<Iterable<Double>>::containsNot
    protected val toContain = "${Assert<Iterable<Double>>::to.name} ${contain::class.simpleName}"
    protected val containsNot = "${Assert<Iterable<Double>>::notTo.name} ${contain::class.simpleName}"
    protected val containsNotValues = "${containsNotFun.name} $Values"
    protected val atLeast = IterableContains.Builder<Double, Iterable<Double>, InAnyOrderSearchBehaviour>::atLeast.name
    protected val butAtMost = AtLeastCheckerOption<Double, Iterable<Double>, InAnyOrderSearchBehaviour>::butAtMost.name
    protected val exactly = IterableContains.Builder<Double, Iterable<Double>, InAnyOrderSearchBehaviour>::exactly.name
    protected val atMost = IterableContains.Builder<Double, Iterable<Double>, InAnyOrderSearchBehaviour>::atMost.name
    protected val notOrAtMost = IterableContains.Builder<Double, Iterable<Double>, InAnyOrderSearchBehaviour>::notOrAtMost.name
    protected val inAnyOrder = "${IterableContains.Builder<Double, Iterable<Double>, NoOpSearchBehaviour>::inAny.name} ${order::class.simpleName}"
    private val theInAnyOrderFun: KFunction2<IterableContains.CheckerOption<Double, Iterable<Double>, InAnyOrderSearchBehaviour>, Values<Double>, Assert<Iterable<Double>>>
        = IterableContains.CheckerOption<Double, Iterable<Double>, InAnyOrderSearchBehaviour>::the
    private val theInAnyOrder = theInAnyOrderFun.name
    protected val inAnyOrderEntries = "$theInAnyOrder $Entries"
    protected val inAnyOrderValues = "$theInAnyOrder $Values"

    private val theInAnyOrderOnlyFun: KFunction2<IterableContains.Builder<Double, Iterable<Double>, InAnyOrderOnlySearchBehaviour>, Values<Double>, Assert<Iterable<Double>>>
        = IterableContains.Builder<Double, Iterable<Double>, InAnyOrderOnlySearchBehaviour>::the
    private val theInAnyOrderOnly = theInAnyOrderOnlyFun.name
    protected val inAnyOrderOnlyValues = "$theInAnyOrderOnly $Values"
    protected val inAnyOrderOnlyEntries = "$theInAnyOrderOnly $Entries"
    protected val inOrder = "${IterableContains.Builder<Double, Iterable<Double>, NoOpSearchBehaviour>::inGiven.name} ${order::class.simpleName}"
    protected val butOnly = "${IterableContains.Builder<Double, Iterable<Double>, InAnyOrderSearchBehaviour>::but.name} ${only::class.simpleName}"
    private val theInOrderOnlyFun: KFunction2<IterableContains.Builder<Double, Iterable<Double>, InOrderOnlySearchBehaviour>, Values<Double>, Assert<Iterable<Double>>>
        = IterableContains.Builder<Double, Iterable<Double>, InOrderOnlySearchBehaviour>::the
    private val theInOrderOnly = theInOrderOnlyFun.name
    protected val inOrderOnlyValues = "$theInOrderOnly $Values"
    protected val inOrderOnlyEntries = "$theInOrderOnly $Entries"
    protected val groupedEntries = "${IterableContains.Builder<Double, Iterable<Double>, InOrderOnlySearchBehaviour>::grouped.name} ${entries::class.simpleName}"
    protected val withinGroup = "${IterableContains.Builder<Double, Iterable<Double>, InOrderOnlyGroupedSearchBehaviour>::within.name} ${group::class.simpleName}"
    private val withinInAnyOrderFun : KFunction2<IterableContains.Builder<Double, Iterable<Double>, InOrderOnlyGroupedWithinSearchBehaviour>, Order<Double, GroupWithoutNullableEntries<Double>>, AssertionPlant<Iterable<Double>>>
        = IterableContains.Builder<Double, Iterable<Double>, InOrderOnlyGroupedWithinSearchBehaviour>::inAny
    protected val withinInAnyOrder = "${withinInAnyOrderFun.name} ${order::class.simpleName}"
}
