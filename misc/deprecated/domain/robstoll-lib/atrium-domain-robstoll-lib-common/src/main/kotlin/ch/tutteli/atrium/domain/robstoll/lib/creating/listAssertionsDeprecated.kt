@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
@file:JvmMultifileClass
@file:JvmName("ListAssertionsKt")

package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.creating.CollectingAssertionPlant
import ch.tutteli.atrium.creating.CollectingAssertionPlantNullable
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.feature.extract.FeatureExtractor
import ch.tutteli.atrium.translations.DescriptionListAssertion
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

fun <T : Any> _get(
    plant: AssertionPlant<List<T>>,
    index: Int
): AssertionPlant<T> = extractorForGetCall(index)
    .withParameterObject(createGetParameterObject(plant, index))
    .extract()

fun <T : Any> _get(
    plant: AssertionPlant<List<T>>,
    index: Int,
    assertionCreator: CollectingAssertionPlant<T>.() -> Unit
): Assertion = extractorForGetCall(index)
    .withParameterObject(createGetParameterObject(plant, index))
    .extractAndAssertIt(assertionCreator)

fun <T> _getNullable(
    plant: AssertionPlant<List<T>>,
    index: Int
): AssertionPlantNullable<T> = extractorForGetCall(index)
    .withParameterObjectNullable(createGetParameterObject(plant, index))
    .extract()

fun <T> _getNullable(
    plant: AssertionPlant<List<T>>,
    index: Int,
    assertionCreator: CollectingAssertionPlantNullable<T>.() -> Unit
): Assertion = extractorForGetCall(index)
    .withParameterObjectNullable(createGetParameterObject(plant, index))
    .extractAndAssertIt(assertionCreator)

private fun extractorForGetCall(index: Int) = AssertImpl.feature.extractor.methodCall("get", index)

@Suppress("DEPRECATION")
private fun <T> createGetParameterObject(
    plant: AssertionPlant<List<T>>,
    index: Int
): FeatureExtractor.ParameterObject<List<T>, T> = FeatureExtractor.ParameterObject(
    plant,
    extractionNotSuccessful = DescriptionListAssertion.INDEX_OUT_OF_BOUNDS,
    warningCannotEvaluate = DescriptionListAssertion.CANNOT_EVALUATE_INDEX_OUT_OF_BOUNDS,
    canBeExtracted = { index < it.size },
    featureExtraction = { it[index] }
)
