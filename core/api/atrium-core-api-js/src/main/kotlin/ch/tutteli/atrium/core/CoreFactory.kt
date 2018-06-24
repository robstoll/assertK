package ch.tutteli.atrium.core

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.checking.AssertionChecker
import ch.tutteli.atrium.creating.AssertionPlantWithCommonFields
import ch.tutteli.atrium.creating.ReportingAssertionPlantNullable
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.translating.Translatable

actual interface CoreFactory : CoreFactoryCommon {

    // we need to define the following methods here so that we can retain binary backward compatibility
    // => Kotlin generates an object called CoreFactory$DefaultImpls due to the optional parameters
    // hence we need to place the methods here and cannot move them to CoreFactoryCommon as well


    /**
     * Creates a [ReportingAssertionPlantNullable] which is the entry point for assertions about nullable types.
     *
     * It creates a [newThrowingAssertionChecker] based on the given [reporter] for assertion checking,
     * uses [subjectProvider] as [AssertionPlantWithCommonFields.CommonFields.subjectProvider] but also as
     * [AssertionPlantWithCommonFields.CommonFields.representationProvider].
     * Notice that [evalOnce] is applied to the given [subjectProvider] to avoid side effects
     * (the provider is most likely called more than once).
     *
     * @param assertionVerb The assertion verb which will be used inter alia in reporting
     *   (see [AssertionPlantWithCommonFields.CommonFields.assertionVerb]).
     * @param subjectProvider Used as [AssertionPlantWithCommonFields.CommonFields.subjectProvider] but
     *   also as [AssertionPlantWithCommonFields.CommonFields.representationProvider].
     * @param reporter The reporter which will be use for a [newThrowingAssertionChecker].
     *
     * @return The newly created assertion plant.
     */
    fun <T : Any?> newReportingPlantNullable(
        assertionVerb: Translatable,
        subjectProvider: () -> T,
        reporter: Reporter,
        nullRepresentation: Any = RawString.NULL
    ): ReportingAssertionPlantNullable<T> = newReportingPlantNullable(
        assertionVerb, subjectProvider, newThrowingAssertionChecker(reporter), nullRepresentation
    )

    /**
     * Creates a [ReportingAssertionPlantNullable] which is the entry point for assertions about nullable types.
     *
     * It uses the given [assertionChecker] for assertion checking, uses [subjectProvider] as
     * [AssertionPlantWithCommonFields.CommonFields.subjectProvider] but also as
     * [AssertionPlantWithCommonFields.CommonFields.representationProvider].
     * Notice that [evalOnce] is applied to the given [subjectProvider] to avoid side effects
     * (the provider is most likely called more than once).
     *
     * @param assertionVerb The assertion verb which will be used inter alia in reporting
     *   (see [AssertionPlantWithCommonFields.CommonFields.assertionVerb]).
     * @param subjectProvider Used as [AssertionPlantWithCommonFields.CommonFields.subjectProvider] but
     *   also as [AssertionPlantWithCommonFields.CommonFields.representationProvider].
     * @param assertionChecker The checker which will be used to check [Assertion]s.
     *   (see [AssertionPlantWithCommonFields.CommonFields.assertionChecker]).
     *
     * @return The newly created assertion plant.
     */
    fun <T : Any?> newReportingPlantNullable(
        assertionVerb: Translatable,
        subjectProvider: () -> T,
        assertionChecker: AssertionChecker,
        nullRepresentation: Any = RawString.NULL
    ): ReportingAssertionPlantNullable<T> {
        val evalOnceSubjectProvider = subjectProvider.evalOnce()
        return newReportingPlantNullable(
            AssertionPlantWithCommonFields.CommonFields(
                assertionVerb,
                evalOnceSubjectProvider,
                evalOnceSubjectProvider,
                assertionChecker,
                nullRepresentation
            )
        )
    }
}
