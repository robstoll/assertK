package ch.tutteli.atrium.domain.robstoll.lib.creating.basic.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.basic.contains.Contains
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Represents the base class for [Contains.Creator]s which use bare objects as search criteria (matching them
 * with `==`).
 *
 * It provides a template to fulfill the job of creating the sophisticated `contains` [Assertion].
 *
 * @param T The type of the [AssertionPlant.subject][SubjectProvider.subject].
 * @param SC The type of the search criteria.
 * @param S The type of the current [Contains.SearchBehaviour].
 * @param C The type of the checkers in use (typically a sub interface of [Contains.Checker]).
 *
 * @property searchBehaviour The chosen search behaviour.
 *
 * @constructor Represents the base class for [Contains.Creator]s which use bare objects as search criteria (matching them
 *   with `==`).
 * @param searchBehaviour The chosen search behaviour.
 * @param checkers The [Contains.Checker]s which shall be applied to the search result.
 */
abstract class ContainsObjectsAssertionCreator<in T : Any, TT : Any, in SC, S : Contains.SearchBehaviour, C : Contains.Checker>(
    searchBehaviour: S,
    checkers: List<C>
) : ContainsAssertionCreator<T, TT, SC, C>(searchBehaviour, checkers) {

    final override fun searchAndCreateAssertion(
        subjectProvider: SubjectProvider<TT>,
        searchCriterion: SC,
        featureFactory: (Int, Translatable) -> AssertionGroup
    ): AssertionGroup {
        val count = search(subjectProvider, searchCriterion)
        val featureAssertion = featureFactory(count, descriptionNumberOfOccurrences)

        return AssertImpl.builder.customType(getAssertionGroupType())
            .withDescriptionAndRepresentation(groupDescription, searchCriterion)
            .withAssertions(decorateAssertion(subjectProvider, featureAssertion))
            .build()
    }

    /**
     * Provides the translation for `number of occurrences`.
     */
    protected abstract val descriptionNumberOfOccurrences: Translatable

    /**
     * Provides the translation for [AssertionGroup.description]
     */
    protected abstract val groupDescription: Translatable

    /**
     * Provides the [AssertionGroupType] for the resulting [AssertionGroup].
     */
    protected abstract fun getAssertionGroupType(): AssertionGroupType


    /**
     * Searches for something matching the given [searchCriterion] in the subject the given [subjectProvider]
     * provides and returns the number of occurrences.
     *
     * @param subjectProvider The provider of the subject of the assertion in which we shall look for something
     *   matching the given [searchCriterion].
     * @param searchCriterion The search criterion used to determine whether something matches or not.
     *
     * @return The number of times the [searchCriterion] matched in the subject of the assertion.
     */
    protected abstract fun search(subjectProvider: SubjectProvider<TT>, searchCriterion: SC): Int

    /**
     * Either return the given [featureAssertion] as [List] or add further assertions.
     */
    abstract fun decorateAssertion(subjectProvider: SubjectProvider<TT>, featureAssertion: Assertion): List<Assertion>
}
