package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.api.cc.infix.en_UK.creating.charsequence.contains.builders.ContainsNotCheckerBuilderImpl
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsBuilder
import ch.tutteli.atrium.creating.AssertImpl
import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.CharSequenceContainsIgnoringCaseSearchBehaviour
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.CharSequenceContainsNoOpSearchBehaviour
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.CharSequenceContainsNotSearchBehaviour
import ch.tutteli.atrium.api.cc.infix.en_UK.assertions.charsequence.contains.builders.CharSequenceContainsNotCheckerBuilder as DeprecatedNotCheckerBuilder

/**
 * Defines that the decoration behaviour `ignore case` shall be applied to this sophisticated `contains` assertion.
 *
 * @param case Has to be `case`.
 *
 * @return The newly created builder.
 */
infix fun <T : CharSequence> CharSequenceContains.Builder<T, CharSequenceContainsNoOpSearchBehaviour>.ignoring(@Suppress("UNUSED_PARAMETER") case: case)
    = AssertImpl.charSequence.contains.searchBehaviours.ignoringCase(this)

@Deprecated("use the extension fun `ignoring` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("builder ignoring case"))
fun <T : CharSequence> ignoring(builder: CharSequenceContainsBuilder<T, CharSequenceContainsNoOpSearchBehaviour>, @Suppress("UNUSED_PARAMETER") case: case): CharSequenceContainsBuilder<T, CharSequenceContainsIgnoringCaseSearchBehaviour>
    = CharSequenceContainsBuilder(builder.plant, (builder ignoring case).searchBehaviour)


/**
 * Defines that the decoration behaviour `ignore case` shall be applied to this sophisticated `contains not` assertion.
 *
 * @param case Has to be `case`.
 *
 * @return The newly created builder.
 */
infix fun <T : CharSequence> ContainsNotCheckerBuilderImpl<T, CharSequenceContainsNotSearchBehaviour>.ignoring(@Suppress("UNUSED_PARAMETER") case: case)
    = ContainsNotCheckerBuilderImpl(containsBuilder ignoring case)

@Deprecated("use the extension fun `ignoring` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("builder ignoring case"))
fun <T : CharSequence> DeprecatedNotCheckerBuilder<T, CharSequenceContainsNotSearchBehaviour>.ignoring(@Suppress("UNUSED_PARAMETER") case: case): DeprecatedNotCheckerBuilder<T, CharSequenceContainsIgnoringCaseSearchBehaviour>
    = DeprecatedNotCheckerBuilder(containsBuilder ignoring case)
