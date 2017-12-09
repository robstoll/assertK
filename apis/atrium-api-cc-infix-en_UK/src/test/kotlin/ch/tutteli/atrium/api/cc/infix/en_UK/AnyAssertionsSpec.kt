package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import ch.tutteli.atrium.spec.assertions.AnyAssertionsSpec
import kotlin.reflect.KFunction2

class AnyAssertionsSpec : ch.tutteli.atrium.spec.assertions.AnyAssertionsSpec(
    AssertionVerbFactory,
    AnyAssertionsSpecFunFactory(),
    AnyAssertionsSpecFunFactory(),
    IAssertionPlant<Int>::toBe.name,
    IAssertionPlant<Int>::notToBe.name,
    IAssertionPlant<Int>::isSame.name,
    IAssertionPlant<Int>::isNotSame.name,
    "${IAssertionPlantNullable<Int?>::toBe.name} null" to Companion::isNull,
    getAndImmediatePair(),
    getAndLazyPair()
) {
    class AnyAssertionsSpecFunFactory<T : Any> : AnyAssertionsSpec.IAnyAssertionsSpecFunFactory<T> {
        override val toBeFun: IAssertionPlant<T>.(T) -> IAssertionPlant<T> = { this toBe it }
        override val notToBeFun: IAssertionPlant<T>.(T) -> IAssertionPlant<T> = { this notToBe it }
        override val isSameFun: IAssertionPlant<T>.(T) -> IAssertionPlant<T> = { this isSame it }
        override val isNotSameFun: IAssertionPlant<T>.(T) -> IAssertionPlant<T> = { this isNotSame it }
    }

    companion object {

        private fun isNull(plant: IAssertionPlantNullable<Int?>){
            plant toBe null
        }

        fun getAndImmediatePair(): Pair<String, IAssertionPlant<Int>.() -> IAssertionPlant<Int>>
            = andLazyName() to IAssertionPlant<Int>::and

        private fun andLazyName(): String {
            val f: KFunction2<IAssertionPlant<Int>, IAssertionPlant<Int>.() -> Unit, IAssertionPlant<Int>> = IAssertionPlant<Int>::and
            return f.name
        }

        fun getAndLazyPair(): Pair<String, IAssertionPlant<Int>.(IAssertionPlant<Int>.() -> Unit) -> IAssertionPlant<Int>>
            = andLazyName() to IAssertionPlant<Int>::and
    }
}

/**
 * not supported in the API, we provide it here so that we can still reuse AnyAssertionsSpec
 */
private val IAssertionPlant<Int>.and: IAssertionPlant<Int> get() = this
