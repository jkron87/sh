package com.spothero.kron.service

import com.spothero.kron.Day
import com.spothero.kron.api.ApiRate
import com.spothero.kron.entity.Rate
import com.spothero.kron.entity.RateDAO
import com.spothero.kron.util.toBase64String
import java.time.DayOfWeek
import java.time.Instant
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZoneOffset.UTC
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.UUID
import javax.inject.Named

@Named("rateService")
class RateServiceImpl(private val rateDAO: RateDAO) : RateService {

    override fun findAll(): Collection<Rate> {
        return rateDAO.findAll()
    }

    override fun save(rate: ApiRate) {
        val days = rate.days.split(",").map {
            Day.fromValue(it)
        }.toMutableList()

        val times: List<String> = rate.times.split("-")
        val start = LocalTime.parse(times.first(), DateTimeFormatter.ofPattern("HHmm"))
        val end = LocalTime.parse(times.last(), DateTimeFormatter.ofPattern("HHmm"))

        val rateGroup = UUID.randomUUID().toBase64String()

        days.forEach {
            val startAdjustedToUTC = Instant.from(ZonedDateTime.of(2020, 1, 1, start.hour, start.minute, 0, 0, ZoneId.of(rate.tz)).with((TemporalAdjusters.nextOrSame(DayOfWeek.valueOf(it.name)))).withZoneSameInstant(UTC))
            val endAdjustedToUTC = Instant.from(ZonedDateTime.of(2020, 1, 1, end.hour, end.minute, 0, 0, ZoneId.of(rate.tz)).with((TemporalAdjusters.nextOrSame(DayOfWeek.valueOf(it.name)))).withZoneSameInstant(UTC))
            val rateEntity = Rate(startTime = startAdjustedToUTC, endTime = endAdjustedToUTC, price = rate.price, timeZone = ZoneId.of(rate.tz), rateGroup = rateGroup)
            val existingRateOptional = rateDAO.findByStartTimeAndEndTimeAndTimeZone(rateEntity.startTime, rateEntity.endTime, rateEntity.timeZone)

            if (existingRateOptional.isPresent) {
                val existingRate = existingRateOptional.get()
                existingRate.price = rate.price
                rateDAO.save(existingRate)
            } else {
                rateDAO.save(rateEntity)
            }
        }
    }

}