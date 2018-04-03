package ch.tutteli.atrium.verbs.expect

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.CoreFactory
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.verbs.AssertionVerb.EXPECT
import ch.tutteli.atrium.verbs.AssertionVerb.EXPECT_THROWN
import ch.tutteli.atrium.verbs.AtriumReporterSupplier

/**
 * Creates an [AssertionPlant] for the given [subject].
 *
 * @return The newly created plant.
 *
 * @see CoreFactory.newReportingPlant
 */
fun <T : Any> expect(subject: T)
    = AssertImpl.coreFactory.newReportingPlant(EXPECT, subject, AtriumReporterSupplier.REPORTER)

/**
 * Creates an [AssertionPlant] for the given [subject] and [AssertionPlant.addAssertionsCreatedBy] the
 * given [assertionCreator] lambda where the created [Assertion]s are added as a group and usually (depending on
 * the configured [Reporter]) reported as a whole.
 *
 * @return The newly created plant.
 *
 * @see CoreFactory.newReportingPlantAndAddAssertionsCreatedBy
 */
fun <T : Any> expect(subject: T, assertionCreator: Assert<T>.() -> Unit)
    = AssertImpl.coreFactory.newReportingPlantAndAddAssertionsCreatedBy(EXPECT, subject, AtriumReporterSupplier.REPORTER, assertionCreator)

/**
 * Creates an [AssertionPlantNullable] for the given [subject] which might be `null`.
 *
 * @return The newly created plant.
 *
 * @see CoreFactory.newReportingPlantNullable
 */
fun <T : Any?> expect(subject: T)
    = AssertImpl.coreFactory.newReportingPlantNullable(EXPECT, subject, AtriumReporterSupplier.REPORTER)

/**
 * Creates a [ThrowableThrown.Builder] for the given function [act] which is expected to throw a [Throwable].
 *
 * @return The newly created [ThrowableThrown.Builder].
 */
fun expect(act: () -> Unit)
    = AssertImpl.throwable.thrownBuilder(EXPECT_THROWN, act, AtriumReporterSupplier.REPORTER)
