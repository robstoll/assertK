package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsCheckerBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours.CharSequenceContainsIgnoringCaseSearchBehaviour
import ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours.CharSequenceContainsNoOpSearchBehaviour
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.translating.ITranslatable

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] object shall be searched,
 * using a non disjoint search.
 *
 * Delegates to `values(expected)`.
 *
 * By non disjoint is meant that 'aa' in 'aaaa' is found three times and not only two times.
 *
 * @param expected The object which is expected to be contained within the input of the search.
 *
 * @return The [IAssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> CharSequenceContainsCheckerBuilder<T, CharSequenceContainsNoOpSearchBehaviour>.value(expected: Any): IAssertionPlant<T>
    = values(expected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] object as well as
 * the [otherExpected] objects shall be searched, using a non disjoint search.
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `'a'` and [expected]
 * is defined as `'a'` and one [otherExpected] is defined as `'a'` as well, then both match, even though they match the
 * same sequence in the input of the search. Use an option such as [atLeast], [atMost] and [exactly] to control
 * the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `contains.exactly(2).value('a')`
 * instead of:
 *   `contains.atLeast(1).values('a', 'a')`
 *
 * @param expected The object which is expected to be contained within the input of the search.
 * @param otherExpected Additional objects which are expected to be contained within the input of the search.
 *
 * @return The [IAssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> CharSequenceContainsCheckerBuilder<T, CharSequenceContainsNoOpSearchBehaviour>.values(expected: Any, vararg otherExpected: Any): IAssertionPlant<T>
    = addAssertion(_containsValues(this, expected, otherExpected))


/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] object shall be searched
 * (ignoring case), using a non disjoint search.
 *
 * Delegates to `values(expected)`.
 *
 * By non disjoint is meant that 'aa' in 'aaaa' is found three times and not only two times.
 *
 * @param expected The object which is expected to be contained within the input of the search.
 *
 * @return The [IAssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("valueIgnoringCase")
fun <T : CharSequence> CharSequenceContainsCheckerBuilder<T, CharSequenceContainsIgnoringCaseSearchBehaviour>.value(expected: Any): IAssertionPlant<T>
    = values(expected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] object as well as
 * the [otherExpected] objects shall be searched (ignoring case), using a non disjoint search.
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `'a'` and [expected]
 * is defined as `'a'` and one [otherExpected] is defined as `'a'` as well, then both match, even though they match the
 * same sequence in the input of the search. Use an option such as [atLeast], [atMost] and [exactly] to control
 * the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `contains.ignoringCase.exactly(2).value('a')`
 * instead of:
 *   `contains.ignoringCase.atLeast(1).values('a', 'a')`
 *
 * @param expected The object which is expected to be contained within the input of the search.
 * @param otherExpected Additional objects which are expected to be contained within the input of the search.
 *
 * @return The [IAssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("valuesIgnoringCase")
fun <T : CharSequence> CharSequenceContainsCheckerBuilder<T, CharSequenceContainsIgnoringCaseSearchBehaviour>.values(expected: Any, vararg otherExpected: Any): IAssertionPlant<T>
    = addAssertion(_containsValuesIgnoringCase(this, expected, otherExpected))


/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected]'s
 * [getDefault][ITranslatable.getDefault] representation as well as the [getDefault][ITranslatable.getDefault]
 * representations of the [otherExpected] (if defined) shall be searched, using a non disjoint search.
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `'a'` and the
 * default translation of [expected] is defined as `'a'` and one default translation of the
 * [otherExpected] is defined as `'a'` as well, then both match, even though they match the
 * same sequence in the input of the search. Use an option such as [atLeast], [atMost] and [exactly] to control
 * the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `contains.exactly(2).defaultTranslationOf(IS)`
 * instead of:
 *   `contains.atLeast(1).defaultTranslationOf(IS, IS)`
 *
 * @return The [IAssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> CharSequenceContainsCheckerBuilder<T, CharSequenceContainsNoOpSearchBehaviour>.defaultTranslationOf(expected: ITranslatable, vararg otherExpected: ITranslatable): IAssertionPlant<T>
    = addAssertion(_containsDefaultTranslationOf(this, expected, otherExpected))

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected]'s
 * [getDefault][ITranslatable.getDefault] representation as well as the [getDefault][ITranslatable.getDefault]
 * representations of the [otherExpected] (if defined) shall be searched (ignoring case), using a non disjoint search.
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `'a'` and the
 * default translation of [expected] is defined as `'a'` and one default translation of the
 * [otherExpected] is defined as `'a'` as well, then both match, even though they match the
 * same sequence in the input of the search. Use an option such as [atLeast], [atMost] and [exactly] to control
 * the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `contains.exactly(2).defaultTranslationOf(IS)`
 * instead of:
 *   `contains.atLeast(1).defaultTranslationOf(IS, IS)`
 *
 * @return The [IAssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("defaultTranslationOfIgnoringCase")
fun <T : CharSequence> CharSequenceContainsCheckerBuilder<T, CharSequenceContainsIgnoringCaseSearchBehaviour>.defaultTranslationOf(expected: ITranslatable, vararg otherExpected: ITranslatable): IAssertionPlant<T>
    = addAssertion(_containsDefaultTranslationOfIgnoringCase(this, expected, otherExpected))


/**
 * Finishes the specification of the sophisticated `contains` assertion where the given regular expression [pattern]
 * as well as the [otherPatterns] are expected to have a match, using a non disjoint search.
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `'ab'` and [pattern]
 * is defined as `'a(b)?'` and one of the [otherPatterns] is defined as `'a(b)?'` as well, then both match, even though
 * they match the same sequence in the input of the search. Use an option such as [atLeast], [atMost] and [exactly] to
 * control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `contains.exactly(2).regex('a(b)?')`
 * instead of:
 *   `contains.atLeast(1).regex('a(b)?', 'a(b)?')`
 *
 * @param pattern The pattern which is expected to have a match against the input of the search.
 * @param otherPatterns Additional patterns which are expected to have a match against the input of the search.
 *
 * @return The [IAssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> CharSequenceContainsCheckerBuilder<T, CharSequenceContainsNoOpSearchBehaviour>.regex(pattern: String, vararg otherPatterns: String): IAssertionPlant<T>
    = addAssertion(_containsRegex(this, pattern, otherPatterns))

/**
 * Finishes the specification of the sophisticated `contains` assertion where the given regular expression [pattern]
 * as well as the [otherPatterns] are expected to have a match (ignoring case), using a non disjoint search.
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `'ab'` and [pattern]
 * is defined as `'a(b)?'` and one of the [otherPatterns] is defined as `'a(b)?'` as well, then both match, even though
 * they match the same sequence in the input of the search. Use an option such as [atLeast], [atMost] and [exactly] to
 * control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `contains.ignoringCase.exactly(2).regex('a(b)?')`
 * instead of:
 *   `contains.ignoringCase.atLeast(1).regex('a(b)?', 'a(b)?')`
 *
 * @param pattern The pattern which is expected to have a match against the input of the search.
 * @param otherPatterns Additional patterns which are expected to have a match against the input of the search.
 *
 * @return The [IAssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("regexIgnoringCase")
fun <T : CharSequence> CharSequenceContainsCheckerBuilder<T, CharSequenceContainsIgnoringCaseSearchBehaviour>.regex(pattern: String, vararg otherPatterns: String): IAssertionPlant<T>
    = addAssertion(_containsRegexIgnoringCase(this, pattern, otherPatterns))
