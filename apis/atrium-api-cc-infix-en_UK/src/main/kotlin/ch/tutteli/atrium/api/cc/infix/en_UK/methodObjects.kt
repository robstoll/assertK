package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.translating.ITranslatable

class DefaultTranslationsOf(val expected: ITranslatable, vararg val otherExpected: ITranslatable)

class Entries<T : Any>(val assertionCreator: IAssertionPlant<T>.() -> Unit, vararg val otherAssertionCreators: IAssertionPlant<T>.() -> Unit)

class Objects<out T>(val expected: T, vararg val otherExpected: T) {
    constructor(values: Values<T>) : this(values.expected, *values.otherExpected)
}

class RegexPatterns(val pattern: String, vararg val otherPatterns: String)
class Values<out T>(val expected: T, vararg val otherExpected: T)
