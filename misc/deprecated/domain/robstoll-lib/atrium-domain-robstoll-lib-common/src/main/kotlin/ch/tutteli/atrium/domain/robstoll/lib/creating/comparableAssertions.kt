package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.translations.DescriptionComparableAssertion.*

fun <T1 : Comparable<T2>, T2 : Any?> _isLessThan(subjectProvider: SubjectProvider<T1>, expected: T2) =
    ExpectImpl.builder.createDescriptive(subjectProvider, IS_LESS_THAN, expected) { it < expected }

fun <T1 : Comparable<T2>, T2 : Any?> _isLessThanOrEqual(subjectProvider: SubjectProvider<T1>, expected: T2) =
    ExpectImpl.builder.createDescriptive(subjectProvider, IS_LESS_THAN_OR_EQUAL, expected) { it <= expected }

fun <T1 : Comparable<T2>, T2 : Any?> _isGreaterThan(subjectProvider: SubjectProvider<T1>, expected: T2) =
    ExpectImpl.builder.createDescriptive(subjectProvider, IS_GREATER_THAN, expected) { it > expected }

fun <T1 : Comparable<T2>, T2 : Any?> _isGreaterThanOrEqual(subjectProvider: SubjectProvider<T1>, expected: T2) =
    ExpectImpl.builder.createDescriptive(subjectProvider, IS_GREATER_THAN_OR_EQUAL, expected) { it >= expected }

fun <T1 : Comparable<T2>, T2 : Any?> _isEqualComparingTo(expect: Expect<T1>, expected: T2) =
    ExpectImpl.builder.createDescriptive(expect, IS_EQUAL, expected) { it.compareTo(expected) == 0 }
