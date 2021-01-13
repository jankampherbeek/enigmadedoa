/*
 *
 *  * Jan Kampherbeek, (c) 2021.
 *  * EnigmaDedOA is open source.
 *  * Please check the file copyright.txt in the root of the source for further details.
 *
 */

package com.radixpro.enigma.dedoa.calc

import com.radixpro.enigma.dedoa.core.DateTime
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class JulianCreatorTest {

    @Test
    fun `Calculation of a Julian Day Number should give the expected result`() {
        val dateTime = DateTime(1953, 1, 29, 7, 37, 30)
        val julianCreator = JulianCreator()
        julianCreator.createDateTimeJulian(dateTime) shouldBe(2434406.8177083335 plusOrMinus(0.00000001))
    }
}