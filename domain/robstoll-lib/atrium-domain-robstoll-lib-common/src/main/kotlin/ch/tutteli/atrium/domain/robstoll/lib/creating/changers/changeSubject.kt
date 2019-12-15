package ch.tutteli.atrium.domain.robstoll.lib.creating.changers

import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.core.trueProvider
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.changers.SubjectChanger
import ch.tutteli.atrium.reporting.translating.Translatable

fun <T, R> _changeSubjectUnreported(
    originalAssertionContainer: Expect<T>,
    transformation: (T) -> R
): Expect<R> = coreFactory.newDelegatingReportingAssertionContainer(
    originalAssertionContainer,
    //TODO wrap transformation with error handling. Could be interesting to see the exception in the context of the assertion
    originalAssertionContainer.maybeSubject.map(transformation)
)

fun <T, R> _changeSubject(
    originalAssertionContainer: Expect<T>,
    description: Translatable,
    representation: Any,
    transformation: (T) -> Option<R>,
    failureHandler: SubjectChanger.FailureHandler<T, R>,
    maybeAssertionCreator: Option<Expect<R>.() -> Unit>
): Expect<R> {

    val assertionContainer = coreFactory.newDelegatingReportingAssertionContainer(
        originalAssertionContainer,
        // TODO wrap transformation with error handling. Could be interesting to see the exception in the context of the assertion
        originalAssertionContainer.maybeSubject.flatMap(transformation)
    )
    // we can transform if maybeSubject is None as we have to be in an explaining like context in such a case,
    // even if the transformation cannot be carried out
    val shallTransform =
        originalAssertionContainer.maybeSubject.fold(trueProvider, { assertionContainer.maybeSubject.isDefined() })

    val descriptiveAssertion = AssertImpl.builder.descriptive
        .withTest { shallTransform }
        .withDescriptionAndRepresentation(description, representation)
        .build()

    if (shallTransform) {
        assertionContainer.addAssertion(descriptiveAssertion)
        maybeAssertionCreator.fold({ /*nothing to do */ }) { assertionCreator ->
            assertionContainer.addAssertionsCreatedBy(assertionCreator)
        }
    } else {
        val assertion = failureHandler.createAssertion(
            originalAssertionContainer, descriptiveAssertion, maybeAssertionCreator
        )
        assertionContainer.addAssertion(assertion)
    }
    return assertionContainer
}
