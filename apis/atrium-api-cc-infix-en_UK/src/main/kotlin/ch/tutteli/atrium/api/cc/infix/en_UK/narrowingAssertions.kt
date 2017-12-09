package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.assertions._isA
import ch.tutteli.atrium.assertions._isNotNull
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable

/**
 * Makes the assertion that [IAssertionPlantNullable.subject] is not null and if so, uses [assertionCreator]
 * which could create further assertions which are added as a group.
 *
 * @return Notice, that this assertion function cannot provide a fluent API because it depends on whether the first
 * assertion ([IAssertionPlant.subject] is not null) holds or not.
 * Define subsequent assertions via the [assertionCreator] lambda.
 *
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix inline fun <reified T : Any> IAssertionPlantNullable<T?>.notToBeNull(noinline assertionCreator: IAssertionPlant<T>.() -> Unit) {
    _isNotNull(this, assertionCreator)
}

/**
 * Makes the assertion that [IAssertionPlant.subject] *is a* [TSub] (the same type or a sub-type) and if so,
 * uses [assertionCreator] which could create further assertions which are added as a group.
 *
 * @return Notice, that this assertion function cannot provide a fluent API because it depends on whether the first
 * assertion ([IAssertionPlant.subject] *is a* [TSub]) holds or not.
 * Define subsequent assertions via the [assertionCreator] lambda.
 *
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix inline fun <reified TSub : Any> IAssertionPlant<Any>.isA(noinline assertionCreator: IAssertionPlant<TSub>.() -> Unit) {
    _isA(this, assertionCreator)
}
