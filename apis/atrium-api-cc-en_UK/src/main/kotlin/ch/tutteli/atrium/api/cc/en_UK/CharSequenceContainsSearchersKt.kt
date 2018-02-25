package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsCheckerBuilder
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.CharSequenceContainsIgnoringCaseSearchBehaviour
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.CharSequenceContainsNoOpSearchBehaviour
import ch.tutteli.atrium.reporting.translating.Translatable

@Deprecated("Do not use this class, it is only here to retain binary compatibility (file was renamed to charSequenceContainsCreators), will be removed with 1.0.0")
object CharSequenceContainsSearchersKt {

    @JvmStatic
    @Deprecated("use the extension fun `value` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("checkerBuilder.value(expected)"))
    fun <T : CharSequence> value(checkerBuilder: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsNoOpSearchBehaviour>, expected: Any): AssertionPlant<T>
        = values(checkerBuilder, expected)

    @JvmStatic
    @Deprecated("use the extension fun `the` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("checkerBuilder.values(expected, *otherExpected)"))
    fun <T : CharSequence> values(checkerBuilder: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsNoOpSearchBehaviour>, expected: Any, vararg otherExpected: Any): AssertionPlant<T>
        = checkerBuilder.values(expected, *otherExpected)


    @JvmStatic
    @Deprecated("use the extension fun `value` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("checkerBuilder.value(expected)"))
    fun <T : CharSequence> valueIgnoringCase(checkerBuilder: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsIgnoringCaseSearchBehaviour>, expected: Any): AssertionPlant<T>
        = valuesIgnoringCase(checkerBuilder, expected)

    @JvmStatic
    @Deprecated("use the extension fun `values` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("checkerBuilder.values(expected, *otherExpected)"))
    fun <T : CharSequence> valuesIgnoringCase(checkerBuilder: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsIgnoringCaseSearchBehaviour>, expected: Any, vararg otherExpected: Any): AssertionPlant<T>
        = checkerBuilder.values(expected, *otherExpected)


    @JvmStatic
    @Deprecated("use the extension fun `defaultTranslationOf` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("checkerBuilder.defaultTranslationOf(expected, *otherExpected)"))
    fun <T : CharSequence> defaultTranslationOf(checkerBuilder: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsNoOpSearchBehaviour>, expected: Translatable, vararg otherExpected: Translatable): AssertionPlant<T>
        = checkerBuilder.defaultTranslationOf(expected, *otherExpected)

    @JvmStatic
    @Deprecated("use the extension fun `defaultTranslationOf` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("checkerBuilder.defaultTranslationOf(expected, *otherExpected)"))
    fun <T : CharSequence> defaultTranslationOfIgnoringCase(checkerBuilder: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsIgnoringCaseSearchBehaviour>, expected: Translatable, vararg otherExpected: Translatable): AssertionPlant<T>
        = checkerBuilder.defaultTranslationOf(expected, *otherExpected)


    @JvmStatic
    @Deprecated("use the extension fun `regex` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("checkerBuilder.regex(pattern, *otherPatterns)"))
    fun <T : CharSequence> regex(checkerBuilder: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsNoOpSearchBehaviour>, pattern: String, vararg otherPatterns: String): AssertionPlant<T>
        = checkerBuilder.regex(pattern, *otherPatterns)

    @JvmStatic
    @Deprecated("use the extension fun `regex` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("checkerBuilder.regex(pattern, *otherPatterns)"))
    fun <T : CharSequence> regexIgnoringCase(checkerBuilder: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsIgnoringCaseSearchBehaviour>, pattern: String, vararg otherPatterns: String): AssertionPlant<T>
        = checkerBuilder.regex(pattern, *otherPatterns)
}
