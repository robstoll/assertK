package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert

object CollectionAssertionsSpec : ch.tutteli.atrium.spec.assertions.CollectionAssertionsSpec(
    AssertionVerbFactory,
    Assert<List<Int>>::hasSize.name to Assert<List<Int>>::hasSize,
    Assert<List<Int>>::isEmpty.name to Assert<List<Int>>::isEmpty,
    Assert<List<Int>>::isNotEmpty.name to Assert<List<Int>>::isNotEmpty
)
