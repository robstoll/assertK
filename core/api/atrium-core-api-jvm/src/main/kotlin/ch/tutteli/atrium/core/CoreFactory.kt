@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.core

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.reporting.AssertionFormatterFacade
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.translating.Locale
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslationSupplier
import java.util.*
import ch.tutteli.atrium.core.newReportingPlantNullable as newReportingPlantNullableFromCommon

actual interface CoreFactory : CoreFactoryCommon {

    /**
     * Creates a [TranslationSupplier] which is based on properties and is compatible with [ResourceBundle] concerning
     * the structure of the properties files.
     *
     * For instance, the translations for `ch.tutteli.atrium.DescriptionAnyAssertion` and the [Locale] `de_CH` are
     * stored in a properties file named `DescriptionAnyAssertion_de_CH.properties` in the folder `/ch/tutteli/atrium/`.
     * Moreover the files need to be encoded in ISO-8859-1 (restriction to be compatible with [ResourceBundle]).
     *
     * An entry in such a file would look like as follows:
     * `TO_BE = a translation for TO_BE`
     *
     * @return The newly created translation supplier.
     */
    fun newPropertiesBasedTranslationSupplier(): TranslationSupplier

    @Deprecated(
        "Use the overload which expects an AtriumErrorAdjuster in addition; will be removed with 1.0.0",
        ReplaceWith("this.newOnlyFailureReporter(assertionFormatterFacade, this.newNoOpAtriumErrorAdjuster())")
    )
    fun newOnlyFailureReporter(assertionFormatterFacade: AssertionFormatterFacade): Reporter

    // we need to define the following methods here so that we can retain binary backward compatibility
    // => Kotlin generates an object called CoreFactory$DefaultImpls due to the optional parameters
    // hence we need to place the methods here and cannot move them to CoreFactoryCommon as well
    // => will be change with 1.0.0

    /**
     * Creates a [ch.tutteli.atrium.creating.ReportingAssertionPlantNullable] which is the entry point for assertions about nullable types.
     *
     * It creates a [newThrowingAssertionChecker] based on the given [reporter] for assertion checking,
     * uses [subjectProvider] as [ch.tutteli.atrium.creating.AssertionPlantWithCommonFields.CommonFields.subjectProvider] but also as
     * [ch.tutteli.atrium.creating.AssertionPlantWithCommonFields.CommonFields.representationProvider].
     * Notice that [evalOnce] is applied to the given [subjectProvider] to avoid side effects
     * (the provider is most likely called more than once).
     *
     * Notice, this method will be moved to [CoreFactoryCommon] with 1.0.0.
     *
     * @param assertionVerb The assertion verb which will be used inter alia in reporting
     *   (see [ch.tutteli.atrium.creating.AssertionPlantWithCommonFields.CommonFields.assertionVerb]).
     * @param subjectProvider Used as [ch.tutteli.atrium.creating.AssertionPlantWithCommonFields.CommonFields.subjectProvider] but
     *   also as [ch.tutteli.atrium.creating.AssertionPlantWithCommonFields.CommonFields.representationProvider].
     * @param reporter The reporter which will be used for a [newThrowingAssertionChecker].
     *
     * @return The newly created assertion plant.
     */
    @Suppress("DEPRECATION")
    @Deprecated(
        "Switch to Expect instead of Assert, thus use newReportingAssertionContainer instead; will be removed with 1.0.0\"",
        ReplaceWith(
            "ExpectBuilder.forSubject(\n" +
                "// !!!! in case you define an assertion verb function, remove it entirely, this is no longer required !!!! otherwise:\n" +
                "// define the subject here instead of subjectProvider(), for instance just `subject` instead of `{ subject }`\n" +
                "// in case you have a transformation from an existing subject, then use this.maybeSubject.map { transform(it) }\n" +
                "subjectProvider()\n" +
                ")\n" +
                ".withVerb(assertionVerb)\n" +
                ".withOptions { withReporter(reporter) }\n" +
                ".build()",
            "ch.tutteli.atrium.domain.builders.reporting.ExpectBuilder"
        )
    )
    fun <T : Any?> newReportingPlantNullable(
        assertionVerb: Translatable,
        subjectProvider: () -> T,
        reporter: Reporter,
        nullRepresentation: Any = Text.NULL
    ): ch.tutteli.atrium.creating.ReportingAssertionPlantNullable<T> = newReportingPlantNullableFromCommon(
        assertionVerb, subjectProvider, reporter, nullRepresentation
    )

    /**
     * Creates a [ch.tutteli.atrium.creating.ReportingAssertionPlantNullable] which is the entry point for assertions about nullable types.
     *
     * It uses the given [assertionChecker] for assertion checking, uses [subjectProvider] as
     * [ch.tutteli.atrium.creating.AssertionPlantWithCommonFields.CommonFields.subjectProvider] but also as
     * [ch.tutteli.atrium.creating.AssertionPlantWithCommonFields.CommonFields.representationProvider].
     * Notice that [evalOnce] is applied to the given [subjectProvider] to avoid side effects
     * (the provider is most likely called more than once).
     *
     * Notice, this method will be moved to [CoreFactoryCommon] with 1.0.0.
     *
     * @param assertionVerb The assertion verb which will be used inter alia in reporting
     *   (see [ch.tutteli.atrium.creating.AssertionPlantWithCommonFields.CommonFields.assertionVerb]).
     * @param subjectProvider Used as [ch.tutteli.atrium.creating.AssertionPlantWithCommonFields.CommonFields.subjectProvider] but
     *   also as [ch.tutteli.atrium.creating.AssertionPlantWithCommonFields.CommonFields.representationProvider].
     * @param assertionChecker The checker which will be used to check [Assertion]s.
     *   (see [ch.tutteli.atrium.creating.AssertionPlantWithCommonFields.CommonFields.assertionChecker]).
     *
     * @return The newly created assertion plant.
     */
    @Suppress("DEPRECATION")
    @Deprecated(
        "Switch to Expect instead of Assert, thus use newReportingAssertionContainer instead; will be removed with 1.0.0\"",
        ReplaceWith(
            "this.newReportingAssertionContainer(\n" +
                "assertionVerb,\n" +
                "// define the subject here instead of subjectProvider(), for instance just `subject` instead of `{ subject }`\n" +
                "// in case you have a transformation from an existing subject, then use this.maybeSubject.map { transform(it) }\n" +
                "Some(subjectProvider()),\n" +
                "assertionChecker\n" +
                ")",
            "ch.tutteli.atrium.core.coreFactory",
            "ch.tutteli.atrium.core.Some",
            "ch.tutteli.atrium.domain.builders.ExpectImpl"
        )
    )
    fun <T : Any?> newReportingPlantNullable(
        assertionVerb: Translatable,
        subjectProvider: () -> T,
        assertionChecker: ch.tutteli.atrium.checking.AssertionChecker,
        nullRepresentation: Any = Text.NULL
    ): ch.tutteli.atrium.creating.ReportingAssertionPlantNullable<T> = newReportingPlantNullableFromCommon(
        assertionVerb, subjectProvider, assertionChecker, nullRepresentation
    )
}
