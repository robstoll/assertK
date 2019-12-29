package ch.tutteli.atrium.api.infix.en_GB.creating.list.get.builders.impl

import ch.tutteli.atrium.api.infix.en_GB.creating.list.get.builders.ListGetStep
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl

internal class ListGetStepImpl<E, T: List<E>>(
    override val assertionContainer: Expect<T>,
    override val index: Int
) : ListGetStep<E, T> {

    override infix fun assertIt(assertionCreator: Expect<E>.() -> Unit): Expect<T>
        = assertionContainer.addAssertion(ExpectImpl.list.get(assertionContainer, index).collect(assertionCreator))
}
