package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import kotlin.reflect.KClass

@Deprecated("Use AssertImpl.throwable.thrown.toBe; will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.throwable.thrown.toBe(throwableThrownBuilder, expectedType, assertionCreator)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <TExpected : Throwable> _toThrow(throwableThrownBuilder: ch.tutteli.atrium.domain.robstoll.lib.creating.throwable.thrown.builders.ThrowableThrownBuilder, expectedType: KClass<TExpected>, assertionCreator: AssertionPlant<TExpected>.() -> Unit) {
    @Suppress("DEPRECATION")
    AssertImpl.throwable.thrown.toBe(throwableThrownBuilder, expectedType, assertionCreator)
}
