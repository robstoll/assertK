package ch.tutteli.atrium.api.infix.en_GB.kotlin_1_3.creating.result

import ch.tutteli.atrium.creating.Expect

/**
 * Parameter object that takes [assertionCreator] which defines assertions for a resulting feature of type [E].
 *
 * Use the function `success { ... }` to create a [SuccessWithCreator].
 *
 * @since 0.11.0
 */
data class SuccessWithCreator<E>(val assertionCreator: Expect<E>.() -> Unit);
