package com.spothero.kron.api

data class ApiRate(
        val times: String,
        val tz: String,
        val days: String,
        val price: Long
)

data class ApiPrice(
        val price: Long
)