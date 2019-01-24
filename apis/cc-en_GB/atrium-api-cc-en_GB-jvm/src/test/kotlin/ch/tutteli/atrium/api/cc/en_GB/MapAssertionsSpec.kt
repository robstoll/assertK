package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.creating.map.KeyNullableValue
import ch.tutteli.atrium.domain.creating.map.KeyValue
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import kotlin.reflect.KFunction3

class MapAssertionsSpec : ch.tutteli.atrium.spec.integration.MapAssertionsSpec(
    AssertionVerbFactory,
    containsFun.name to Assert<Map<String, Int>>::contains,
    containsNullableFun.name to Assert<Map<String?, Int?>>::containsNullable,
    containsInAnyOrderOnlyFun.name to Assert<Map<String, Int>>::containsInAnyOrderOnly,
    containsInAnyOrderOnlyNullableFun.name to Assert<Map<String?, Int?>>::containsInAnyOrderOnlyNullable,
    "${containsKeyWithValueAssertionsFun.name} ${KeyValue::class.simpleName}" to Assert<Map<String, Int>>::contains,
    "${containsKeyWithNullableValueAssertionsFun.name} ${KeyNullableValue::class.simpleName}" to Assert<Map<String?, Int?>>::containsNullable,
    Assert<Map<String, *>>::containsKey.name to Assert<Map<String, *>>::containsKey,
    "${Assert<Map<String?, *>>::containsKey.name} for nullable" to Assert<Map<String?, *>>::containsKey,
    Assert<Map<String, *>>::containsKey.name to Assert<Map<String, *>>::containsNotKey,
    "${Assert<Map<String?, *>>::containsKey.name} for nullable" to Assert<Map<String?, *>>::containsNotKey,
    Assert<Map<*, *>>::hasSize.name to Assert<Map<*, *>>::hasSize,
    Assert<Map<*, *>>::isEmpty.name to Assert<Map<*, *>>::isEmpty,
    Assert<Map<*, *>>::isNotEmpty.name to Assert<Map<*, *>>::isNotEmpty
){
    companion object {
        private val containsFun : KFunction3<Assert<Map<String, Int>>, Pair<String, Int>, Array<out Pair<String, Int>>, Assert<Map<String, Int>>> = Assert<Map<String, Int>>::contains
        private val containsNullableFun : KFunction3<Assert<Map<String?, Int?>>, Pair<String?, Int?>, Array<out Pair<String?, Int?>>, Assert<Map<String?, Int?>>> = Assert<Map<String?, Int?>>::containsNullable
        private val containsInAnyOrderOnlyFun : KFunction3<Assert<Map<String, Int>>, Pair<String, Int>, Array<out Pair<String, Int>>, Assert<Map<String, Int>>> = Assert<Map<String, Int>>::containsInAnyOrderOnly
        private val containsInAnyOrderOnlyNullableFun : KFunction3<Assert<Map<String?, Int?>>, Pair<String?, Int?>, Array<out Pair<String?, Int?>>, Assert<Map<String?, Int?>>> = Assert<Map<String?, Int?>>::containsInAnyOrderOnlyNullable
        private val containsKeyWithValueAssertionsFun : KFunction3<Assert<Map<String, Int>>, KeyValue<String, Int>, Array<out KeyValue<String, Int>>, Assert<Map<String, Int>>> = Assert<Map<String, Int>>::contains
        private val containsKeyWithNullableValueAssertionsFun : KFunction3<Assert<Map<String?, Int?>>, KeyNullableValue<String?, Int>, Array<out KeyNullableValue<String?, Int>>, Assert<Map<String?, Int?>>> = Assert<Map<String?, Int?>>::containsNullable
    }
}
