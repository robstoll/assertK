// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.fluent.en_GB.jdk8

import ch.tutteli.atrium.api.fluent.en_GB.isExecutable
import ch.tutteli.atrium.api.fluent.en_GB.isRelative
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun0
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.fun3
import ch.tutteli.atrium.specs.notImplemented
import java.nio.file.Path
import java.nio.file.Paths

class PathAssertionsSpec : ch.tutteli.atrium.specs.integration.PathAssertionsSpec(
    fun0(Expect<Path>::exists),
    fun0(Expect<Path>::existsNot),
    fun1(Expect<Path>::startsWith),
    fun1(Expect<Path>::startsNotWith),
    fun1(Expect<Path>::endsWith),
    fun1(Expect<Path>::endsNotWith),
    fun0(Expect<Path>::isReadable),
    fun0(Expect<Path>::isWritable),
    fun0(Expect<Path>::isExecutable), // checks the new function from fluent-jvm because it is not implemented in fluent-jkd8
    fun0(Expect<Path>::isRegularFile),
    fun0(Expect<Path>::isDirectory),
    fun0(Expect<Path>::isRelative), // checks the new function from fluent-jvm because it is not implemented in fluent-jkd8
    fun1(Expect<Path>::hasSameBinaryContentAs),
    fun3(Expect<Path>::hasSameTextualContentAs),
    fun1(Companion::hasSameTextualContentAsDefaultArgs)
) {

    companion object {
        private fun hasSameTextualContentAsDefaultArgs(expect: Expect<Path>, targetPath: Path): Expect<Path> =
            expect.hasSameTextualContentAs(targetPath)
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        val a1: Expect<DummyPath> = notImplemented()

        a1.exists()
        a1.existsNot()
        a1.startsWith(Paths.get("a"))
        a1.startsNotWith(Paths.get("a"))
        a1.endsWith(Paths.get("a"))
        a1.endsNotWith(Paths.get("a"))
        a1.isReadable()
        a1.isWritable()
        a1.isRegularFile()
        a1.isDirectory()
        a1.hasSameBinaryContentAs(Paths.get("a"))
        a1.hasSameTextualContentAs(Paths.get("a"))
    }
}

class DummyPath(path: Path) : Path by path
