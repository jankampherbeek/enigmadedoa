/*
 *
 *  * Jan Kampherbeek, (c) 2021.
 *  * EnigmaDedOA is open source.
 *  * Please check the file copyright.txt in the root of the source for further details.
 *
 */

package com.radixpro.enigma.dedoa.calc

import com.radixpro.enigma.dedoa.core.SePositionResult
import org.slf4j.LoggerFactory
import swisseph.SweDate
import swisseph.SwissEph

/**
 * Simple wrapper to access the Java port to the SE by Thomas Mack.
 * Implemented as a singleton to prevent multiple instantiations.
 */
object SeFrontend {
    private const val SE_LOCATION = "./se"
    private val log = LoggerFactory.getLogger(SeFrontend::class.java)
    private val swissEph = SwissEph(SE_LOCATION)


    /**
     * Returns TRUE if a date is valid, otherwise FALSE. Use only Gregorian calendar.
     */
    fun isValidDate(year: Int,
                    month: Int,
                    day: Int): Boolean {
        val sweDate1 = SweDate(year, month, day, 0.0, true)
        val calculatedJulDay = sweDate1.julDay
        val sweDate2 = SweDate(calculatedJulDay, true)
        return sweDate1.year == sweDate2.year && sweDate1.month == sweDate2.month && sweDate1.day == sweDate2.day
    }

    /**
     * Calculate ecliptical or equatorial positions for a body
     *
     * @param jdUt  Julian Day based on Ephemeris Time
     * @param id    indicates the body
     * @param flags combined settings for the SE. Always SWISSEPH and SPEED, optionally EQUATORIAL
     * @return calculated positions. Array contains for ecliptical positions: from 0..5: Longitude, latitude, distance in AU, speed long, speed lat, speed dist,
     * and for equatorial positions from 0..5: right ascension, declination, distance in AU, speed RA, speed decl, speed dist.
     */
    fun getPositionsForCelBody(jdUt: Double,
                               id: Int,
                               flags: Int): SePositionResult {
        val allPositions = DoubleArray(6)
        val errorMsg = StringBuffer()                  // StringBuilder not possible because Java Port to the SE uses a StringBuffer.
        swissEph.swe_calc_ut(jdUt, id, flags, allPositions, errorMsg)
        return SePositionResult(allPositions.asList(), errorMsg.toString())
    }

}