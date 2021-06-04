package com.spothero.kron.api

import com.spothero.kron.DayOfWeek
import com.spothero.kron.entity.Rate
import java.time.format.DateTimeFormatter

fun convertToApiResponse(rate: Rate): ApiRate {

    val times = rate.startTime.format(DateTimeFormatter.ofPattern("HHmm")) + "-" + rate.endTime.format(DateTimeFormatter.ofPattern("HHmm"))
    val tz = rate.timeZone.id
    val days = rate.days.joinToString(",") { DayOfWeek.valueOf(it.name).value }

    return ApiRate(times = times, tz = tz, days = days, price = rate.price)

}