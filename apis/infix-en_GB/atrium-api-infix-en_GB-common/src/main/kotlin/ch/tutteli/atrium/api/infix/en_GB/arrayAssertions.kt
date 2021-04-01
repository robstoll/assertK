package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.changeSubject
import kotlin.jvm.JvmName

/**
 * Turns `Expect<Array<E>>` into `Expect<List<E>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature { f(it::asList) }` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.ArrayAssertionSamples.asListFeature
 *
 * @since 0.12.0
 */
infix fun <E> Expect<out Array<out E>>.asList(@Suppress("UNUSED_PARAMETER") o: o): Expect<List<E>> =
    _logic.changeSubject.unreported { it.asList() }

/**
 * Expects that the subject of `this` expectation holds all assertions the given [assertionCreator] creates for
 * the subject as [List].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature of({ f(it::asList) }, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.ArrayAssertionSamples.asList
 *
 * @since 0.12.0
 */
infix fun <E> Expect<Array<E>>.asList(assertionCreator: Expect<List<E>>.() -> Unit): Expect<Array<E>> =
    apply { asList(o).addAssertionsCreatedBy(assertionCreator) }

/**
 * Expects that the subject of `this` expectation holds all assertions the given [assertionCreator] creates for
 * the subject as [List].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature of({ f(it::asList) }, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.ArrayAssertionSamples.asListEOut
 *
 * @since 0.12.0
 */
@JvmName("asListEOut")
infix fun <E> Expect<Array<out E>>.asList(assertionCreator: Expect<List<E>>.() -> Unit): Expect<Array<out E>> =
    apply { asList(o).addAssertionsCreatedBy(assertionCreator) }

/**
 * Turns `Expect<CharArray>` into `Expect<List<Byte>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature { f(it::asList) }` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.ArrayAssertionSamples.byteArrAsListFeature
 *
 * @since 0.12.0
 */
@JvmName("byteArrAsList")
infix fun Expect<ByteArray>.asList(@Suppress("UNUSED_PARAMETER") o: o): Expect<List<Byte>> =
    _logic.changeSubject.unreported { it.asList() }

/**
 * Expects that the subject of `this` expectation holds all assertions the given [assertionCreator] creates for
 * the subject as [List].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature of({ f(it::asList) }, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.ArrayAssertionSamples.byteArrAsList
 *
 * @since 0.12.0
 */
@JvmName("byteArrAsList")
infix fun Expect<ByteArray>.asList(assertionCreator: Expect<List<Byte>>.() -> Unit): Expect<ByteArray> =
    apply { asList(o).addAssertionsCreatedBy(assertionCreator) }


/**
 * Turns `Expect<CharArray>` into `Expect<List<Char>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature { f(it::asList) }` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.ArrayAssertionSamples.charArrAsListFeature
 *
 * @since 0.12.0
 */
@JvmName("charArrAsList")
infix fun Expect<CharArray>.asList(@Suppress("UNUSED_PARAMETER") o: o): Expect<List<Char>> =
    _logic.changeSubject.unreported { it.asList() }

/**
 * Expects that the subject of `this` expectation holds all assertions the given [assertionCreator] creates for
 * the subject as [List].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature of({ f(it::asList) }, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.ArrayAssertionSamples.charArrAsList
 *
 * @since 0.12.0
 */
@JvmName("charArrAsList")
infix fun Expect<CharArray>.asList(assertionCreator: Expect<List<Char>>.() -> Unit): Expect<CharArray> =
    apply { asList(o).addAssertionsCreatedBy(assertionCreator) }


/**
 * Turns `Expect<ShortArray>` into `Expect<List<Short>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature { f(it::asList) }` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.ArrayAssertionSamples.shortArrAsListFeature
 *
 * @since 0.12.0
 */
@JvmName("shortArrAsList")
infix fun Expect<ShortArray>.asList(@Suppress("UNUSED_PARAMETER") o: o): Expect<List<Short>> =
    _logic.changeSubject.unreported { it.asList() }

/**
 * Expects that the subject of `this` expectation holds all assertions the given [assertionCreator] creates for
 * the subject as [List].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature of({ f(it::asList) }, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.ArrayAssertionSamples.shortArrAsList
 *
 * @since 0.12.0
 */
@JvmName("shortArrAsList")
infix fun Expect<ShortArray>.asList(assertionCreator: Expect<List<Short>>.() -> Unit): Expect<ShortArray> =
    apply { asList(o).addAssertionsCreatedBy(assertionCreator) }


/**
 * Turns `Expect<IntArray>` into `Expect<List<Int>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature { f(it::asList) }` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.ArrayAssertionSamples.intArrAsListFeature
 *
 * @since 0.12.0
 */
@JvmName("intArrAsList")
infix fun Expect<IntArray>.asList(@Suppress("UNUSED_PARAMETER") o: o): Expect<List<Int>> =
    _logic.changeSubject.unreported { it.asList() }

/**
 * Expects that the subject of `this` expectation holds all assertions the given [assertionCreator] creates for
 * the subject as [List].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature of({ f(it::asList) }, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.ArrayAssertionSamples.intArrAsList
 *
 * @since 0.12.0
 */
@JvmName("intArrAsList")
infix fun Expect<IntArray>.asList(assertionCreator: Expect<List<Int>>.() -> Unit): Expect<IntArray> =
    apply { asList(o).addAssertionsCreatedBy(assertionCreator) }


/**
 * Turns `Expect<LongArray>` into `Expect<List<Double>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature { f(it::asList) }` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.ArrayAssertionSamples.longArrAsListFeature
 *
 * @since 0.12.0
 */
@JvmName("longArrAsList")
infix fun Expect<LongArray>.asList(@Suppress("UNUSED_PARAMETER") o: o): Expect<List<Long>> =
    _logic.changeSubject.unreported { it.asList() }

/**
 * Expects that the subject of `this` expectation holds all assertions the given [assertionCreator] creates for
 * the subject as [List].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature of({ f(it::asList) }, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.ArrayAssertionSamples.longArrAsList
 *
 * @since 0.12.0
 */
@JvmName("longArrAsList")
infix fun Expect<LongArray>.asList(assertionCreator: Expect<List<Long>>.() -> Unit): Expect<LongArray> =
    apply { asList(o).addAssertionsCreatedBy(assertionCreator) }


/**
 * Turns `Expect<FloatArray>` into `Expect<List<Float>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature { f(it::asList) }` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.ArrayAssertionSamples.floatArrAsListFeature
 *
 * @since 0.12.0
 */
@JvmName("floatArrAsList")
infix fun Expect<FloatArray>.asList(@Suppress("UNUSED_PARAMETER") o: o): Expect<List<Float>> =
    _logic.changeSubject.unreported { it.asList() }

/**
 * Expects that the subject of `this` expectation holds all assertions the given [assertionCreator] creates for
 * the subject as [List].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature of({ f(it::asList) }, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.ArrayAssertionSamples.floatArrAsList
 *
 * @since 0.12.0
 */
@JvmName("floatArrAsList")
infix fun Expect<FloatArray>.asList(assertionCreator: Expect<List<Float>>.() -> Unit): Expect<FloatArray> =
    apply { asList(o).addAssertionsCreatedBy(assertionCreator) }


/**
 * Turns `Expect<DoubleArray>` into `Expect<List<Double>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature { f(it::asList) }` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.ArrayAssertionSamples.doubleArrAsListFeature
 *
 * @since 0.12.0
 */
@JvmName("doubleArrAsList")
infix fun Expect<DoubleArray>.asList(@Suppress("UNUSED_PARAMETER") o: o): Expect<List<Double>> =
    _logic.changeSubject.unreported { it.asList() }

/**
 * Expects that the subject of `this` expectation holds all assertions the given [assertionCreator] creates for
 * the subject as [List].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature of({ f(it::asList) }, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.ArrayAssertionSamples.doubleArrAsList
 *
 * @since 0.12.0
 */
@JvmName("doubleArrAsList")
infix fun Expect<DoubleArray>.asList(assertionCreator: Expect<List<Double>>.() -> Unit): Expect<DoubleArray> =
    apply { asList(o).addAssertionsCreatedBy(assertionCreator) }


/**
 * Turns `Expect<BooleanArray>` into `Expect<List<Boolean>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature { f(it::asList) }` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.ArrayAssertionSamples.booleanArrAsListFeature
 *
 * @since 0.12.0
 */
@JvmName("boolArrAsList")
infix fun Expect<BooleanArray>.asList(@Suppress("UNUSED_PARAMETER") o: o): Expect<List<Boolean>> =
    _logic.changeSubject.unreported { it.asList() }

/**
 * Expects that the subject of `this` expectation holds all assertions the given [assertionCreator] creates for
 * the subject as [List].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature of({ f(it::asList) }, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.ArrayAssertionSamples.booleanArrAsList
 *
 * @since 0.12.0
 */
@JvmName("boolArrAsList")
infix fun Expect<BooleanArray>.asList(assertionCreator: Expect<List<Boolean>>.() -> Unit): Expect<BooleanArray> =
    apply { asList(o).addAssertionsCreatedBy(assertionCreator) }
