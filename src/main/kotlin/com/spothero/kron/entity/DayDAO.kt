package com.spothero.kron.entity

interface DayDAO {
    fun save(day: Day): Day
}