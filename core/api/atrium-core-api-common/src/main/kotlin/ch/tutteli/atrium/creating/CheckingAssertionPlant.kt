package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion

/**
 * Represents a plant for [Assertion]s and offers the possibility to check whether [allAssertionsHold] which have been
 * [added][addAssertion] to this plant (since the last check).
 *
 * In contrast to [ReportingAssertionPlant], this plant does not offer error reporting capabilities but merely checks
 * whether the added [Assertion]s hold.
 *
 * @param T The type of the [subject] of this [AssertionPlant].
 */
interface CheckingAssertionPlant<out T : Any> : AssertionPlant<T> {

    override fun addAssertionsCreatedBy(assertionCreator: AssertionPlant<T>.() -> Unit): CheckingAssertionPlant<T>

    /**
     * Checks whether the newly [added][addAssertion] [Assertion]s hold.
     *
     * Calling this method more than once should not re-check previously [added][addAssertion] [Assertion]s.
     * In contrast to [ReportingAssertionPlant], this method should not report or throw an exception
     * if an assertion does not hold. It merely states whether the newly [added][addAssertion] [Assertion]s hold.
     *
     * However, it should throw an [IllegalStateException] in case no new assertions have been added to this plant.
     * Particularly after one called [allAssertionsHold] and a second call happens without adding an additional
     * assertion in between.
     *
     * @return `true` if the [added][addAssertion] [Assertion]s hold; `false` otherwise.
     *
     * @throws IllegalStateException in case no new assertions have been added to this plant.
     */
    fun allAssertionsHold(): Boolean
}
