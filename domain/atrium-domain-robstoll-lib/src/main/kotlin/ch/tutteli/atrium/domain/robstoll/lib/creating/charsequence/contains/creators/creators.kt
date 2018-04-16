package ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.creators

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.searchers.IgnoringCaseIndexSearcher
import ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.searchers.IgnoringCaseRegexSearcher
import ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.searchers.IndexSearcher
import ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.searchers.RegexSearcher
import ch.tutteli.atrium.reporting.translating.Translatable

fun <T : CharSequence> _containsValues(
    checkerOption: CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>,
    expected: List<Any>
): AssertionGroup = checkOnlyAllowedTypeAndCreateAssertionGroup(checkerOption, IndexSearcher(), expected)

fun <T : CharSequence> _containsValuesIgnoringCase(
    checkerOption: CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>,
    expected: Any,
    otherExpected: Array<out Any>
): AssertionGroup = checkOnlyAllowedTypeAndCreateAssertionGroup(
    checkerOption,
    IgnoringCaseIndexSearcher(),
    listOf(expected, *otherExpected)
)

private fun <T : CharSequence, S : CharSequenceContains.SearchBehaviour> checkOnlyAllowedTypeAndCreateAssertionGroup(
    checkerOption: CharSequenceContains.CheckerOption<T, S>,
    searcher: CharSequenceContains.Searcher<S>,
    expected: List<Any>
): AssertionGroup {
    require(expected.isNotEmpty()) {
        "You have to specify at least one search criterion for a CharSequence contains assertion"
    }
    expected.forEach {
        require(it is CharSequence || it is Number || it is Char) {
            "Only values of type CharSequence, Number and Char are allowed\nGiven: $it\n" +
                "We provide an API with Any for convenience (so that you can mix String and Int for instance).\n" +
                "Use toString() if you really want to search for its toString()-representation."
        }
    }
    return createAssertionGroup(checkerOption, searcher, expected)
}

fun <T : CharSequence> _containsDefaultTranslationOf(
    checkerOption: CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>,
    expected: Translatable,
    otherExpected: Array<out Translatable>
): AssertionGroup
    = _containsValues(checkerOption, listOf(expected.getDefault(), *mapDefaultTranslations(otherExpected)))

fun <T : CharSequence> _containsDefaultTranslationOfIgnoringCase(
    checkerOption: CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>,
    expected: Translatable,
    otherExpected: Array<out Translatable>
): AssertionGroup
    = _containsValuesIgnoringCase(checkerOption, expected.getDefault(), mapDefaultTranslations(otherExpected))

private fun mapDefaultTranslations(otherExpected: Array<out Translatable>) =
    otherExpected.map { it.getDefault() }.toTypedArray()

fun <T : CharSequence> _containsRegex(
    checkerOption: CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>,
    expected: String,
    otherExpected: Array<out String>
): AssertionGroup
    = createAssertionGroup(checkerOption, RegexSearcher(), expected, otherExpected)

fun <T : CharSequence> _containsRegexIgnoringCase(
    checkerOption: CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>,
    expected: String,
    otherExpected: Array<out String>
): AssertionGroup
    = createAssertionGroup(checkerOption, IgnoringCaseRegexSearcher(), expected, otherExpected)

private fun <T : CharSequence, SC : Any, S : CharSequenceContains.SearchBehaviour> createAssertionGroup(
    checkerOption: CharSequenceContains.CheckerOption<T, S>,
    searcher: CharSequenceContains.Searcher<S>,
    expected: SC,
    otherExpected: Array<out SC>
): AssertionGroup
    = createAssertionGroup(checkerOption, searcher, listOf(expected, *otherExpected))

private fun <T : CharSequence, SC : Any, S : CharSequenceContains.SearchBehaviour> createAssertionGroup(
    checkerOption: CharSequenceContains.CheckerOption<T, S>,
    searcher: CharSequenceContains.Searcher<S>,
    expected: List<SC>
): AssertionGroup {
    val creator = CharSequenceContainsAssertionCreator<T, SC, S>(
        checkerOption.containsBuilder.searchBehaviour,
        searcher,
        checkerOption.checkers
    )
    return creator.createAssertionGroup(checkerOption.containsBuilder.plant, expected)
}
