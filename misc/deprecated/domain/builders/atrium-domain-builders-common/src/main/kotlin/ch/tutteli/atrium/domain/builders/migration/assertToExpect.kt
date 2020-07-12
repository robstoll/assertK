// TODO remove file with 1.0.0
@file:Suppress("TYPEALIAS_EXPANSION_DEPRECATION")

package ch.tutteli.atrium.domain.builders.migration

import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.core.getOrElse
import ch.tutteli.atrium.core.newReportingPlantNullable
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.domain.builders.utils.Group
import ch.tutteli.atrium.domain.builders.utils.subExpect
import ch.tutteli.atrium.reporting.SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG_TRANSLATABLE
import kotlin.js.JsName

/**
 * Turns this [Expect] into an [Assert] so that you can use functions which have not yet been migrated to [Expect].
 *
 * Will be removed with 1.0.0
 */
@Deprecated(
    "Switch from Assert to Expect, most likely you only need to add `import ch.tutteli.atrium.api.fluent.en_GB.*` and then you can remove this call. " +
        "This function was introduced in 0.9.0 to ease the migration from Assert to Expect; will be removed with 1.0.0"
)
@Suppress("DEPRECATION", "DeprecatedCallableAddReplaceWith")
@JsName("asAssert")
fun <T : Any> Expect<T>.asAssert(): Assert<T> =
    coreFactory.newReportingPlant(
        SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG_TRANSLATABLE,
        { this.maybeSubject.getOrElse { throw PlantHasNoSubjectException() } },
        coreFactory.newDelegatingAssertionChecker(this)
    )

/**
 * Turns this [Expect] into an [Assert] so that you can use functions which have not yet been migrated to [Expect].
 *
 * Will be removed with 1.0.0
 */
@Deprecated(
    "Switch from Assert to Expect, most likely you only need to add `import ch.tutteli.atrium.api.fluent.en_GB.*` and then you can remove this call. " +
        "This function was introduced in 0.9.0 to ease the migration from Assert to Expect; will be removed with 1.0.0"
)
@Suppress("DEPRECATION", "DeprecatedCallableAddReplaceWith")
fun <T : Any> Expect<T>.asAssert(assertionCreator: Assert<T>.() -> Unit): Assert<T> =
    asAssert().addAssertionsCreatedBy(assertionCreator)

/**
 * Turns this [Expect] into an [AssertionPlantNullable] so that you can use functions
 * which have not yet been migrated to [Expect].
 *
 * Will be removed with 1.0.0
 */
@Deprecated(
    "Switch from Assert to Expect, most likely you only need to add `import ch.tutteli.atrium.api.fluent.en_GB.*` and then you can remove this call. " +
        "This function was introduced in 0.9.0 to ease the migration from Assert to Expect; will be removed with 1.0.0"
)
@Suppress("DEPRECATION", "DeprecatedCallableAddReplaceWith")
fun <T : Any> Expect<T?>.asAssert(): AssertionPlantNullable<T?> =
    coreFactory.newReportingPlantNullable(
        SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG_TRANSLATABLE,
        { this.maybeSubject.getOrElse { throw PlantHasNoSubjectException() } },
        coreFactory.newDelegatingAssertionChecker(this)
    )

@Deprecated(
    "Switch from Assert to Expect, migrate all inner functions first and then remove this call -- this function was introduced in 0.9.0 to ease the migration from Assert to Expect; will be removed with 1.0.0",
    ReplaceWith("group")
)
@Suppress("DEPRECATION")
fun <T : Any> asExpectGroup(group: Group<(Assert<T>.() -> Unit)?>): Group<(Expect<T>.() -> Unit)?> =
    object : Group<(Expect<T>.() -> Unit)?> {
        override fun toList() = group.toList().map { asSubExpect(it) }
    }

@Deprecated(
    "Switch from Assert to Expect, migrate all inner functions first and then remove this call -- this function was introduced in 0.9.0 to ease the migration from Assert to Expect; will be removed with 1.0.0",
    ReplaceWith("assertionCreatorOrNull")
)
@Suppress("DEPRECATION")
fun <T : Any> asSubExpect(
    assertionCreatorOrNull: (Assert<T>.() -> Unit)?
): (Expect<T>.() -> Unit)? = assertionCreatorOrNull?.let { subExpect { asAssert(it) } }

/**
 * Turns [Assert] or [AssertionPlantNullable] into an [Expect] so that you can use new functionality
 * which is not available on [Assert]/[AssertionPlantNullable].
 *
 * Try to switch entirely to [Expect] as [Assert] along with this function will be removed with 1.0.0
 */
//TODO deprecate with 0.14.0
//@Deprecated("Switch from Assert to Expect, this function was introduced in 0.9.0 to ease the migration from Assert to Expect; will be removed with 1.0.0")
@Suppress("DEPRECATION")
fun <T : Any?, A : BaseAssertionPlant<T, *>> A.asExpect(): Expect<T> =
    DelegatingExpect(this, this.maybeSubject)

/**
 * Turns [Assert] or [AssertionPlantNullable] into an [Expect] so that you can use new functionality
 * which is not available on [Assert]/[AssertionPlantNullable].
 *
 * Try to switch entirely to [Expect] as [Assert] along with this function will be removed with 1.0.0
 *
 * @returns The deprecated plant.
 */
//TODO deprecate with 0.14.0
//@Deprecated("Switch from Assert to Expect, this function was introduced in 0.9.0 to ease the migration from Assert to Expect; will be removed with 1.0.0")
@Suppress("DEPRECATION")
fun <T : Any, A : BaseAssertionPlant<T, *>> A.asExpect(assertionCreator: Expect<T>.() -> Unit): A {
    asExpect().addAssertionsCreatedBy(assertionCreator)
    return this
}
