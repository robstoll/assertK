package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep

interface IterableLikeAssertions {

    //TODO add with 0.14.0
//    fun <T : Any, E> iterableLikeContainsBuilder(
//        container: AssertionContainer<T>,
//        converter: (T) -> Iterable<E>
//    ): IterableLikeContains.Builder<T, E, NoOpSearchBehaviour>
//
//    fun <T : Any, E> iterableLikeContainsNotBuilder(
//        container: AssertionContainer<T>,
//        converter: (T) -> Iterable<E>
//    ): IterableLikeContains.Builder<T, E, NotSearchBehaviour>

    fun <T : Any, E : Any> all(
        container: AssertionContainer<T>,
        converter: (T) -> Iterable<E?>,
        assertionCreatorOrNull: (Expect<E>.() -> Unit)?
    ): Assertion

    fun <T : Any, E> hasNext(
        container: AssertionContainer<T>,
        converter: (T) -> Iterable<E>
    ): Assertion

    fun <T : Any, E> hasNotNext(
        container: AssertionContainer<T>,
        converter: (T) -> Iterable<E>
    ): Assertion

    fun <T : Any, E : Comparable<E>> min(
        container: AssertionContainer<T>,
        converter: (T) -> Iterable<E>
    ): ExtractedFeaturePostStep<T, E>

    fun <T : Any, E : Comparable<E>> max(
        container: AssertionContainer<T>,
        converter: (T) -> Iterable<E>
    ): ExtractedFeaturePostStep<T, E>
}