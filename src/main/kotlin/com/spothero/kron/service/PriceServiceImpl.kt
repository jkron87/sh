package com.spothero.kron.service

import com.spothero.kron.entity.RateDAO
import com.spothero.kron.exceptions.BadRequestException
import java.time.Instant
import java.time.ZonedDateTime
import java.time.temporal.TemporalAdjusters
import java.util.Optional
import javax.inject.Named

@Named("priceService")
class PriceServiceImpl(private val rateDAO: RateDAO) : PriceService {

    override fun findPrice(start: ZonedDateTime, end: ZonedDateTime): Optional<Long> {
        val startDayOfWeek = start.dayOfWeek
        val endDayOfWeek = end.dayOfWeek

        val convertedStartDate = Instant.from(ZonedDateTime.of(2020, 1, 1, start.hour, start.minute, start.second, start.nano, start.zone).with((TemporalAdjusters.nextOrSame(java.time.DayOfWeek.valueOf(startDayOfWeek.toString())))))
        val convertedEndDate = Instant.from(ZonedDateTime.of(2020, 1, calculateDay(start, end), end.hour, end.minute, end.second, end.nano, end.zone).with((TemporalAdjusters.nextOrSame(java.time.DayOfWeek.valueOf(endDayOfWeek.toString())))))

        val availableRates = rateDAO.findAllByStartTimeLessThanEqualAndEndTimeGreaterThanEqual(convertedStartDate, convertedEndDate)
        if (availableRates.size > 1) {
            throw BadRequestException("unavailable")
        }
        val rate = availableRates.firstOrNull()

        return Optional.ofNullable(rate?.price)
    }

    private fun calculateDay(start: ZonedDateTime, end: ZonedDateTime): Int {
        return if (start.dayOfWeek != end.dayOfWeek) {
            2
        } else {
            1
        }
    }


}