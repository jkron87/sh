package com.spothero.kron.service

import java.time.ZonedDateTime
import java.util.Optional

interface PriceService {

    fun findPrice(start: ZonedDateTime, end: ZonedDateTime): Optional<Long>
}