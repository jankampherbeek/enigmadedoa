/*
 *
 *  * Jan Kampherbeek, (c) 2021.
 *  * EnigmaDedOA is open source.
 *  * Please check the file copyright.txt in the root of the source for further details.
 *
 */

package com.radixpro.enigma.dedoa.handlers

import com.radixpro.enigma.dedoa.calc.JulianCreator
import com.radixpro.enigma.dedoa.core.DateTime
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class DateTimeHandlerTest {

    private val handler = DateTimeHandler(JulianCreator())
    private val delta = 0.00000001

    @Test
    fun `Calculation of jdnr for a given date should give the correct results`() {
       val dateTime = DateTime(1953, 1, 29, 7, 37, 30)
       val expectedJd = 2434406.8177083335
       val result = handler.calculateJdnrEt(dateTime)
       result shouldBe(expectedJd plusOrMinus delta)
    }

    @Test
    fun `Validation of correct values for date should return true`() {
        assertTrue(handler.validDate(1953, 1, 29))
    }

    @Test
    fun `Validation of incorrect values for date should return false`() {
        assertFalse(handler.validDate(1953,2,29))
    }
}