package com.spothero.kron

enum class Day(val abbreviation: String) {
    MONDAY("mon"),
    TUESDAY("tues"),
    WEDNESDAY("wed"),
    THURSDAY("thurs"),
    FRIDAY("fri"),
    SATURDAY("sat"),
    SUNDAY("sun");


    companion object {
        private val lookup = values().associateBy(Day::abbreviation)
        fun fromValue(value: String): Day = requireNotNull(lookup[value]) { "No DayOfWeek with value $value" }
    }
}