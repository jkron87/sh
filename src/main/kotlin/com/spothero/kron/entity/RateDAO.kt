package com.spothero.kron.entity

import java.time.Instant
import java.time.ZoneId
import java.util.Optional
import java.util.TimeZone

interface RateDAO {
    fun findByStartTimeAndEndTimeAndTimeZone(startTime: Instant, endTime: Instant, timeZone: ZoneId): Optional<Rate>
    fun findAllByStartTimeLessThanEqualAndEndTimeGreaterThanEqual(startTime: Instant, endTime:Instant): Collection<Rate>
    fun findAll(): Collection<Rate>
    fun save(rate: Rate): Rate
}