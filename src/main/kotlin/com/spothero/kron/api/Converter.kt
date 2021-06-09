package com.spothero.kron.api

import com.spothero.kron.Day
import com.spothero.kron.entity.Rate
import java.time.format.DateTimeFormatter

fun convertToApiResponse(rates: List<Rate>): ApiRate {

    val startTime = rates.first().startTime.atZone(rates.first().timeZone)
    val endTime = rates.first().endTime.atZone(rates.first().timeZone)
    val times = startTime.format(DateTimeFormatter.ofPattern("HHmm")) + "-" + endTime.format(DateTimeFormatter.ofPattern("HHmm"))
    val tz = rates.first().timeZone.id
    val days = rates.joinToString { Day.valueOf(it.startTime.atZone(rates.first().timeZone).dayOfWeek.toString()).abbreviation }

    return ApiRate(times = times, tz = tz, days = days, price = rates.first().price)

}