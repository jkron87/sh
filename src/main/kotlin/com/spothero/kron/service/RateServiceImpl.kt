package com.spothero.kron.service

import com.spothero.kron.DayOfWeek
import com.spothero.kron.api.ApiRate
import com.spothero.kron.entity.Day
import com.spothero.kron.entity.Rate
import com.spothero.kron.entity.RateDAO
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Named

@Named("rateService")
class RateServiceImpl(private val rateDAO: RateDAO) : RateService {
    override fun findById(id: String): Rate {
        return rateDAO.findById(id).get()
    }

    override fun findAll(): Collection<Rate> {
        return rateDAO.findAll()
    }

    override fun save(rate: ApiRate): Rate {

        val days = rate.days.split(",").map {
            val dayOfWeek: DayOfWeek = DayOfWeek.fromValue(it)
            Day(id = dayOfWeek.id, name = dayOfWeek.value)
        }.toMutableList()

        val times = rate.times.split("-")
        val start = LocalTime.parse(times.first(), DateTimeFormatter.ofPattern("HHmm"))
        val end = LocalTime.parse(times.last(), DateTimeFormatter.ofPattern("HHmm"))
        val rate = Rate(startTime = start, endTime = end, price = rate.price, timeZone = ZoneId.of(rate.tz), days = days)

        return rateDAO.save(rate)
    }

}