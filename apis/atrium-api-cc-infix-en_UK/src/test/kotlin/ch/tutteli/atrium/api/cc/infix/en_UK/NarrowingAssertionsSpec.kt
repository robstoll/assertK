package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import kotlin.reflect.KFunction
import kotlin.reflect.KFunction2


class NarrowingAssertionsSpec : ch.tutteli.atrium.spec.assertions.NarrowingAssertionsSpec(
    AssertionVerbFactory,
    getIsNotNullPair(),
    Companion::isNotNullLess,
    Companion::isNotNullGreaterAndLess,
    getNameIsA(),
    Companion::isAInt,
    Companion::isAString,
    Companion::isACharSequence,
    Companion::isASubType,
    Companion::isAIntLess
) {
    companion object {

        private inline fun <reified T : Any> isNotNull(): KFunction2<IAssertionPlantNullable<T?>, IAssertionPlant<T>.() -> Unit, Unit>
            = IAssertionPlantNullable<T?>::notToBeNull

        private fun getIsNotNullPair() = isNotNull<Int>().name to isNotNull<Int>()

        private fun isNotNullLess(plant: IAssertionPlantNullable<Int?>, number: Int)
            = plant notToBeNull { isLessThan(number) }

        private fun isNotNullGreaterAndLess(plant: IAssertionPlantNullable<Int?>, lowerBound:Int, upperBound: Int)
            = plant notToBeNull { isGreaterThan(lowerBound); isLessThan(upperBound) }


        private fun getNameIsA(): String {
            val f: IAssertionPlant<Any>.(IAssertionPlant<Any>.() -> Unit) -> Unit = IAssertionPlant<Any>::isA
            return (f as KFunction<*>).name
        }

        private inline fun <reified TSub : Any> isA(plant: IAssertionPlant<Any>, noinline assertionCreator: IAssertionPlant<TSub>.() -> Unit) {
            plant isA(assertionCreator)
        }

        //TODO get rid of different overloads as soon as https://youtrack.jetbrains.com/issue/KT-19884 is fixed
        private fun isAInt(plant: IAssertionPlant<Any>, assertionCreator: IAssertionPlant<Int>.() -> Unit)
            = isA(plant, assertionCreator)

        private fun isAString(plant: IAssertionPlant<Any>, assertionCreator: IAssertionPlant<String>.() -> Unit)
            = isA(plant, assertionCreator)

        private fun isACharSequence(plant: IAssertionPlant<Any>, assertionCreator: IAssertionPlant<CharSequence>.() -> Unit)
            = isA(plant, assertionCreator)

        private fun isASubType(plant: IAssertionPlant<Any>, assertionCreator: IAssertionPlant<SubType>.() -> Unit)
            = isA(plant, assertionCreator)

        private fun isAIntLess(plant: IAssertionPlant<Number>, number: Int)
            //TODO change to infix as soon as https://youtrack.jetbrains.com/issue/KT-21593 is fixed
            = plant.isA<Int> { isLessThan(number) }
    }
}
