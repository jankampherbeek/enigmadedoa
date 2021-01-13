/*
 *
 *  * Jan Kampherbeek, (c) 2021.
 *  * EnigmaDedOA is open source.
 *  * Please check the file copyright.txt in the root of the source for further details.
 *
 */

package com.radixpro.enigma.dedoa.handlers

import com.radixpro.enigma.dedoa.calc.JulianCreator
import com.radixpro.enigma.dedoa.calc.SeFrontend
import com.radixpro.enigma.dedoa.core.*

class DateTimeHandler(private val julianCreator: JulianCreator) {

    fun calculateJdnrEt(dateTime: DateTime): Double {
        return julianCreator.createDateTimeJulian(dateTime)
    }

    fun validDate(year: Int, month: Int, day: Int): Boolean {
        return SeFrontend.isValidDate(year, month, day)
    }

}


class AstronPositionHandler() {

    private fun calcSinglePos(point: CelestialPoints, jdNr: Double, flags: Int): SinglePosition {
        val posResult = SeFrontend.getPositionsForCelBody(jdNr, point.id, flags)
        return SinglePosition(posResult.allPositions[0], posResult.allPositions[3])
    }

    /**
     * Calculate longitude and speed for a specific point at a specific time.
     */
    fun calcSinglePosition4Point(point: CelestialPoints, jdNr: Double, flags: Int): SinglePosition4Point {
        return SinglePosition4Point(point, calcSinglePos(point, jdNr, flags))
    }

    /**
     * Calculate longitude and speed for several points at a specific time.
     */
    fun calcPosition4MultiplePoints(points: List<CelestialPoints>, jdnr: Double, flags: Int): List<SinglePosition4Point> {
        val positions: MutableList<SinglePosition4Point> = mutableListOf()
        for (point in points) {
            positions.add(calcSinglePosition4Point(point, jdnr, flags))
        }
        return positions.toList()
    }

    /**
     * Calculate SinglePosition for a given time range and for a specific CelestialPoint. The result always includes the last Julian Day.
     */
    fun calcSingleRangePos4Point(point: CelestialPoints, flags: Int, jdnr1: Double, jdnr2: Double, interval: Int): SinglePosRange4Point {
        var actJdnr = jdnr1
        val datedPositions: MutableList<DatedSinglePosition> = ArrayList()
        while (actJdnr < jdnr2) {
            datedPositions.add(DatedSinglePosition(actJdnr, calcSinglePos(point, actJdnr, flags)))
            actJdnr += interval
        }
        return SinglePosRange4Point(point, datedPositions.toList())
    }


}


