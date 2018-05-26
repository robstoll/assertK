package ch.tutteli.atrium.assertions.iterable.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.assertions.builders.fixedClaimGroup
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.translations.DescriptionIterableAssertion

@Deprecated("Will be removed with 1.0.0", ReplaceWith(""))
internal fun <E : Any> createExplanatoryAssertions(assertionCreator: (AssertionPlant<E>.() -> Unit)?, list: List<E?>)
    = when {
        list.isNotEmpty() -> collectIterableAssertionsForExplanationWithFirst(assertionCreator, list.firstOrNull { it != null })
        else -> collectIterableAssertionsForExplanation(assertionCreator, null)
    }

@Deprecated("Will be removed with 1.0.0", ReplaceWith(""))
internal fun <E : Any> collectIterableAssertionsForExplanationWithFirst(assertionCreator: (AssertionPlant<E>.() -> Unit)?, first: E?): List<Assertion> {
    return if (first != null) {
        collectIterableAssertionsForExplanation(assertionCreator, first)
    } else {
        collectIterableAssertionsForExplanation(
            DescriptionIterableAssertion.CANNOT_EVALUATE_SUBJECT_ONLY_NULL,
            assertionCreator,
            null)
    }
}

@Deprecated("Will be removed with 1.0.0", ReplaceWith(""))
internal fun <E : Any> collectIterableAssertionsForExplanation(assertionCreator: (AssertionPlant<E>.() -> Unit)?, subject: E?)
    = collectIterableAssertionsForExplanation(
        DescriptionIterableAssertion.CANNOT_EVALUATE_SUBJECT_EMPTY_ITERABLE,
        assertionCreator,
        subject
    )

@Deprecated("Will be removed with 1.0.0", ReplaceWith(""))
internal fun <E : Any> collectIterableAssertionsForExplanation(description: Translatable, assertionCreator: (AssertionPlant<E>.() -> Unit)?, subject: E?)
    =  AssertImpl.collector
        .forExplanation
        .throwIfNoAssertionIsCollected
        .collect(description, assertionCreator, subject)

@Deprecated("Will be removed with 1.0.0", ReplaceWith(""))
internal fun createEntryAssertion(explanatoryAssertions: List<Assertion>, found: Boolean)
    = AssertImpl.builder
        .fixedClaimGroup(DescriptionIterableAssertion.AN_ENTRY_WHICH)
        .withListType
        .withClaim(found)
        .create(AssertImpl.builder.explanatoryGroup.withDefault.create(explanatoryAssertions))

@Deprecated("Will be removed with 1.0.0", ReplaceWith(""))
internal fun <E : Any> allCreatedAssertionsHold(subject: E?, assertionCreator: (AssertionPlant<E>.() -> Unit)?): Boolean
    = when (subject) {
        null -> assertionCreator == null
        else -> assertionCreator != null &&
            coreFactory.newCheckingPlant(subject)
                .addAssertionsCreatedBy(assertionCreator)
                .allAssertionsHold()
    }
