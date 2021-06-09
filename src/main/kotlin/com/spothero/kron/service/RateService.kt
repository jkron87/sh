package com.spothero.kron.service

import com.spothero.kron.api.ApiRate
import com.spothero.kron.entity.Rate

interface RateService {
    fun findAll(): Collection<Rate>
    fun save(rate: ApiRate)
}