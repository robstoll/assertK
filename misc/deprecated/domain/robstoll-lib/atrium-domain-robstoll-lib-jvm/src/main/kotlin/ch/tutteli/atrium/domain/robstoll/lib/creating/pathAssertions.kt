//TODO remove file with 1.0.0
@file:Suppress(/* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */"JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE",
    "DEPRECATION"
)

package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.builders.withFailureHintBasedOnDefinedSubject
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.domain.robstoll.lib.creating.filesystem.*
import ch.tutteli.atrium.domain.robstoll.lib.creating.throwable.thrown.creators.ThrowableThrownFailureHandler
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionBasic.*
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion.ENDS_NOT_WITH
import ch.tutteli.atrium.translations.DescriptionPathAssertion.*
import ch.tutteli.niok.*
import java.io.IOException
import java.nio.charset.Charset
import java.nio.file.AccessDeniedException
import java.nio.file.AccessMode
import java.nio.file.NoSuchFileException
import java.nio.file.Path
import java.nio.file.attribute.*
import java.nio.file.attribute.PosixFilePermission.*
import java.util.*

private const val IO_EXCEPTION_STACK_TRACE_LENGTH = 15

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <T : Path> _startsWith(expect: Expect<T>, expected: Path): Assertion =
    ExpectImpl.builder.createDescriptive(expect, STARTS_WITH, expected) { it.startsWith(expected) }

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <T : Path> _startsNotWith(expect: Expect<T>, expected: Path): Assertion =
    ExpectImpl.builder.createDescriptive(expect, STARTS_NOT_WITH, expected) { !it.startsWith(expected) }

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <T : Path> _endsWith(expect: Expect<T>, expected: Path): Assertion =
    ExpectImpl.builder.createDescriptive(expect, ENDS_WITH, expected) { it.endsWith(expected) }

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <T : Path> _endsNotWith(expect: Expect<T>, expected: Path) =
    ExpectImpl.builder.createDescriptive(expect, ENDS_NOT_WITH, expected) { !it.endsWith(expected) }

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <T : Path> _hasSameTextualContentAs(
    expect: Expect<T>,
    targetPath: Path,
    sourceCharset: Charset,
    targetCharset: Charset
) =
    ExpectImpl.builder.createDescriptive(
        expect,
        TranslatableWithArgs(HAS_SAME_TEXTUAL_CONTENT, sourceCharset, targetCharset),
        targetPath
    ) {
        it.readText(sourceCharset) == targetPath.readText(targetCharset)
    }

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <T : Path> _hasSameBinaryContentAs(expect: Expect<T>, targetPath: Path) =
    ExpectImpl.builder.createDescriptive(expect, HAS_SAME_BINARY_CONTENT, targetPath) {
        it.readAllBytes().contentEquals(targetPath.readAllBytes())
    }

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <T : Path> _exists(expect: Expect<T>): Assertion =
    changeSubjectToFileAttributes(expect) { fileAttributesAssertionContainer ->
        ExpectImpl.builder.descriptive
            .withTest(fileAttributesAssertionContainer) { it is Success }
            .withFailureHintBasedOnDefinedSubject(fileAttributesAssertionContainer) { result ->
                explainForResolvedLink(result.path) { realPath ->
                    val exception = (result as Failure).exception
                    when (exception) {
                        // TODO remove group once https://github.com/robstoll/atrium-roadmap/issues/1 is implemented
                        is NoSuchFileException -> ExpectImpl.builder.explanatoryGroup.withDefaultType.withAssertion(
                            hintForClosestExistingParent(realPath)
                        ).build()
                        else -> hintForIoException(realPath, exception)
                    }
                }
            }
            .withDescriptionAndRepresentation(TO, EXIST)
            .build()
    }

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <T : Path> _existsNot(expect: Expect<T>): Assertion =
    changeSubjectToFileAttributes(expect) { fileAttributesAssertionContainer ->
        ExpectImpl.builder.descriptive
            .withTest(fileAttributesAssertionContainer) { it is Failure && it.exception is NoSuchFileException }
            .withFailureHintBasedOnDefinedSubject(fileAttributesAssertionContainer) { result ->
                explainForResolvedLink(result.path) { realPath ->
                    when (result) {
                        is Success -> describeWas(result.value.fileType)
                        is Failure -> hintForIoException(realPath, result.exception)
                    }
                }
            }
            .withDescriptionAndRepresentation(NOT_TO, EXIST)
            .build()
    }

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <T : Path> _isReadable(expect: Expect<T>): Assertion =
    filePermissionAssertion(expect, READABLE, AccessMode.READ)

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <T : Path> _isWritable(expect: Expect<T>): Assertion =
    filePermissionAssertion(expect, WRITABLE, AccessMode.WRITE)

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <T : Path> _isRegularFile(expect: Expect<T>): Assertion =
    fileTypeAssertion(expect, A_FILE) { it.isRegularFile }

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <T : Path> _isDirectory(expect: Expect<T>): Assertion =
    fileTypeAssertion(expect, A_DIRECTORY) { it.isDirectory }

private fun <T : Path> filePermissionAssertion(
    expect: Expect<T>,
    permissionName: Translatable,
    accessMode: AccessMode
) = ExpectImpl.changeSubject(expect).unreported {
    it.runCatchingIo { fileSystem.provider().checkAccess(it, accessMode) }
}.let { checkAccessResultContainer ->
    ExpectImpl.builder.descriptive
        .withTest(checkAccessResultContainer) { it is Success }
        .withFailureHintBasedOnDefinedSubject(checkAccessResultContainer) { result ->
            explainForResolvedLink(result.path) { realPath ->
                val exception = (result as Failure).exception
                when (exception) {
                    is AccessDeniedException -> findHintForProblemWithParent(realPath)
                        ?: ExpectImpl.builder.explanatoryGroup.withDefaultType
                            .withAssertions(
                                listOf(hintForExistsButMissingPermission(realPath, permissionName))
                                    + hintForOwnersAndPermissions(realPath)
                            )
                            .build()
                    else -> hintForIoException(realPath, exception)
                }
            }
        }
        .withDescriptionAndRepresentation(IS, permissionName)
        .build()
}

private inline fun <T : Path> fileTypeAssertion(
    expect: Expect<T>,
    typeName: Translatable,
    crossinline typeTest: (BasicFileAttributes) -> Boolean
) = changeSubjectToFileAttributes(expect) { fileAttributesAssertionContainer ->
    ExpectImpl.builder.descriptive
        .withTest(fileAttributesAssertionContainer) { it is Success && typeTest(it.value) }
        .withFailureHintBasedOnDefinedSubject(fileAttributesAssertionContainer) { result ->
            explainForResolvedLink(result.path) { realPath ->
                when (result) {
                    is Success -> describeWas(result.value.fileType)
                    is Failure -> hintForIoException(realPath, result.exception)
                }
            }
        }
        .withDescriptionAndRepresentation(IS, typeName)
        .build()
}

private inline fun <T : Path, R> changeSubjectToFileAttributes(
    expect: Expect<T>,
    block: (Expect<IoResult<BasicFileAttributes>>) -> R
): R = ExpectImpl.changeSubject(expect).unreported {
    it.runCatchingIo { readAttributes<BasicFileAttributes>() }
}.let(block)

/**
 * Searches for any problem with a parent directory that is not that the directory does not exist.
 * @return an appropriate hint if a problem with a parent is found that is not that that parent does not exist.
 */
private fun findHintForProblemWithParent(path: Path): Assertion? {
    val absolutePath = path.toAbsolutePath()
    var currentParentPart = absolutePath.root
    for (part in absolutePath) {
        currentParentPart = currentParentPart.resolve(part)
        if (currentParentPart != path) {
            try {
                val attributes = currentParentPart.readAttributes<BasicFileAttributes>()
                if (!attributes.isDirectory) {
                    return hintForParentFailure(
                        currentParentPart,
                        explanation = hintForNotDirectory(attributes.fileType)
                    )
                }
            } catch (e: AccessDeniedException) {
                return hintForParentFailure(
                    currentParentPart.parent,
                    explanation = hintForAccessDenied(currentParentPart.parent)
                )
            } catch (e: IOException) {
                return hintForParentFailure(
                    currentParentPart,
                    explanation = hintForFileSpecificIoException(currentParentPart, e)
                )
            }
        }
    }
    return null
}

private fun hintForParentFailure(parent: Path, explanation: Assertion) =
    ExpectImpl.builder.explanatoryGroup.withDefaultType
        .withAssertions(
            ExpectImpl.builder.descriptive.failing
                .withDescriptionAndRepresentation(FAILURE_DUE_TO_PARENT, parent)
                .build(),
            when (explanation) {
                is AssertionGroup -> explanation
                // TODO remove group once https://github.com/robstoll/atrium-roadmap/issues/1 is implemented
                else -> ExpectImpl.builder.explanatoryGroup.withDefaultType
                    .withAssertion(explanation)
                    .build()
            }
        ).build()

private fun hintForAccessDenied(path: Path): Assertion {
    val failureDueToAccessDeniedHint = ExpectImpl.builder.explanatory
        .withExplanation(FAILURE_DUE_TO_ACCESS_DENIED)
        .build()
    return try {
        val hints = hintForOwnersAndPermissions(path)
        hints.add(0, failureDueToAccessDeniedHint)
        ExpectImpl.builder.explanatoryGroup.withDefaultType
            .withAssertions(hints)
            .build()
    } catch (e: IOException) {
        failureDueToAccessDeniedHint
    }
}

private fun hintForOwnersAndPermissions(path: Path): MutableList<Assertion> {
    val hintList = LinkedList<Assertion>()
    val aclView = path.getFileAttributeView<AclFileAttributeView>()
    if (aclView != null) {
        hintList.add(hintForOwner(aclView.owner.name))
        hintList.addAll(hintsForActualAclPermissions(aclView.acl))
    } else {
        val posixView = path.getFileAttributeView<PosixFileAttributeView>()
        if (posixView != null) {
            val posixAttributes = posixView.readAttributes()
            hintList.add(hintForOwnerAndGroup(posixAttributes.owner().name, posixAttributes.group().name))
            hintList.add(hintForActualPosixPermissions(posixAttributes.permissions()))
        }
    }
    return hintList
}

private fun hintForOwner(owner: String) =
    ExpectImpl.builder.explanatory
        .withExplanation(HINT_OWNER, owner)
        .build()

private fun hintForOwnerAndGroup(owner: String, group: String) =
    ExpectImpl.builder.explanatory
        .withExplanation(HINT_OWNER_AND_GROUP, owner, group)
        .build()

private fun hintsForActualAclPermissions(acl: List<AclEntry>) =
    arrayOf(
        ExpectImpl.builder.explanatory
            .withExplanation(HINT_ACTUAL_ACL_PERMISSIONS)
            .build(),
        ExpectImpl.builder.explanatoryGroup.withDefaultType
            .withAssertions(acl.map(::hintForAclEntry))
            .build()
    )

private fun hintForAclEntry(entry: AclEntry) =
    ExpectImpl.builder.explanatory
        .withExplanation("${entry.type()} ${entry.principal().name}: ${entry.permissions().joinToString()}")
        .build()

private fun hintForActualPosixPermissions(filePermissions: Set<PosixFilePermission>) =
    ExpectImpl.builder.explanatory
        .withExplanation(HINT_ACTUAL_POSIX_PERMISSIONS, formatPosixPermissions(filePermissions))
        .build()

private fun formatPosixPermissions(filePermissions: Set<PosixFilePermission>): StringBuilder {
    val permissionString = StringBuilder(3 * 5 + 2)
    permissionString
        .append("u=")
        .append(toPermissionString(filePermissions, OWNER_READ, OWNER_WRITE, OWNER_EXECUTE))
        .append(' ')
        .append("g=")
        .append(toPermissionString(filePermissions, GROUP_READ, GROUP_WRITE, GROUP_EXECUTE))
        .append(' ')
        .append("o=")
        .append(toPermissionString(filePermissions, OTHERS_READ, OTHERS_WRITE, OTHERS_EXECUTE))
    return permissionString
}

private fun toPermissionString(
    permissions: Set<PosixFilePermission>,
    readPermission: PosixFilePermission,
    writePermission: PosixFilePermission,
    executePermission: PosixFilePermission
): StringBuilder {
    val result = StringBuilder(3)
    if (permissions.contains(readPermission)) result.append('r')
    if (permissions.contains(writePermission)) result.append('w')
    if (permissions.contains(executePermission)) result.append('x')
    return result
}

private fun <T : Path> hintForExistsButMissingPermission(subject: T, permissionName: Translatable) =
    ExpectImpl.builder.explanatory
        .withExplanation(FAILURE_DUE_TO_PERMISSION_FILE_TYPE_HINT, subject.fileType, permissionName)
        .build()

private fun describeWas(actual: Translatable) =
    ExpectImpl.builder.descriptive.failing
        .withDescriptionAndRepresentation(WAS, actual)
        .build()

private fun hintForIoException(path: Path, exception: IOException) = when (exception) {
    is NoSuchFileException -> hintForFileNotFound(path)
    else -> findHintForProblemWithParent(path) ?: hintForFileSpecificIoException(path, exception)
}

private fun hintForFileSpecificIoException(path: Path, exception: IOException) =
    when (exception) {
        is AccessDeniedException -> hintForAccessDenied(path)
        else -> hintForOtherIoException(exception)
    }

private fun hintForFileNotFound(path: Path) =
    ExpectImpl.builder.explanatoryGroup.withDefaultType
        .withAssertions(
            hintForNoSuchFile(),
            hintForClosestExistingParent(path)
        )
        .build()

private fun hintForNoSuchFile() =
    ExpectImpl.builder.explanatory
        .withExplanation(FAILURE_DUE_TO_NO_SUCH_FILE)
        .build()

/**
 * Assumes that we know that [path] does not exist.
 * @return The closest parent directory (including [path] itself) that exists. `null` if there is no such directory.
 */
private fun hintForClosestExistingParent(path: Path): Assertion {
    var testPath = path.toAbsolutePath().parent
    while (testPath.nameCount > 0) {
        try {
            val testPathAttributes = testPath.readAttributes<BasicFileAttributes>()
            return if (testPathAttributes.isDirectory) {
                hintForExistingParentDirectory(testPath)
            } else {
                hintForParentFailure(
                    testPath,
                    explanation = hintForNotDirectory(testPathAttributes.fileType)
                )
            }
        } catch (e: NoSuchFileException) {
            /* continue searching. Any other IOException should not occur because [path] does not exist */
        }
        testPath = testPath.parent
    }
    return hintForExistingParentDirectory(null)
}

private fun hintForExistingParentDirectory(parent: Path?) =
    ExpectImpl.builder.explanatory
        .withExplanation(HINT_CLOSEST_EXISTING_PARENT_DIRECTORY, parent ?: NONE)
        .build()

private fun hintForNotDirectory(actualType: Translatable) =
    ExpectImpl.builder.explanatory
        .withExplanation(FAILURE_DUE_TO_WRONG_FILE_TYPE, actualType, A_DIRECTORY)
        .build()

private fun hintForOtherIoException(exception: IOException) =
    ThrowableThrownFailureHandler.propertiesOfThrowable(
        exception,
        maxStackTrace = IO_EXCEPTION_STACK_TRACE_LENGTH,
        explanation = ExpectImpl.builder.explanatory
            .withExplanation(
                FAILURE_DUE_TO_ACCESS_EXCEPTION,
                exception::class.simpleName ?: exception::class.fullName
            )
            .build()
    )

private val Path.fileType: Translatable
    get() = readAttributes<BasicFileAttributes>().fileType

private val BasicFileAttributes.fileType: Translatable
    get() = when {
        isRegularFile -> A_FILE
        isDirectory -> A_DIRECTORY
        isSymbolicLink -> A_SYMBOLIC_LINK
        else -> A_UNKNOWN_FILE_TYPE
    }

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <T : Path> _fileName(expect: Expect<T>): ExtractedFeaturePostStep<T, String> =
    ExpectImpl.feature.manualFeature(expect, FILE_NAME) { fileName.toString() }

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <T : Path> _fileNameWithoutExtension(expect: Expect<T>): ExtractedFeaturePostStep<T, String> =
    ExpectImpl.feature.manualFeature(expect, FILE_NAME_WITHOUT_EXTENSION) { fileNameWithoutExtension }

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <T : Path> _parent(expect: Expect<T>): ExtractedFeaturePostStep<T, Path> =
    ExpectImpl.feature.extractor(expect)
        .withDescription(PARENT)
        .withRepresentationForFailure(DOES_NOT_HAVE_PARENT)
        .withFeatureExtraction {
            val parent: Path? = it.parent
            if (parent != null) Some(parent) else None
        }
        .withoutOptions()
        .build()

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <T : Path> _resolve(expect: Expect<T>, other: String): ExtractedFeaturePostStep<T, Path> =
    ExpectImpl.feature.f1<T, String, Path>(expect, Path::resolve, other)

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <T : Path> _extension(expect: Expect<T>): ExtractedFeaturePostStep<T, String> =
    ExpectImpl.feature.manualFeature(expect, EXTENSION) { extension }
