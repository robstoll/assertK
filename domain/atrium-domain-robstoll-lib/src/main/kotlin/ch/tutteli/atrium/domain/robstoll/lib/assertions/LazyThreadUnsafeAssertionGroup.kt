package ch.tutteli.atrium.domain.robstoll.lib.assertions

import ch.tutteli.atrium.assertions.AssertionGroup

/**
 * Represents an [AssertionGroup] which is evaluated lazily where the lazy loading is not thread safe.
 *
 * @constructor Represents an [AssertionGroup] which is evaluated lazily where the lazy loading is not thread safe.
 * @param assertionCreator The factory function which is used for lazy loading.
 */
class LazyThreadUnsafeAssertionGroup(assertionCreator: () -> AssertionGroup) : AssertionGroup {
    private val assertionGroup by lazy(LazyThreadSafetyMode.NONE) {
        assertionCreator()
    }

    override val name get() = assertionGroup.name
    override val type get() = assertionGroup.type
    override val representation get() = assertionGroup.representation
    override val assertions get() = assertionGroup.assertions
    override fun holds() = assertionGroup.holds()
}
