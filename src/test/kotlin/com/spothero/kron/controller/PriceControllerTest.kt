package com.spothero.kron.controller

import com.spothero.kron.exceptions.BadRequestException
import com.spothero.kron.service.PriceService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.mock


class PriceControllerTest {

    private val priceService = mock(PriceService::class.java)
    private val priceController = PriceController(priceService)

    @Test
    fun `a time range greater than 24 hours should throw a bad request exception`() {
        assertThrows<BadRequestException> { priceController.getPrice("2015-07-04T07:00:00+05:00", "2015-07-06T07:00:00+05:00") }
    }

    @Test
    fun `an un-parseable date should throw a bad request exception`() {
        assertThrows<BadRequestException> {
            priceController.getPrice("unparseable", "2015-07-06T07:00:00+05:00")
        }
    }
}