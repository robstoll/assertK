package ch.tutteli.atrium.api.fluent.en_GB.jdk8

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.property
import java.nio.file.Path

class PathFeatureAssertionsSpec : ch.tutteli.atrium.specs.integration.PathFeatureAssertionsSpec(
    property<Path, Path>(Expect<Path>::parent),
    fun1<Path, Expect<Path>.() -> Unit>(Expect<Path>::parent),
    property<Path, String>(Expect<Path>::fileName),
    fun1<Path, Expect<String>.() -> Unit>(Expect<Path>::fileName),
    property<Path, String>(Expect<Path>::fileNameWithoutExtension),
    fun1<Path, Expect<String>.() -> Unit>(Expect<Path>::fileNameWithoutExtension),
    property<Path, String>(Expect<Path>::extension),
    fun1<Path, Expect<String>.() -> Unit>(Expect<Path>::extension)
) {
    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<Path> = notImplemented()
        var a2: Expect<out Path> = notImplemented()

        a1.parent
        a2.parent
        a1 = a1.parent { }
        a2 = a2.parent { }

        a1.fileName
        a2.fileName
        a1 = a1.fileName { }
        a2 = a2.fileName { }

        a1.fileNameWithoutExtension
        a2.fileNameWithoutExtension
        a1 = a1.fileNameWithoutExtension { }
        a2 = a2.fileNameWithoutExtension { }
    }
}
