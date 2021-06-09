package com.spothero.kron.service

import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.whenever
import com.spothero.kron.entity.Rate
import com.spothero.kron.entity.RateDAO
import com.spothero.kron.exceptions.BadRequestException
import junit.framework.Assert.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import org.mockito.Mockito.mock
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

class PriceServiceImplTest {

    private val rateDAO: RateDAO = mock(RateDAO::class.java)
    private val priceService = PriceServiceImpl(rateDAO)

    @Test
    fun `finding price should not return more than one rate`() {
        val chinaZoneId = ZoneId.of("Asia/Bangkok")
        val start = ZonedDateTime.of(2021, 6, 8, 18, 0, 0, 0, chinaZoneId)
        val end = ZonedDateTime.of(2021, 6, 8, 19, 0, 0, 0, chinaZoneId)

        whenever(rateDAO.findAllByStartTimeLessThanEqualAndEndTimeGreaterThanEqual(Instant.ofEpochSecond(1578395100), Instant.ofEpochSecond(1578395400))).thenReturn(listOf(
                Rate(
                        rateGroup = "abc123",
                        startTime = start.withZoneSameInstant((chinaZoneId)).toInstant(),
                        endTime = end.withZoneSameInstant((chinaZoneId)).toInstant(),
                        price = 700,
                        timeZone = chinaZoneId),
                Rate(
                        rateGroup = "def",
                        startTime = start.withZoneSameInstant((chinaZoneId)).toInstant(),
                        endTime = end.withZoneSameInstant((chinaZoneId)).toInstant(),
                        price = 700,
                        timeZone = chinaZoneId)))

        assertThrows<BadRequestException> { priceService.findPrice(start.plusMinutes(5), start.plusMinutes(10)).get() }

    }

    @Test
    fun `finding price should calculate calibrated day correctly when days span more than one day of week`() {
        val chinaZoneId = ZoneId.of("Asia/Bangkok")
        val start = ZonedDateTime.of(2021, 6, 8, 18, 0, 0, 0, chinaZoneId)
        val end = ZonedDateTime.of(2021, 6, 9, 1, 0, 0, 0, chinaZoneId)

        priceService.findPrice(start, end)
        val startConvertedDate = argumentCaptor<Instant>()
        val endConvertedDate = argumentCaptor<Instant>()
        Mockito.verify(rateDAO, Mockito.times(1)).findAllByStartTimeLessThanEqualAndEndTimeGreaterThanEqual(startConvertedDate.capture(), endConvertedDate.capture())

        assertTrue(startConvertedDate.firstValue.atZone(chinaZoneId).dayOfWeek !=  endConvertedDate.firstValue.atZone(chinaZoneId).dayOfWeek)
    }

}