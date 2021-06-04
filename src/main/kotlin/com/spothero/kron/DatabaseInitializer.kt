package com.spothero.kron

import com.spothero.kron.entity.Day
import com.spothero.kron.entity.DayDAO
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.DayOfWeek

@Configuration
open class DatabaseInitializer {

    @Bean
    open fun populateDaysOfWeek(dayDAO: DayDAO) = ApplicationRunner {

        dayDAO.save(Day(id = 1, name = DayOfWeek.MONDAY.toString()))
        dayDAO.save(Day(id = 2, name = DayOfWeek.TUESDAY.toString()))
        dayDAO.save(Day(id = 3, name = DayOfWeek.WEDNESDAY.toString()))
        dayDAO.save(Day(id = 4, name = DayOfWeek.THURSDAY.toString()))
        dayDAO.save(Day(id = 5, name = DayOfWeek.FRIDAY.toString()))
        dayDAO.save(Day(id = 6, name = DayOfWeek.SATURDAY.toString()))
        dayDAO.save(Day(id = 7, name = DayOfWeek.SUNDAY.toString()))
    }

}