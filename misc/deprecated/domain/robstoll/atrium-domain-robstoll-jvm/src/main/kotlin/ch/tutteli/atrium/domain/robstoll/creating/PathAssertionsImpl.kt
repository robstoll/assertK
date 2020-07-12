//TODO remove file with 1.0.0
@file:Suppress(
    "DEPRECATION",
    /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.PathAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating.*
import java.nio.charset.Charset
import java.nio.file.Path

class PathAssertionsImpl : PathAssertions {
    override fun <T : Path> startsWith(expect: Expect<T>, expected: Path) =
        _startsWith(expect, expected)

    override fun <T : Path> startsNotWith(expect: Expect<T>, expected: Path) =
        _startsNotWith(expect, expected)

    override fun <T : Path> endsWith(expect: Expect<T>, expected: Path) =
        _endsWith(expect, expected)

    override fun <T : Path> endsNotWith(expect: Expect<T>, expected: Path) =
        _endsNotWith(expect, expected)

    override fun <T : Path> exists(expect: Expect<T>) = _exists(expect)

    override fun <T : Path> existsNot(expect: Expect<T>) = _existsNot(expect)

    override fun <T : Path> fileName(expect: Expect<T>) = _fileName(expect)
    override fun <T : Path> extension(expect: Expect<T>) = _extension(expect)
    override fun <T : Path> fileNameWithoutExtension(expect: Expect<T>) =
        _fileNameWithoutExtension(expect)

    override fun <T : Path> parent(expect: Expect<T>) = _parent(expect)
    override fun <T : Path> resolve(expect: Expect<T>, other: String) = _resolve(expect, other)

    override fun <T : Path> isReadable(expect: Expect<T>) = _isReadable(expect)
    override fun <T : Path> isWritable(expect: Expect<T>) = _isWritable(expect)
    override fun <T : Path> isRegularFile(expect: Expect<T>) = _isRegularFile(expect)
    override fun <T : Path> isDirectory(expect: Expect<T>) = _isDirectory(expect)

    override fun <T : Path> hasSameTextualContentAs(expect: Expect<T>, targetPath: Path, sourceCharset: Charset, targetCharset: Charset) =
        _hasSameTextualContentAs(expect, targetPath, sourceCharset, targetCharset)

    override fun <T : Path> hasSameBinaryContentAs(expect: Expect<T>, targetPath: Path) =
        _hasSameBinaryContentAs(expect, targetPath)
}
