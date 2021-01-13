/*
 *
 *  * Jan Kampherbeek, (c) 2021.
 *  * EnigmaDedOA is open source.
 *  * Please check the file copyright.txt in the root of the source for further details.
 *
 */

package com.radixpro.enigma.dedoa.core

import swisseph.SweConst

/**
 * Supported celestial points from the SE that are relevant for this program. The id corresponds to the id of the SE.
 */
enum class CelestialPoints(val id: Int) {
    SUN(0),
    MOON(1),
    MEAN_NODE(10),
    TRUE_NODE(11),
    MEAN_APOGEE(12),
    OSC_APOGEE_SE(13)
}


/**
 * Different parameters that define a calculation, only the values as used in this application have been added.
 * The long values will be or'ed to define the flag value for the SE.
 */
enum class SeFlags(val seValue: Int) {
    SWISSEPH(SweConst.SEFLG_SWIEPH),       // 2L
    SPEED(SweConst.SEFLG_SPEED),           // 256L
    EQUATORIAL(SweConst.SEFLG_EQUATORIAL); // 2048L
}