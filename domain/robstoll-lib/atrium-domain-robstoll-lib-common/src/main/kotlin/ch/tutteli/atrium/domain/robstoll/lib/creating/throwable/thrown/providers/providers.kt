@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)

package ch.tutteli.atrium.domain.robstoll.lib.creating.throwable.thrown.providers

import ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.reporting.translating.Translatable

@Suppress("DeprecatedCallableAddReplaceWith", "DEPRECATION")
@Deprecated("Will be removed with 1.0.0")
fun _translatableBased(translatable: Translatable): ThrowableThrown.AbsentThrowableMessageProvider =
    AbsentThrowableMessageProvider(translatable)
