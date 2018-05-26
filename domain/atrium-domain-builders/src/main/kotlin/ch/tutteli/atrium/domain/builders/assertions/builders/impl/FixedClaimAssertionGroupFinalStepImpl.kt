package ch.tutteli.atrium.domain.builders.assertions.builders.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.domain.builders.assertions.builders.FixedClaimAssertionGroupFinalStep
import ch.tutteli.atrium.reporting.translating.Translatable

internal class FixedClaimAssertionGroupFinalStepImpl(
    private val groupType: AssertionGroupType,
    private val description: Translatable,
    private val representation: Any,
    private val assertions: List<Assertion>,
    override val holds: Boolean
) : FixedClaimAssertionGroupFinalStep {

    override fun build(): AssertionGroup
        = FixedClaimAssertionGroup(groupType, description, representation, assertions, holds)
}
