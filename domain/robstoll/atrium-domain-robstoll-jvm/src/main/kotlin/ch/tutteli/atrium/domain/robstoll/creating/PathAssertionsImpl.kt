package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.PathAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating._endsWith
import ch.tutteli.atrium.domain.robstoll.lib.creating._endsNotWith
import ch.tutteli.atrium.domain.robstoll.lib.creating._exists
import ch.tutteli.atrium.domain.robstoll.lib.creating._existsNot
import ch.tutteli.atrium.domain.robstoll.lib.creating._fileNameWithoutExtension
import ch.tutteli.atrium.domain.robstoll.lib.creating._parent
import ch.tutteli.atrium.domain.robstoll.lib.creating.*
import java.nio.file.Path

class PathAssertionsImpl : PathAssertions {
    override fun <T : Path> startsWith(assertionContainer: Expect<T>, expected: Path) =
        _startsWith(assertionContainer, expected)

    override fun <T : Path> startsNotWith(assertionContainer: Expect<T>, expected: Path) =
        _startsNotWith(assertionContainer, expected)

    override fun <T : Path> endsWith(assertionContainer: Expect<T>, expected: Path) =
        _endsWith(assertionContainer, expected)

    override fun <T : Path> endsNotWith(assertionContainer: Expect<T>, expected: T) =
        _endsNotWith(assertionContainer, expected)

    override fun <T : Path> exists(assertionContainer: Expect<T>) = _exists(assertionContainer)
    override fun <T : Path> existsNot(assertionContainer: Expect<T>) = _existsNot(assertionContainer)

    override fun <T : Path> fileNameWithoutExtension(assertionContainer: Expect<T>) =
        _fileNameWithoutExtension(assertionContainer)

    override fun <T : Path> parent(assertionContainer: Expect<T>) = _parent(assertionContainer)
}
