package com.spothero.kron.service

import com.nhaarman.mockitokotlin2.argumentCaptor
import com.spothero.kron.api.ApiRate
import com.spothero.kron.entity.Rate
import com.spothero.kron.entity.RateDAO
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

class RateServiceImplTest{

    private val rateDAO: RateDAO = mock(RateDAO::class.java)
    private val rateService = RateServiceImpl(rateDAO)
    private val rateCaptor = argumentCaptor<Rate>()

    @Test
    fun `rate service should save a rate for each day of week`() {

        val apiRate = ApiRate("0100-0500", "America/Chicago", "mon,tues", 1500)
        rateService.save(apiRate)

        verify(rateDAO, times(2)).save(rateCaptor.capture())
    }
}