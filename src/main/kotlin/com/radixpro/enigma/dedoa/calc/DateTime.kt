/*
 *
 *  * Jan Kampherbeek, (c) 2021.
 *  * EnigmaDedOA is open source.
 *  * Please check the file copyright.txt in the root of the source for further details.
 *
 */

package com.radixpro.enigma.dedoa.calc

import com.radixpro.enigma.dedoa.core.DateTime
import swisseph.SweDate

/**
 * Create a Julian Day Numer for Ephemeris Time.
 * Always assumes a Gregorian calendar and Universal Time.
 */
class JulianCreator {

    fun createDateTimeJulian(dateTime: DateTime): Double {
        val jdFor0h = createJdFor0h(intArrayOf(dateTime.year, dateTime.month, dateTime.day))
        var time = dateTime.hour + dateTime.min / 60.0 + dateTime.sec / 3600.0
        return jdFor0h + time / 24.0
    }

    private fun createJdFor0h(dateParts: IntArray): Double {
        val sweDate = SweDate(dateParts[0], dateParts[1], dateParts[2], 0.0, true)
        return sweDate.julDay
    }
}


