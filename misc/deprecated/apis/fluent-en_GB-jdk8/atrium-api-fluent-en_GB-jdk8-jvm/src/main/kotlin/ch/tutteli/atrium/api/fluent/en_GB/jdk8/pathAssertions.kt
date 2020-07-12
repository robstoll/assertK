//TODO remove file with 1.0.0
@file:Suppress(
    "DEPRECATION",
    /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)
package ch.tutteli.atrium.api.fluent.en_GB.jdk8

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.path
import java.nio.charset.Charset
import java.nio.file.Path

/**
 * Expects that the subject of the assertion (a [Path]) starts with the [expected] [Path].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
@Deprecated(
    "Use the function from the normal jvm module; the jdk8 extension will be removed with 1.0.0",
    ReplaceWith("this.startsWith(expected)", "ch.tutteli.atrium.api.fluent.en_GB.startsWith")
)
fun <T : Path> Expect<T>.startsWith(expected: Path): Expect<T> =
    addAssertion(ExpectImpl.path.startsWith(this, expected))

/**
 * Expects that the subject of the assertion (a [Path]) does not start with the [expected] [Path].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
@Deprecated(
    "Use the function from the normal jvm module; the jdk8 extension will be removed with 1.0.0",
    ReplaceWith("this.startsNotWith(expected)", "ch.tutteli.atrium.api.fluent.en_GB.startsNotWith")
)
fun <T : Path> Expect<T>.startsNotWith(expected: Path): Expect<T> =
    addAssertion(ExpectImpl.path.startsNotWith(this, expected))

/**
 * Expects that the subject of the assertion (a [Path]) ends with the expected [Path].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
@Deprecated(
    "Use the function from the normal jvm module; the jdk8 extension will be removed with 1.0.0",
    ReplaceWith("this.endsWith(expected)", "ch.tutteli.atrium.api.fluent.en_GB.endsWith")
)
fun <T : Path> Expect<T>.endsWith(expected: Path): Expect<T> =
    addAssertion(ExpectImpl.path.endsWith(this, expected))

/**
 * Expects that the subject of the assertion (a [Path]) does not end with the expected [Path];
 *
 * @param expected The [Path] provided to the assertion
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
@Deprecated(
    "Use the function from the normal jvm module; the jdk8 extension will be removed with 1.0.0",
    ReplaceWith("this.endsNotWith(expected)", "ch.tutteli.atrium.api.fluent.en_GB.endsNotWith")
)
fun <T : Path> Expect<T>.endsNotWith(expected: Path): Expect<T> =
    addAssertion(ExpectImpl.path.endsNotWith(this, expected))

/**
 * Expects that the subject of the assertion (a [Path]) exists;
 * meaning that there is a file system entry at the location the [Path] points to.
 *
 * This matcher _resolves_ symbolic links. Therefore, if a symbolic link exists at the location the subject points to,
 * then the search will continue at that location.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
@Deprecated(
    "Use the function from the normal jvm module; the jdk8 extension will be removed with 1.0.0",
    ReplaceWith("this.exists()", "ch.tutteli.atrium.api.fluent.en_GB.exists")
)
fun <T : Path> Expect<T>.exists(): Expect<T> = addAssertion(ExpectImpl.path.exists(this))

/**
 * Expects that the subject of the assertion (a [Path]) does not exist;
 * meaning that there is no file system entry at the location the [Path] points to.
 *
 * This matcher _resolves_ symbolic links. Therefore, if a symbolic link exists at the location the subject points to,
 * then the search will continue at that location.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
@Deprecated(
    "Use the function from the normal jvm module; the jdk8 extension will be removed with 1.0.0",
    ReplaceWith("this.existsNot()", "ch.tutteli.atrium.api.fluent.en_GB.existsNot")
)
fun <T : Path> Expect<T>.existsNot(): Expect<T> = addAssertion(ExpectImpl.path.existsNot(this))

/**
 * Creates an [Expect] for the property [Path.fileNameAsString][ch.tutteli.niok.fileNameAsString]
 * (provided via [niok](https://github.com/robstoll/niok)) of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @since 0.9.0
 */
@Deprecated(
    "Use the function from the normal jvm module; the jdk8 extension will be removed with 1.0.0",
    ReplaceWith("this.fileName", "ch.tutteli.atrium.api.fluent.en_GB.fileName")
)
val <T : Path> Expect<T>.fileName: Expect<String>
    get() = ExpectImpl.path.fileName(this).getExpectOfFeature()

/**
 * Expects that the property [Path.fileNameAsString][ch.tutteli.niok.fileNameAsString]
 * (provided via [niok](https://github.com/robstoll/niok)) of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of the assertion.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
@Deprecated(
    "Use the function from the normal jvm module; the jdk8 extension will be removed with 1.0.0",
    ReplaceWith("this.fileName(assertionCreator)", "ch.tutteli.atrium.api.fluent.en_GB.fileName")
)
fun <T : Path> Expect<T>.fileName(assertionCreator: Expect<String>.() -> Unit): Expect<T> =
    ExpectImpl.path.fileName(this).addToInitial(assertionCreator)

/**
 * Creates an [Expect] for the property [Path.fileNameWithoutExtension][ch.tutteli.niok.fileNameWithoutExtension]
 * (provided via [niok](https://github.com/robstoll/niok)) of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
@Deprecated(
    "Use the function from the normal jvm module; the jdk8 extension will be removed with 1.0.0",
    ReplaceWith("this.fileNameWithoutExtension", "ch.tutteli.atrium.api.fluent.en_GB.fileNameWithoutExtension")
)
val <T : Path> Expect<T>.fileNameWithoutExtension: Expect<String>
    get() = ExpectImpl.path.fileNameWithoutExtension(this).getExpectOfFeature()

/**
 * Expects that the property [Path.fileNameWithoutExtension][ch.tutteli.niok.fileNameWithoutExtension]
 * (provided via [niok](https://github.com/robstoll/niok))
 * of the subject of the assertion holds all assertions the given [assertionCreator] creates for it
 * and returns an [Expect] for the current subject of the assertion.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
@Deprecated(
    "Use the function from the normal jvm module; the jdk8 extension will be removed with 1.0.0",
    ReplaceWith(
        "this.fileNameWithoutExtension(assertionCreator)",
        "ch.tutteli.atrium.api.fluent.en_GB.fileNameWithoutExtension"
    )
)
fun <T : Path> Expect<T>.fileNameWithoutExtension(assertionCreator: Expect<String>.() -> Unit): Expect<T> =
    ExpectImpl.path.fileNameWithoutExtension(this).addToInitial(assertionCreator)

/**
 * Expects that this [Path] has a [parent][Path.getParent] and creates an [Expect] for it,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
@Deprecated(
    "Use the function from the normal jvm module; the jdk8 extension will be removed with 1.0.0",
    ReplaceWith("this.parent", "ch.tutteli.atrium.api.fluent.en_GB.parent")
)
val <T : Path> Expect<T>.parent: Expect<Path>
    get() = ExpectImpl.path.parent(this).getExpectOfFeature()

/**
 * Expects that this [Path] has a [parent][Path.getParent], that the parent holds all assertions the
 * given [assertionCreator] creates for it and returns an [Expect] for the current subject of the assertion.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
@Deprecated(
    "Use the function from the normal jvm module; the jdk8 extension will be removed with 1.0.0",
    ReplaceWith("this.parent(assertionCreator)", "ch.tutteli.atrium.api.fluent.en_GB.parent")
)
fun <T : Path> Expect<T>.parent(assertionCreator: Expect<Path>.() -> Unit): Expect<T> =
    ExpectImpl.path.parent(this).addToInitial(assertionCreator)

/**
 * Expects that [other] resolves against this [Path] and creates an [Expect] for the resolved [Path]
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.10.0
 */
@Deprecated(
    "Use the function from the normal jvm module; the jdk8 extension will be removed with 1.0.0",
    ReplaceWith("this.resolve(other)", "ch.tutteli.atrium.api.fluent.en_GB.resolve")
)
fun <T : Path> Expect<T>.resolve(other: String): Expect<Path> =
    ExpectImpl.path.resolve(this, other).getExpectOfFeature()

/**
 * Expects that [other] resolves against this [Path], that the resolved [Path] holds all assertions the
 * given [assertionCreator] creates for it and returns an [Expect] for the current subject of the assertion.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.10.0
 */
@Deprecated(
    "Use the function from the normal jvm module; the jdk8 extension will be removed with 1.0.0",
    ReplaceWith("resolve(other, assertionCreator)", "ch.tutteli.atrium.api.fluent.en_GB.resolve")
)
fun <T : Path> Expect<T>.resolve(other: String, assertionCreator: Expect<Path>.() -> Unit): Expect<T> =
    ExpectImpl.path.resolve(this, other).addToInitial(assertionCreator)

/**
 * Expects that the subject of the assertion (a [Path]) is readable;
 * meaning that there is a file system entry at the location the [Path] points to and
 * that the current thread has the permission to read from it.
 *
 * This matcher _resolves_ symbolic links.
 * Therefore, if a symbolic link exists at the location the subject points to,
 * search will continue at the location the link points at.
 *
 * This assertion is not atomic with respect to concurrent file system operations on the paths the assertions works on.
 * Its result, in particular its extended explanations, may be wrong if such concurrent file system operations
 * take place.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
@Deprecated(
    "Use the function from the normal jvm module; the jdk8 extension will be removed with 1.0.0",
    ReplaceWith("this.isReadable()", "ch.tutteli.atrium.api.fluent.en_GB.isReadable")
)
fun <T : Path> Expect<T>.isReadable(): Expect<T> = addAssertion(ExpectImpl.path.isReadable(this))

/**
 * Expects that the subject of the assertion (a [Path]) is writable;
 * meaning that there is a file system entry at the location the [Path] points to and
 * that the current thread has the permission to write to it.
 *
 * This matcher _resolves_ symbolic links.
 * Therefore, if a symbolic link exists at the location the subject points to, search will continue
 * at the location the link points at.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
@Deprecated(
    "Use the function from the normal jvm module; the jdk8 extension will be removed with 1.0.0",
    ReplaceWith("this.isWritable()", "ch.tutteli.atrium.api.fluent.en_GB.isWritable")
)
fun <T : Path> Expect<T>.isWritable(): Expect<T> = addAssertion(ExpectImpl.path.isWritable(this))

/**
 * Expects that the subject of the assertion (a [Path]) is a file;
 * meaning that there is a file system entry at the location the [Path] points to and that is a regular file.
 *
 * This matcher _resolves_ symbolic links.
 * Therefore, if a symbolic link exists at the location the subject points to, search will continue
 * at the location the link points at.
 *
 * This assertion is not atomic with respect to concurrent file system operations on the paths the assertions works on.
 * Its result, in particular its extended explanations, may be wrong if such concurrent file system operations
 * take place.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
@Deprecated(
    "Use the function from the normal jvm module; the jdk8 extension will be removed with 1.0.0",
    ReplaceWith("this.isRegularFile()", "ch.tutteli.atrium.api.fluent.en_GB.isRegularFile")
)
fun <T : Path> Expect<T>.isRegularFile(): Expect<T> = addAssertion(ExpectImpl.path.isRegularFile(this))

/**
 * Expects that the subject of the assertion (a [Path]) is a directory;
 * meaning that there is a file system entry at the location the [Path] points to and that is a directory.
 *
 * This matcher _resolves_ symbolic links.
 * Therefore, if a symbolic link exists at the location the subject points to, search will continue
 * at the location the link points at.
 *
 * This assertion is not atomic with respect to concurrent file system operations on the paths the assertions works on.
 * Its result, in particular its extended explanations, may be wrong if such concurrent file system operations
 * take place.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
@Deprecated(
    "Use the function from the normal jvm module; the jdk8 extension will be removed with 1.0.0",
    ReplaceWith("this.isDirectory()", "ch.tutteli.atrium.api.fluent.en_GB.isDirectory")
)
fun <T : Path> Expect<T>.isDirectory(): Expect<T> = addAssertion(ExpectImpl.path.isDirectory(this))

/**
 * Creates an [Expect] for the property [Path.extension][ch.tutteli.niok.extension]
 * (provided via [niok](https://github.com/robstoll/niok)) of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @since 0.9.0
 */
@Deprecated(
    "Use the function from the normal jvm module; the jdk8 extension will be removed with 1.0.0",
    ReplaceWith("this.extension", "ch.tutteli.atrium.api.fluent.en_GB.extension")
)
val <T : Path> Expect<T>.extension: Expect<String>
    get() = ExpectImpl.path.extension(this).getExpectOfFeature()

/**
 * Expects that the property [Path.extension][ch.tutteli.niok.extension]
 * (provided via [niok](https://github.com/robstoll/niok)) of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of the assertion.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
@Deprecated(
    "Use the function from the normal jvm module; the jdk8 extension will be removed with 1.0.0",
    ReplaceWith("this.extension(assertionCreator)", "ch.tutteli.atrium.api.fluent.en_GB.extension")
)
fun <T : Path> Expect<T>.extension(assertionCreator: Expect<String>.() -> Unit): Expect<T> =
    ExpectImpl.path.extension(this).addToInitial(assertionCreator)

/**
 * Expects that the subject of the assertion (a [Path]) has the same textual content
 * as [targetPath].
 *
 * @param sourceCharset source file encoding - UTF-8 per default.
 * @param targetCharset target file encoding - UTF-8 per default.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.12.0
 */
@Deprecated(
    "Use the function from the normal jvm module; the jdk8 extension will be removed with 1.0.0",
    ReplaceWith(
        "this.hasSameTextualContentAs(targetPath, sourceCharset, targetCharset)",
        "ch.tutteli.atrium.api.fluent.en_GB.hasSameTextualContentAs"
    )
)
fun <T : Path> Expect<T>.hasSameTextualContentAs(
    targetPath: Path,
    sourceCharset: Charset = Charsets.UTF_8,
    targetCharset: Charset = Charsets.UTF_8
): Expect<T> =
    addAssertion(ExpectImpl.path.hasSameTextualContentAs(this, targetPath, sourceCharset, targetCharset))

/**
 * Expects that the subject of the assertion (a [Path]) has the same binary content
 * as [targetPath].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.12.0
 */
@Deprecated(
    "Use the function from the normal jvm module; the jdk8 extension will be removed with 1.0.0",
    ReplaceWith("this.hasSameBinaryContentAs(targetPath)", "ch.tutteli.atrium.api.fluent.en_GB.hasSameBinaryContentAs")
)
fun <T : Path> Expect<T>.hasSameBinaryContentAs(targetPath: Path): Expect<T> =
    addAssertion(ExpectImpl.path.hasSameBinaryContentAs(this, targetPath))
