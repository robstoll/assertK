package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.integration.DiffEqualsCompareTo

object ComparableAssertionsSpec : ch.tutteli.atrium.specs.integration.ComparableAssertionsSpec(
    fun1(Expect<Int>::isLessThan),
    fun1(Expect<Int>::isLessThanOrEqual),
    fun1(Expect<Int>::isGreaterThan),
    fun1(Expect<Int>::isGreaterThanOrEqual),
    fun1(Expect<Int>::isEqualComparingTo),
    fun1(Expect<DiffEqualsCompareTo>::isEqualComparingTo)
)
