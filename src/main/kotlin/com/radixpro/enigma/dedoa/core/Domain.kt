/*
 *
 *  * Jan Kampherbeek, (c) 2021.
 *  * EnigmaDedOA is open source.
 *  * Please check the file copyright.txt in the root of the source for further details.
 *
 */

package com.radixpro.enigma.dedoa.core


/**
 * Position and speed in one coordinate of a specific coordinate-system.
 */
data class SinglePosition(val mainPos: Double, val speed: Double)


/**
 * SinglePosition combined with the Julian day number for that position.
 */
data class DatedSinglePosition(val jdnr: Double, val pos: SinglePosition)

/**
 * A CelestialPoint combined with a SinglePosition.
 */
data class SinglePosition4Point(val point: CelestialPoints, val pos: SinglePosition)

/**
 * Position values for a specific celestial point (celPoint). Longitude (lon), latitude (lat), Right Ascension (ra) and Declination (decl).
 */
data class CelestialPosition(val celPoint: CelestialPoints, val lon: SinglePosition, val lat: SinglePosition, val ra: SinglePosition, val decl: SinglePosition)

/**
 * A CelestialPoint combined with a CelestialPosition.
 */
data class CelestialPosition4Point(val point: CelestialPoints, val pos: CelestialPosition)

/**
 * A range of datedpositions for a CelestialPoint.
 */
data class SinglePosRange4Point(val point: CelestialPoints, val datedPositions: List<DatedSinglePosition>)

/**
 * Set of data for a specific date and time. Contains Julian Day Number (jdnr) and a list of positions.
 */
data class BasicDataSet(val jdnr: Double, val positions: List<CelestialPosition>)

/**
 * Container for the result of a SE calculation for celestial bodies.
 *
 * @param allPositions List with the following values from 0..5 : main position, deviation, distance,
 * speed of main position, speed of deviation, speed of distance.
 * @param errorMsg     Error message or "OK".
 */
data class SePositionResult(val allPositions: List<Double>, val errorMsg: String)

/**
 * Elements for date and time.
 */
data class DateTime(val year: Int, val month: Int, val day: Int, val hour: Int, val min: Int, val sec: Int = 0)
