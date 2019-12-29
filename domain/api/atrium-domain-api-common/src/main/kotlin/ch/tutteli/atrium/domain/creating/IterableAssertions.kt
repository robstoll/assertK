package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.NotSearchBehaviour

/**
 * The access point to an implementation of [IterableAssertions].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
val iterableAssertions by lazy { loadSingleService(IterableAssertions::class) }


/**
 * Defines the minimum set of assertion functions and builders applicable to [Iterable],
 * which an implementation of the domain of Atrium has to provide.
 */
interface IterableAssertions {
    fun <E, T : Iterable<E>> containsBuilder(subjectProvider: SubjectProvider<T>): IterableContains.Builder<E, T, NoOpSearchBehaviour>
    fun <E, T : Iterable<E>> containsNotBuilder(subjectProvider: SubjectProvider<T>): IterableContains.Builder<E, T, NotSearchBehaviour>

    fun <E : Any, T : Iterable<E?>> all(
        assertionContainer: Expect<T>,
        assertionCreator: (Expect<E>.() -> Unit)?
    ): Assertion

    fun <E, T : Iterable<E>> hasNext(expect: Expect<T>): Assertion
    fun <E, T : Iterable<E>> hasNotNext(expect: Expect<T>): Assertion

    fun <E : Comparable<E>, T : Iterable<E>> min(assertionContainer: Expect<T>): ExtractedFeaturePostStep<T, E>
    fun <E : Comparable<E>, T : Iterable<E>> max(assertionContainer: Expect<T>): ExtractedFeaturePostStep<T, E>

    @Deprecated("Switch from Assert to Expect; will be removed with 1.0.0")
    fun <E : Any> all(plant: AssertionPlant<Iterable<E?>>, assertionCreator: (AssertionPlant<E>.() -> Unit)?): Assertion
}
