/*
 *
 *  * Jan Kampherbeek, (c) 2021.
 *  * EnigmaDedOA is open source.
 *  * Please check the file copyright.txt in the root of the source for further details.
 *
 */

package com.radixpro.enigma.dedoa.calc

import com.radixpro.enigma.dedoa.core.CelestialPoints
import com.radixpro.enigma.dedoa.core.SeFlags
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class SeFrontendTest {

    private val delta = 0.00001

    @Test
    fun `Happy flow for a date should result in a valid date`() {
        SeFrontend.isValidDate(2020,1,6).shouldBeTrue()
    }

    @Test
    fun `Wrong input for a date should result in an invalid date`() {
        SeFrontend.isValidDate(2020,1,33).shouldBeFalse()
    }

    @Test
    fun `Correct input for a date of febr 29 in a leapyear should result in a valid date`() {
        SeFrontend.isValidDate(2020,2,29).shouldBeTrue()
    }

    @Test
    fun `Input for a date of febr 29 in a non-leapyear should result in an invalid date`() {
        SeFrontend.isValidDate(2021,2,29).shouldBeFalse()
    }

    @Test
    fun `Calculation of position for Sun should give correct results`() {
        val flags = SeFlags.SWISSEPH.seValue or SeFlags.SPEED.seValue
        val jdUt = 2459220.459137 - 69.4 / (24 * 3600)        // 2021-1-6 23:00 UT
        val result = SeFrontend.getPositionsForCelBody(jdUt, CelestialPoints.SUN.id, flags)
        val expLon = 285.832805465
        val expLat = 0.0001221
        val expSpeedLon = 1.019231558
        result.allPositions[0] shouldBe(expLon plusOrMinus delta)
        result.allPositions[1] shouldBe(expLat plusOrMinus delta)
        result.allPositions[3] shouldBe(expSpeedLon plusOrMinus delta)
    }

}