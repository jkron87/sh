package com.spothero.kron.api

import com.fasterxml.jackson.annotation.JsonProperty

data class ApiRate(
        val times: String,
        val tz: String,
        val days: String,
        val price: Long
)