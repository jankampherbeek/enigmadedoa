/*
 *
 *  * Jan Kampherbeek, (c) 2021.
 *  * EnigmaDedOA is open source.
 *  * Please check the file copyright.txt in the root of the source for further details.
 *
 */

package com.radixpro.enigma.dedoa.core


/**
 * Position and speed in a one coordinate of a specific coordinate-system.
 */
data class SinglePosition(val pos: Double, val speed: Double)

/**
 * Position values for a specific celestial point (celPoint). Longitude (lon), latitude (lat), Right Ascension (ra) and Declination (decl).
 */
data class CelestialPosition(val celPoint: CelestialPoints, val lon: SinglePosition, val lat: SinglePosition, val ra: SinglePosition, val decl: SinglePosition)

/**
 * Set of data for a specifric date and time. Contains Julian Day Number (jdnr) and a list of positions.
 */
data class BasicDataSet(val jdnr: Double, val positions: List<CelestialPosition>)