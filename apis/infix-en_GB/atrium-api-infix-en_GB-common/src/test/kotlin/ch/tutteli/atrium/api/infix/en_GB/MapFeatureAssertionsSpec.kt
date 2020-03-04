package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.testutils.WithAsciiReporter
import kotlin.jvm.JvmName

class MapFeatureAssertionsSpec : ch.tutteli.atrium.specs.integration.MapFeatureAssertionsSpec(
    property<Map<String, Int>, Set<String>>(Expect<Map<String, Int>>::keys),
    fun1<Map<String, Int>, Expect<Set<String>>.() -> Unit>(Expect<Map<String, Int>>::keys),
    property<Map<String, Int>, Collection<Int>>(Expect<Map<String, Int>>::values),
    fun1<Map<String, Int>, Expect<Collection<Int>>.() -> Unit>(Expect<Map<String, Int>>::values),
    feature1<Map<String, Int>, String, Int>(Expect<Map<String, Int>>::getExisting),
    fun2<Map<String, Int>, String, Expect<Int>.() -> Unit>(::getExisting),
    feature1<Map<String?, Int?>, String?, Int?>(Expect<Map<String?, Int?>>::getExisting).withNullableSuffix(),
    fun2<Map<String?, Int?>, String?, Expect<Int?>.() -> Unit>(::getExisting).withNullableSuffix()
) {
    companion object : WithAsciiReporter()

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<Map<String, Int>> = notImplemented()
        var a2: Expect<Map<out String, Int>> = notImplemented()
        var a3: Expect<Map<String?, Int?>> = notImplemented()
        var star: Expect<Map<*, *>> = notImplemented()

        //TODO ideally this would not work as the map has not defined the key to be out
        a1 getExisting 1
        a2 getExisting 1
        a3 getExisting null as String?
        star getExisting "a"

        a1 = a1 getExisting key("a") { }
        a2 = a2 getExisting key(1) { }
        a3 = a3 getExisting key(null) { }
        star = star getExisting key("a") { }
    }
}

private fun getExisting(
    expect: Expect<Map<String, Int>>,
    key: String,
    assertionCreator: Expect<Int>.() -> Unit
): Expect<Map<String, Int>> = expect getExisting key(key) { assertionCreator() }

@JvmName("getExistingNullable")
private fun getExisting(
    expect: Expect<Map<String?, Int?>>,
    key: String?,
    assertionCreator: Expect<Int?>.() -> Unit
): Expect<Map<String?, Int?>> = expect getExisting key(key) { assertionCreator() }
