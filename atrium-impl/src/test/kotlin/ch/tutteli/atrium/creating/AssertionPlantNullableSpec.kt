package ch.tutteli.atrium.creating

import ch.tutteli.atrium.*
import ch.tutteli.atrium.reporting.RawString
import org.jetbrains.spek.api.Spek

class AssertionPlantNullableSpec : Spek({
    describe("subject is null") {
        val i: Int? = null
        group("isNull() does not throw an Exception") {
            check("using the plant directly") {
                val testee = AssertionPlantFactory.newNullable("assert", i, AtriumReporterSupplier.REPORTER)
                testee.isNull()
            }
            check("using `assert`") {
                assert(i).isNull()
            }
        }
    }

    describe("subject is not null") {
        val i: Int? = 1
        group("isNull()  throws an AssertionError") {
            check("using the plant directly") {
                val testee = AssertionPlantFactory.newNullable("assert", i, AtriumReporterSupplier.REPORTER)
                expect {
                    testee.isNull()
                }.toThrow<AssertionError>().and.message.contains("to be", RawString.NULL)
            }
            check("using `assert`") {
                expect {
                    assert(i).isNull()
                }.toThrow<AssertionError>().and.message.contains("to be", RawString.NULL)
            }
        }
    }
})
