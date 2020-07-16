package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.reporting.AtriumErrorAdjuster

actual class RemoveRunnerAtriumErrorAdjuster : FilterAtriumErrorAdjuster(), AtriumErrorAdjuster {
    override fun adjustStack(stackTrace: Sequence<String>): Sequence<String> = stackTrace.takeWhile {
        !runnerRegex.containsMatchIn(it)
    }

    companion object {
        val runnerRegex: Regex = Regex("[\\\\|/](mocha|jasmine)[\\\\|/]")
    }
}
