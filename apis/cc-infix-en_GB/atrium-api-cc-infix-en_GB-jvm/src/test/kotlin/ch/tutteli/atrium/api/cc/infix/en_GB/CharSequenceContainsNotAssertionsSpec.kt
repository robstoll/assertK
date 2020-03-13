@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.case
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.contain
import ch.tutteli.atrium.creating.Assert

//TODO remove with 1.0.0, no need to migrate to Spek 2
class CharSequenceContainsNotAssertionsSpec : ch.tutteli.atrium.spec.integration.CharSequenceContainsNotAssertionsSpec(
    AssertionVerbFactory,
    getContainsNotTriple(),
    getContainsNotIgnoringCaseTriple(),
    "◆ ", "⚬ "
) {

    companion object : CharSequenceContainsSpecBase() {

        private fun getContainsNotTriple() = Triple(
            notToContain,
            { what: String -> "$notToContain $what" },
            Companion::containsNotFun
        )

        private fun containsNotFun(plant: Assert<CharSequence>, a: Any, aX: Array<out Any>): Assert<CharSequence> {
            return if (aX.isEmpty()) {
                plant notTo contain value a
            } else {
                plant notTo contain the Values(a, *aX)
            }
        }

        private fun getContainsNotIgnoringCaseTriple() = Triple(
            "$notToContain $ignoringCase",
            { what: String -> "$notToContain $ignoringCase $what" },
            Companion::containsNotIgnoringCase
        )

        private fun containsNotIgnoringCase(plant: Assert<CharSequence>, a: Any, aX: Array<out Any>): Assert<CharSequence> {
            return if (aX.isEmpty()) {
                plant notTo contain ignoring case value a
            } else {
                plant notTo contain ignoring case the Values(a, *aX)
            }
        }
    }
}
