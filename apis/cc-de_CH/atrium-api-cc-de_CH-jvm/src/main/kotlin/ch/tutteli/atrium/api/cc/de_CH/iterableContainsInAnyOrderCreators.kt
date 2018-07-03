@file:JvmMultifileClass
@file:JvmName("IterableContainsInAnyOrderCreatorsKt")
package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsCheckerBuilder
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour

@Deprecated("Use the extension fun `wert` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("checkerBuilder.wert(expected)"))
fun <E, T : Iterable<E>> wert(checkerBuilder: IterableContainsCheckerBuilder<E, T, InAnyOrderSearchBehaviour>, expected: E): AssertionPlant<T>
    = werte(checkerBuilder, expected)

@JvmName("deprecatedWert")
@Deprecated("Use `nullableWert` instead; will be removed with 1.0.0", ReplaceWith("nullableWert(expected)"))
fun <E : Any?, T : Iterable<E>> IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>.wert(expected: E): AssertionPlant<T>
    = nullableWert(expected)


@Deprecated("Use the extension fun `werte` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("checkerBuilder.werte(expected, *otherExpected)"))
fun <E, T : Iterable<E>> werte(checkerBuilder: IterableContainsCheckerBuilder<E, T, InAnyOrderSearchBehaviour>, expected: E, vararg otherExpected: E): AssertionPlant<T>
    = checkerBuilder.werte(expected, *otherExpected)


@Deprecated("Will be removed with 1.0.0 because it is redundant in terms of `wert(expected)` without adding enough to be a legit alternative.", ReplaceWith("wert(expected)"))
fun <E, T : Iterable<E>> IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>.objekt(expected: E): AssertionPlant<T>
    = werte(expected)

@Deprecated("Use the extension fun `wert` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("checkerBuilder.wert(expected)"))
fun <E, T : Iterable<E>> objekt(checkerBuilder: IterableContainsCheckerBuilder<E, T, InAnyOrderSearchBehaviour>, expected: E): AssertionPlant<T>
    = werte(checkerBuilder, expected)

@Deprecated("Will be removed with 1.0.0 because it is redundant in terms of `werte(expected, otherExpected)` without adding enough to be a legit alternative.", ReplaceWith("werte(expected, *otherExpected)"))
fun <E, T : Iterable<E>> IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>.objekte(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = werte(expected, *otherExpected)

@Deprecated("Use the extension fun `werte` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("checkerBuilder.werte(expected, *otherExpected)"))
fun <E, T : Iterable<E>> objekte(checkerBuilder: IterableContainsCheckerBuilder<E, T, InAnyOrderSearchBehaviour>, expected: E, vararg otherExpected: E): AssertionPlant<T>
    = checkerBuilder.werte(expected, *otherExpected)

@Deprecated("Use `nullableWerte` instead; will be removed with 1.0.0", ReplaceWith("nullableWerte(expected, *otherExpected)"))
@JvmName("deprecatedWerte")
fun <E : Any?, T : Iterable<E>> IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>.werte(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = nullableWerte(expected, *otherExpected)

@Deprecated("Use the extension fun `eintrag` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("checkerBuilder.eintrag(assertionCreator)"))
fun <E : Any, T : Iterable<E>> eintrag(checkerBuilder: IterableContainsCheckerBuilder<E, T, InAnyOrderSearchBehaviour>, assertionCreator: Assert<E>.() -> Unit): AssertionPlant<T>
    = eintraege(checkerBuilder, assertionCreator)

@JvmName("deprecatedEintrag")
@Deprecated("Use `nullableEintrag` instead; will be removed with 1.0.0", ReplaceWith("nullableEintrag(assertionCreator)"))
fun <E : Any, T : Iterable<E?>> IterableContains.CheckerOption<E?, T, InAnyOrderSearchBehaviour>.eintrag(assertionCreator: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = nullableEintrag(assertionCreator)

@Deprecated("Use the extension fun `nullableEintrag` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("checkerBuilder.nullableEintrag(assertionCreator)"))
fun <E : Any, T : Iterable<E?>> nullableEintrag(checkerBuilder: IterableContainsCheckerBuilder<E?, T, InAnyOrderSearchBehaviour>, assertionCreator: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = nullableEintraege(checkerBuilder, assertionCreator)

@Deprecated("Use the extension fun `eintraege` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("checkerBuilder.eintraege(assertionCreator, *otherAssertionCreators)"))
fun <E : Any, T : Iterable<E>> eintraege(
    checkerBuilder: IterableContainsCheckerBuilder<E, T, InAnyOrderSearchBehaviour>,
    assertionCreator: Assert<E>.() -> Unit,
    vararg otherAssertionCreators: Assert<E>.() -> Unit
): AssertionPlant<T>
    = checkerBuilder.eintraege(assertionCreator, *otherAssertionCreators)

@JvmName("deprecatedEintraege")
@Deprecated("Use `nullableEintraege` instead; will be removed with 1.0.0", ReplaceWith("nullableEintraege(assertionCreator, *otherAssertionCreators)"))
fun <E : Any, T : Iterable<E?>> IterableContains.CheckerOption<E?, T, InAnyOrderSearchBehaviour>.eintraege(
    assertionCreator: (Assert<E>.() -> Unit)?,
    vararg otherAssertionCreators: (Assert<E>.() -> Unit)?
): AssertionPlant<T>
    = nullableEintraege(assertionCreator, *otherAssertionCreators)

@Deprecated("Use the extension fun `nullableEintraege` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("checkerBuilder.nullableEintraege(assertionCreator, *otherAssertionCreators)"))
fun <E : Any, T : Iterable<E?>> nullableEintraege(
    checkerBuilder: IterableContainsCheckerBuilder<E?, T, InAnyOrderSearchBehaviour>,
    assertionCreator: (Assert<E>.() -> Unit)?,
    vararg otherAssertionCreators: (Assert<E>.() -> Unit)?
): AssertionPlant<T>
    = checkerBuilder.nullableEintraege(assertionCreator, *otherAssertionCreators)
