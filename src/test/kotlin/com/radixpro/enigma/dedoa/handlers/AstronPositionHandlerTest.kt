/*
 *
 *  * Jan Kampherbeek, (c) 2021.
 *  * EnigmaDedOA is open source.
 *  * Please check the file copyright.txt in the root of the source for further details.
 *
 */

package com.radixpro.enigma.dedoa.handlers

import com.radixpro.enigma.dedoa.core.CelestialPoints
import com.radixpro.enigma.dedoa.core.SeFlags
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe
import org.junit.Before
import org.junit.jupiter.api.Test

internal class AstronPositionHandlerTest {

    private val delta = 0.00001
    private var handler = AstronPositionHandler()

    @Before
    fun setUp() {
        handler = AstronPositionHandler()
    }


    @Test
    fun `Calculation of a single position for a specific time should give the correct result`() {
        val flags = SeFlags.SWISSEPH.seValue or SeFlags.SPEED.seValue
        val jdUt = 2459220.459137 - 69.4 / (24 * 3600)        // 2021-1-6 23:00 UT
        val point = CelestialPoints.SUN
        val result = handler.calcSinglePosition4Point(point, jdUt, flags)
        val expLon = 285.832805465
        val expSpeedLon = 1.019231558
        result.pos.mainPos shouldBe(expLon plusOrMinus delta)
        result.pos.speed shouldBe(expSpeedLon plusOrMinus delta)
        result.point shouldBe point
    }

    @Test
    fun `Calculation of mulltiple position4Point for a specific time should give the correct result`() {
        val flags = SeFlags.SWISSEPH.seValue or SeFlags.SPEED.seValue
        val jdUt = 2459220.459137 - 69.4 / (24 * 3600)        // 2021-1-6 23:00 UT
        val expLon = 285.832805465
        val expSpeedLon = 1.019231558
        val points: List<CelestialPoints> = listOf(CelestialPoints.SUN, CelestialPoints.MOON)
        val result = handler.calcPosition4MultiplePoints(points, jdUt, flags)
        result.size shouldBe 2
        result[1].point shouldBe CelestialPoints.MOON
        result[0].pos.mainPos shouldBe(expLon plusOrMinus delta)
        result[0].pos.speed shouldBe(expSpeedLon plusOrMinus delta)
    }

    @Test
    fun `Calculation of a range of positions for a specific point should give the correct results`() {
        val flags = SeFlags.SWISSEPH.seValue or SeFlags.SPEED.seValue
        val point = CelestialPoints.SUN
        val jdUtStart = 2459220.459137 - 69.4 / (24 * 3600)        // 2021-1-6 23:00 UT
        val jdUtEnd = jdUtStart + 3.1
        val expLonJd1 = 285.832805465
        val expSpeedLonJd1 = 1.019231558
        val interval = 1
        val result = handler.calcSingleRangePos4Point(point, flags, jdUtStart, jdUtEnd, interval)
        result.point shouldBe CelestialPoints.SUN
        result.datedPositions.size shouldBe 4
        result.datedPositions[0].jdnr shouldBe(jdUtStart plusOrMinus delta)
        result.datedPositions[2].jdnr shouldBe(jdUtStart + 2 plusOrMinus delta)
        result.datedPositions[0].pos.mainPos shouldBe(expLonJd1 plusOrMinus delta)
        result.datedPositions[0].pos.speed shouldBe(expSpeedLonJd1 plusOrMinus delta)
    }


    //    fun calcSingleRangePos4Point(point: CelestialPoints, flags: Int, jdnr1: Double, jdnr2: Double, interval: Int): SinglePosRange4Point {

}

