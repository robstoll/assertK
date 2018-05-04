package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.FeatureAssertionGroupType
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.creating.CollectingAssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import kotlin.reflect.*

/**
 * Creates an [AssertionPlant] for the given [property] which eventually adds [AssertionGroup]s with a
 * [FeatureAssertionGroupType], containing the assertions created for the given [property], to the current plant.
 *
 * @return An [AssertionPlant] for the given [property].
 */
fun <T : Any, TProperty : Any> Assert<T>.property(property: KProperty0<TProperty>): AssertionPlant<TProperty>
    = AssertImpl.feature.property(this, property)

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("Use the overload with KProperty1 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")
fun <T : Any, TProperty : Any> CollectingAssertionPlant<T>.property(property: KProperty0<TProperty>): AssertionPlant<TProperty>
    = AssertImpl.feature.property(this, property)

/**
 * Creates an [AssertionPlant] for the given [property] which eventually adds [AssertionGroup]s with a
 * [FeatureAssertionGroupType], containing the assertions created for the given [property], to the current plant.
 *
 * @return An [AssertionPlant] for the given [property].
 */
fun <T : Any, TProperty : Any> Assert<T>.property(property: KProperty1<T, TProperty>): AssertionPlant<TProperty>
    = AssertImpl.feature.property(this, property)


/**
 * Creates an [AssertionPlant] for the given [property] which eventually adds [AssertionGroup]s with a
 * [FeatureAssertionGroupType], containing the assertions created for the given [property], to the current plant --
 * starting with a group consisting of the [Assertion]s created by the [assertionCreator] lambda.
 *
 * @return An [AssertionPlant] for the given [property].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *   (by calling [assertionCreator]) does not hold.
 */
fun <T : Any, TProperty : Any> Assert<T>.property(property: KProperty0<TProperty>, assertionCreator: Assert<TProperty>.() -> Unit): AssertionPlant<TProperty>
    = AssertImpl.feature.property(this, property, assertionCreator)

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("Use the overload with KProperty1 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")
fun <T : Any, TProperty : Any> CollectingAssertionPlant<T>.property(property: KProperty0<TProperty>, assertionCreator: Assert<TProperty>.() -> Unit): AssertionPlant<TProperty>
    = AssertImpl.feature.property(this, property, assertionCreator)

/**
 * Creates an [AssertionPlant] for the given [property] which eventually adds [AssertionGroup]s with a
 * [FeatureAssertionGroupType], containing the assertions created for the given [property], to the current plant --
 * starting with a group consisting of the [Assertion]s created by the [assertionCreator] lambda.
 *
 * @return An [AssertionPlant] for the given [property].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *   (by calling [assertionCreator]) does not hold.
 */
fun <T : Any, TProperty : Any> Assert<T>.property(property: KProperty1<T, TProperty>, assertionCreator: Assert<TProperty>.() -> Unit): AssertionPlant<TProperty>
    = AssertImpl.feature.property(this, property, assertionCreator)


/**
 * Creates an [AssertionPlantNullable] for the given [property] which eventually adds [AssertionGroup]s with a
 * [FeatureAssertionGroupType], containing the assertions created for the given [property], to the current plant.
 *
 * @return An [AssertionPlantNullable] for the given [property].
 */
fun <T : Any, TProperty : Any?> Assert<T>.property(property: KProperty0<TProperty>): AssertionPlantNullable<TProperty>
    = AssertImpl.feature.property(this, property)

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("Use the overload with KProperty1 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")
fun <T : Any, TProperty : Any?> CollectingAssertionPlant<T>.property(property: KProperty0<TProperty>): AssertionPlantNullable<TProperty>
    = AssertImpl.feature.property(this, property)

/**
 * Creates an [AssertionPlantNullable] for the given [property] which eventually adds [AssertionGroup]s with a
 * [FeatureAssertionGroupType], containing the assertions created for the given [property], to the current plant.
 *
 * @return An [AssertionPlantNullable] for the given [property].
 */
fun <T : Any, TProperty : Any?> Assert<T>.property(property: KProperty1<T, TProperty>): AssertionPlantNullable<TProperty>
    = AssertImpl.feature.property(this, property)


// Arg 0 ----------------------------------------------------------------------------

/**
 * Creates an [AssertionPlant], for the value returned by calling [method], which eventually adds [AssertionGroup]s
 * with a [FeatureAssertionGroupType], containing the assertions created for the return value, to the current plant.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 */
fun <T : Any, R : Any> Assert<T>.returnValueOf(method: KFunction0<R>): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf0(this, method)

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("Use the overload with KFunction1 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")
fun <T : Any, R : Any> CollectingAssertionPlant<T>.returnValueOf(method: KFunction0<R>): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf0(this, method)

/**
 * Creates an [AssertionPlant], for the value returned by calling [method], which eventually adds [AssertionGroup]s
 * with a [FeatureAssertionGroupType], containing the assertions created for the return value, to the current plant.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 */
@JvmName("safeReturnValueOf")
fun <T : Any, R : Any> Assert<T>.returnValueOf(method: KFunction1<T, R>): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf0(this, method)


/**
 * Creates an [AssertionPlant], for the value returned by calling [method], which eventually adds
 * [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant -- starting with a group consisting of the [Assertion]s created by the
 * [assertionCreator] lambda.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *   (by calling [assertionCreator]) does not hold.
 */
fun <T : Any, R : Any> Assert<T>.returnValueOf(method: KFunction0<R>, assertionCreator: Assert<R>.() -> Unit): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf0(this, method, assertionCreator)

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("Use the overload with KFunction1 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")
fun <T : Any, R : Any> CollectingAssertionPlant<T>.returnValueOf(method: KFunction0<R>, assertionCreator: Assert<R>.() -> Unit): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf0(this, method, assertionCreator)

/**
 * Creates an [AssertionPlant], for the value returned by calling [method], which eventually adds
 * [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant -- starting with a group consisting of the [Assertion]s created by the
 * [assertionCreator] lambda.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *   (by calling [assertionCreator]) does not hold.
 */
@JvmName("safeReturnValueOf")
fun <T : Any, R : Any> Assert<T>.returnValueOf(method: KFunction1<T, R>, assertionCreator: Assert<R>.() -> Unit): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf0(this, method, assertionCreator)


/**
 * Creates an [AssertionPlantNullable], for the value returned by calling [method], which eventually adds
 * [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant.
 *
 * @return An [AssertionPlantNullable] for the given [property].
 */
fun <T : Any, R : Any?> Assert<T>.returnValueOf(method: KFunction0<R>): AssertionPlantNullable<R>
    = AssertImpl.feature.returnValueOf0(this, method)

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("Use the overload with KFunction1 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")
fun <T : Any, R : Any?> CollectingAssertionPlant<T>.returnValueOf(method: KFunction0<R>): AssertionPlantNullable<R>
    = AssertImpl.feature.returnValueOf0(this, method)

/**
 * Creates an [AssertionPlantNullable], for the value returned by calling [method], which eventually adds
 * [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant.
 *
 * @return An [AssertionPlantNullable] for the given [property].
 */
@JvmName("safeReturnValueOf")
fun <T : Any, R : Any?> Assert<T>.returnValueOf(method: KFunction1<T, R>): AssertionPlantNullable<R>
    = AssertImpl.feature.returnValueOf0(this, method)


// Arg 1 ----------------------------------------------------------------------------

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], which eventually adds
 * [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 */
fun <T : Any, T1, R : Any> Assert<T>.returnValueOf(method: KFunction1<T1, R>, arg1: T1): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf1(this, method, arg1)

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("Use the overload with KFunction2 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")
fun <T : Any, T1, R : Any> CollectingAssertionPlant<T>.returnValueOf(method: KFunction1<T1, R>, arg1: T1): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf1(this, method, arg1)

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], which eventually adds
 * [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 */
@JvmName("safeReturnValueOf")
fun <T : Any, T1, R : Any> Assert<T>.returnValueOf(method: KFunction2<T, T1, R>, arg1: T1): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf1(this, method, arg1)


/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], which eventually adds
 * [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant -- starting with a group consisting of the [Assertion]s created by the
 * [assertionCreator] lambda.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *   (by calling [assertionCreator]) does not hold.
 */
fun <T : Any, T1, R : Any> Assert<T>.returnValueOf(method: KFunction1<T1, R>, arg1: T1, assertionCreator: Assert<R>.() -> Unit): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf1(this, method, arg1, assertionCreator)

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("Use the overload with KFunction2 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")
fun <T : Any, T1, R : Any> CollectingAssertionPlant<T>.returnValueOf(method: KFunction1<T1, R>, arg1: T1, assertionCreator: Assert<R>.() -> Unit): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf1(this, method, arg1, assertionCreator)

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], which eventually adds
 * [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant -- starting with a group consisting of the [Assertion]s created by the
 * [assertionCreator] lambda.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *   (by calling [assertionCreator]) does not hold.
 */
@JvmName("safeReturnValueOf")
fun <T : Any, T1, R : Any> Assert<T>.returnValueOf(method: KFunction2<T, T1, R>, arg1: T1, assertionCreator: Assert<R>.() -> Unit): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf1(this, method, arg1, assertionCreator)


/**
 * Creates an [AssertionPlantNullable], for the value returned by calling [method] with [arg1], which eventually adds
 * [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant.
 *
 * @return An [AssertionPlantNullable] for the given [property].
 */
fun <T : Any, T1, R : Any?> Assert<T>.returnValueOf(method: KFunction1<T1, R>, arg1: T1): AssertionPlantNullable<R>
    = AssertImpl.feature.returnValueOf1(this, method, arg1)

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("Use the overload with KFunction2 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")
fun <T : Any, T1, R : Any?> CollectingAssertionPlant<T>.returnValueOf(method: KFunction1<T1, R>, arg1: T1): AssertionPlantNullable<R>
    = AssertImpl.feature.returnValueOf1(this, method, arg1)

/**
 * Creates an [AssertionPlantNullable], for the value returned by calling [method] with [arg1], which eventually adds
 * [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant.
 *
 * @return An [AssertionPlantNullable] for the given [property].
 */
@JvmName("safeReturnValueOf")
fun <T : Any, T1, R : Any?> Assert<T>.returnValueOf(method: KFunction2<T, T1, R>, arg1: T1): AssertionPlantNullable<R>
    = AssertImpl.feature.returnValueOf1(this, method, arg1)


// Arg 2 ----------------------------------------------------------------------------

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1] and [arg2], which eventually
 * adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 */
fun <T : Any, T1, T2, R : Any> Assert<T>.returnValueOf(method: KFunction2<T1, T2, R>, arg1: T1, arg2: T2): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf2(this, method, arg1, arg2)

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("Use the overload with KFunction3 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")
fun <T : Any, T1, T2, R : Any> CollectingAssertionPlant<T>.returnValueOf(method: KFunction2<T1, T2, R>, arg1: T1, arg2: T2): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf2(this, method, arg1, arg2)

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1] and [arg2], which eventually
 * adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 */
@JvmName("safeReturnValueOf")
fun <T : Any, T1, T2, R : Any> Assert<T>.returnValueOf(method: KFunction3<T, T1, T2, R>, arg1: T1, arg2: T2): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf2(this, method, arg1, arg2)


/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1] and [arg2], which eventually
 * adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant -- starting with a group consisting of the [Assertion]s created by the
 * [assertionCreator] lambda.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *   (by calling [assertionCreator]) does not hold.
 */
fun <T : Any, T1, T2, R : Any> Assert<T>.returnValueOf(method: KFunction2<T1, T2, R>, arg1: T1, arg2: T2, assertionCreator: Assert<R>.() -> Unit): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf2(this, method, arg1, arg2, assertionCreator)

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("Use the overload with KFunction3 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")
fun <T : Any, T1, T2, R : Any> CollectingAssertionPlant<T>.returnValueOf(method: KFunction2<T1, T2, R>, arg1: T1, arg2: T2, assertionCreator: Assert<R>.() -> Unit): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf2(this, method, arg1, arg2, assertionCreator)

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1] and [arg2], which eventually
 * adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant -- starting with a group consisting of the [Assertion]s created by the
 * [assertionCreator] lambda.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *   (by calling [assertionCreator]) does not hold.
 */
@JvmName("safeReturnValueOf")
fun <T : Any, T1, T2, R : Any> Assert<T>.returnValueOf(method: KFunction3<T, T1, T2, R>, arg1: T1, arg2: T2, assertionCreator: Assert<R>.() -> Unit): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf2(this, method, arg1, arg2, assertionCreator)


/**
 * Creates an [AssertionPlantNullable], for the value returned by calling [method] with [arg1] and [arg2], which
 * eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for
 * the return value, to the current plant.
 *
 * @return An [AssertionPlantNullable] for the given [property].
 */
fun <T : Any, T1, T2, R : Any?> Assert<T>.returnValueOf(method: KFunction2<T1, T2, R>, arg1: T1, arg2: T2): AssertionPlantNullable<R>
    = AssertImpl.feature.returnValueOf2(this, method, arg1, arg2)

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("Use the overload with KFunction3 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")
fun <T : Any, T1, T2, R : Any?> CollectingAssertionPlant<T>.returnValueOf(method: KFunction2<T1, T2, R>, arg1: T1, arg2: T2): AssertionPlantNullable<R>
    = AssertImpl.feature.returnValueOf2(this, method, arg1, arg2)

/**
 * Creates an [AssertionPlantNullable], for the value returned by calling [method] with [arg1] and [arg2], which
 * eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for
 * the return value, to the current plant.
 *
 * @return An [AssertionPlantNullable] for the given [property].
 */
@JvmName("safeReturnValueOf")
fun <T : Any, T1, T2, R : Any?> Assert<T>.returnValueOf(method: KFunction3<T, T1, T2, R>, arg1: T1, arg2: T2): AssertionPlantNullable<R>
    = AssertImpl.feature.returnValueOf2(this, method, arg1, arg2)


// Arg 3 ----------------------------------------------------------------------------

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], [arg2] and [arg3],
 * which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created
 * for the return value, to the current plant.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 */
fun <T : Any, T1, T2, T3, R : Any> Assert<T>.returnValueOf(method: KFunction3<T1, T2, T3, R>, arg1: T1, arg2: T2, arg3: T3): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf3(this, method, arg1, arg2, arg3)

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("Use the overload with KFunction4 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")
fun <T : Any, T1, T2, T3, R : Any> CollectingAssertionPlant<T>.returnValueOf(method: KFunction3<T1, T2, T3, R>, arg1: T1, arg2: T2, arg3: T3): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf3(this, method, arg1, arg2, arg3)

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], [arg2] and [arg3],
 * which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created
 * for the return value, to the current plant.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 */
@JvmName("safeReturnValueOf")
fun <T : Any, T1, T2, T3, R : Any> Assert<T>.returnValueOf(method: KFunction4<T, T1, T2, T3, R>, arg1: T1, arg2: T2, arg3: T3): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf3(this, method, arg1, arg2, arg3)


/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], [arg2] and [arg3],
 * which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created
 * for the return value, to the current plant -- starting with a group consisting of the [Assertion]s created by the
 * [assertionCreator] lambda.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *   (by calling [assertionCreator]) does not hold.
 */
fun <T : Any, T1, T2, T3, R : Any> Assert<T>.returnValueOf(method: KFunction3<T1, T2, T3, R>, arg1: T1, arg2: T2, arg3: T3, assertionCreator: Assert<R>.() -> Unit): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf3(this, method, arg1, arg2, arg3, assertionCreator)

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("Use the overload with KFunction4 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")
fun <T : Any, T1, T2, T3, R : Any> CollectingAssertionPlant<T>.returnValueOf(method: KFunction3<T1, T2, T3, R>, arg1: T1, arg2: T2, arg3: T3, assertionCreator: Assert<R>.() -> Unit): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf3(this, method, arg1, arg2, arg3, assertionCreator)

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], [arg2] and [arg3],
 * which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created
 * for the return value, to the current plant -- starting with a group consisting of the [Assertion]s created by the
 * [assertionCreator] lambda.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *   (by calling [assertionCreator]) does not hold.
 */
@JvmName("safeReturnValueOf")
fun <T : Any, T1, T2, T3, R : Any> Assert<T>.returnValueOf(method: KFunction4<T, T1, T2, T3, R>, arg1: T1, arg2: T2, arg3: T3, assertionCreator: Assert<R>.() -> Unit): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf3(this, method, arg1, arg2, arg3, assertionCreator)


/**
 * Creates an [AssertionPlantNullable], for the value returned by calling [method] with [arg1], [arg2] and [arg3],
 * which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for
 * the return value, to the current plant.
 *
 * @return An [AssertionPlantNullable] for the given [property].
 */
fun <T : Any, T1, T2, T3, R : Any?> Assert<T>.returnValueOf(method: KFunction3<T1, T2, T3, R>, arg1: T1, arg2: T2, arg3: T3): AssertionPlantNullable<R>
    = AssertImpl.feature.returnValueOf3(this, method, arg1, arg2, arg3)

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("Use the overload with KFunction4 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")
fun <T : Any, T1, T2, T3, R : Any?> CollectingAssertionPlant<T>.returnValueOf(method: KFunction3<T1, T2, T3, R>, arg1: T1, arg2: T2, arg3: T3): AssertionPlantNullable<R>
    = AssertImpl.feature.returnValueOf3(this, method, arg1, arg2, arg3)

/**
 * Creates an [AssertionPlantNullable], for the value returned by calling [method] with [arg1], [arg2] and [arg3],
 * which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for
 * the return value, to the current plant.
 *
 * @return An [AssertionPlantNullable] for the given [property].
 */
@JvmName("safeReturnValueOf")
fun <T : Any, T1, T2, T3, R : Any?> Assert<T>.returnValueOf(method: KFunction4<T, T1, T2, T3, R>, arg1: T1, arg2: T2, arg3: T3): AssertionPlantNullable<R>
    = AssertImpl.feature.returnValueOf3(this, method, arg1, arg2, arg3)


// Arg 4 ----------------------------------------------------------------------------

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], [arg2], [arg3] and [arg4],
 * which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created
 * for the return value, to the current plant.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 */
fun <T : Any, T1, T2, T3, T4, R : Any> Assert<T>.returnValueOf(method: KFunction4<T1, T2, T3, T4, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf4(this, method, arg1, arg2, arg3, arg4)

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("Use the overload with KFunction5 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")
fun <T : Any, T1, T2, T3, T4, R : Any> CollectingAssertionPlant<T>.returnValueOf(method: KFunction4<T1, T2, T3, T4, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf4(this, method, arg1, arg2, arg3, arg4)

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], [arg2], [arg3] and [arg4],
 * which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created
 * for the return value, to the current plant.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 */
@JvmName("safeReturnValueOf")
fun <T : Any, T1, T2, T3, T4, R : Any> Assert<T>.returnValueOf(method: KFunction5<T, T1, T2, T3, T4, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf4(this, method, arg1, arg2, arg3, arg4)


/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], [arg2], [arg3] and [arg4],
 * which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created
 * for the return value, to the current plant -- starting with a group consisting of the [Assertion]s created by the
 * [assertionCreator] lambda.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *   (by calling [assertionCreator]) does not hold.
 */
fun <T : Any, T1, T2, T3, T4, R : Any> Assert<T>.returnValueOf(method: KFunction4<T1, T2, T3, T4, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, assertionCreator: Assert<R>.() -> Unit): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf4(this, method, arg1, arg2, arg3, arg4, assertionCreator)

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("Use the overload with KFunction5 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")
fun <T : Any, T1, T2, T3, T4, R : Any> CollectingAssertionPlant<T>.returnValueOf(method: KFunction4<T1, T2, T3, T4, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, assertionCreator: Assert<R>.() -> Unit): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf4(this, method, arg1, arg2, arg3, arg4, assertionCreator)

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], [arg2], [arg3] and [arg4],
 * which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created
 * for the return value, to the current plant -- starting with a group consisting of the [Assertion]s created by the
 * [assertionCreator] lambda.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *   (by calling [assertionCreator]) does not hold.
 */
@JvmName("safeReturnValueOf")
fun <T : Any, T1, T2, T3, T4, R : Any> Assert<T>.returnValueOf(method: KFunction5<T, T1, T2, T3, T4, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, assertionCreator: Assert<R>.() -> Unit): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf4(this, method, arg1, arg2, arg3, arg4, assertionCreator)


/**
 * Creates an [AssertionPlantNullable], for the value returned by calling [method] with [arg1], [arg2], [arg3]
 * and [arg4], which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions
 * created for the return value, to the current plant.
 *
 * @return An [AssertionPlantNullable] for the given [property].
 */
fun <T : Any, T1, T2, T3, T4, R : Any?> Assert<T>.returnValueOf(method: KFunction4<T1, T2, T3, T4, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlantNullable<R>
    = AssertImpl.feature.returnValueOf4(this, method, arg1, arg2, arg3, arg4)

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("Use the overload with KFunction5 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")
fun <T : Any, T1, T2, T3, T4, R : Any?> CollectingAssertionPlant<T>.returnValueOf(method: KFunction4<T1, T2, T3, T4, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlantNullable<R>
    = AssertImpl.feature.returnValueOf4(this, method, arg1, arg2, arg3, arg4)

/**
 * Creates an [AssertionPlantNullable], for the value returned by calling [method] with [arg1], [arg2], [arg3]
 * and [arg4], which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions
 * created for the return value, to the current plant.
 *
 * @return An [AssertionPlantNullable] for the given [property].
 */
@JvmName("safeReturnValueOf")
fun <T : Any, T1, T2, T3, T4, R : Any?> Assert<T>.returnValueOf(method: KFunction5<T, T1, T2, T3, T4, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlantNullable<R>
    = AssertImpl.feature.returnValueOf4(this, method, arg1, arg2, arg3, arg4)


// Arg 5 ----------------------------------------------------------------------------

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], [arg2], [arg3], [arg4]
 * and [arg5], which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions
 * created for the return value, to the current plant.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 */
fun <T : Any, T1, T2, T3, T4, T5, R : Any> Assert<T>.returnValueOf(method: KFunction5<T1, T2, T3, T4, T5, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf5(this, method, arg1, arg2, arg3, arg4, arg5)

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("Use the overload with KFunction6 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")
fun <T : Any, T1, T2, T3, T4, T5, R : Any> CollectingAssertionPlant<T>.returnValueOf(method: KFunction5<T1, T2, T3, T4, T5, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf5(this, method, arg1, arg2, arg3, arg4, arg5)

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], [arg2], [arg3], [arg4]
 * and [arg5], which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions
 * created for the return value, to the current plant.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 */
@JvmName("safeReturnValueOf")
fun <T : Any, T1, T2, T3, T4, T5, R : Any> Assert<T>.returnValueOf(method: KFunction6<T, T1, T2, T3, T4, T5, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf5(this, method, arg1, arg2, arg3, arg4, arg5)


/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], [arg2], [arg3], [arg4]
 * and [arg5], which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions
 * created for the return value, to the current plant -- starting with a group consisting of the [Assertion]s created
 * by the [assertionCreator] lambda.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *   (by calling [assertionCreator]) does not hold.
 */
fun <T : Any, T1, T2, T3, T4, T5, R : Any> Assert<T>.returnValueOf(method: KFunction5<T1, T2, T3, T4, T5, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, assertionCreator: Assert<R>.() -> Unit): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf5(this, method, arg1, arg2, arg3, arg4, arg5, assertionCreator)

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("Use the overload with KFunction6 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")
fun <T : Any, T1, T2, T3, T4, T5, R : Any> CollectingAssertionPlant<T>.returnValueOf(method: KFunction5<T1, T2, T3, T4, T5, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, assertionCreator: Assert<R>.() -> Unit): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf5(this, method, arg1, arg2, arg3, arg4, arg5, assertionCreator)

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], [arg2], [arg3], [arg4]
 * and [arg5], which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions
 * created for the return value, to the current plant -- starting with a group consisting of the [Assertion]s created
 * by the [assertionCreator] lambda.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *   (by calling [assertionCreator]) does not hold.
 */
@JvmName("safeReturnValueOf")
fun <T : Any, T1, T2, T3, T4, T5, R : Any> Assert<T>.returnValueOf(method: KFunction6<T, T1, T2, T3, T4, T5, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, assertionCreator: Assert<R>.() -> Unit): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf5(this, method, arg1, arg2, arg3, arg4, arg5, assertionCreator)


/**
 * Creates an [AssertionPlantNullable], for the value returned by calling [method] with [arg1], [arg2], [arg3],
 * [arg4] and [arg5], which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the
 * assertions created for the return value, to the current plant.
 *
 * @return An [AssertionPlantNullable] for the given [property].
 */
fun <T : Any, T1, T2, T3, T4, T5, R : Any?> Assert<T>.returnValueOf(method: KFunction5<T1, T2, T3, T4, T5, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlantNullable<R>
    = AssertImpl.feature.returnValueOf5(this, method, arg1, arg2, arg3, arg4, arg5)

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("Use the overload with KFunction6 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")
fun <T : Any, T1, T2, T3, T4, T5, R : Any?> CollectingAssertionPlant<T>.returnValueOf(method: KFunction5<T1, T2, T3, T4, T5, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlantNullable<R>
    = AssertImpl.feature.returnValueOf5(this, method, arg1, arg2, arg3, arg4, arg5)

/**
 * Creates an [AssertionPlantNullable], for the value returned by calling [method] with [arg1], [arg2], [arg3],
 * [arg4] and [arg5], which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the
 * assertions created for the return value, to the current plant.
 *
 * @return An [AssertionPlantNullable] for the given [property].
 */
@JvmName("safeReturnValueOf")
fun <T : Any, T1, T2, T3, T4, T5, R : Any?> Assert<T>.returnValueOf(method: KFunction6<T, T1, T2, T3, T4, T5, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlantNullable<R>
    = AssertImpl.feature.returnValueOf5(this, method, arg1, arg2, arg3, arg4, arg5)
