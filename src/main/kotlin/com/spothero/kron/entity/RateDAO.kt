package com.spothero.kron.entity

import java.util.Optional

interface RateDAO {
    fun findById(id: String): Optional<Rate>
    fun findAll(): Collection<Rate>
    fun save(rate: Rate): Rate
}