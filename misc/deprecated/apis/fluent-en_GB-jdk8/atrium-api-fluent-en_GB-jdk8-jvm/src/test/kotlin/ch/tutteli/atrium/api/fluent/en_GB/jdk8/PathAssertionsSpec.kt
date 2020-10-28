// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.fluent.en_GB.jdk8

import ch.tutteli.atrium.api.fluent.en_GB.hasDirectoryEntry
import ch.tutteli.atrium.api.fluent.en_GB.isAbsolute
import ch.tutteli.atrium.api.fluent.en_GB.isExecutable
import ch.tutteli.atrium.api.fluent.en_GB.isRelative
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
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
    fun0(Expect<Path>::isAbsolute), // checks the new function from fluent-jvm because it is not implemented in fluent-jkd8
    fun0(Expect<Path>::isRelative), // checks the new function from fluent-jvm because it is not implemented in fluent-jkd8
    Expect<Path>::hasDirectoryEntry.name to Companion::hasDirectoryEntrySingle, // checks the new function from fluent-jvm because it is not implemented in fluent-jkd8
    fun2<Path, String, Array<out String>>(Expect<Path>::hasDirectoryEntry), // checks the new function from fluent-jvm because it is not implemented in fluent-jkd8
    fun1(Expect<Path>::hasSameBinaryContentAs),
    fun3(Expect<Path>::hasSameTextualContentAs),
    Expect<Path>::hasSameTextualContentAs.name to Companion::hasSameTextualContentAsDefaultArgs
) {

    companion object {
        private fun hasSameTextualContentAsDefaultArgs(expect: Expect<Path>, targetPath: Path): Expect<Path> =
            expect.hasSameTextualContentAs(targetPath)

        private fun hasDirectoryEntrySingle(expect: Expect<Path>, entry: String) = expect.hasDirectoryEntry(entry)
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
