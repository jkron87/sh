package com.spothero.kron

enum class DayOfWeek(val value: String ,val id: Long) {
    MONDAY("mon", 1),
    TUESDAY("tues", 2),
    WEDNESDAY("wed", 3),
    THURSDAY("thurs", 4),
    FRIDAY("fri", 5),
    SATURDAY("sat", 6),
    SUNDAY("sun", 7);


    companion object {
        private val lookup = values().associateBy(DayOfWeek::value)
        fun fromValue(value: String): DayOfWeek = requireNotNull(lookup[value]) { "No DayOfWeek with value $value" }
    }
}