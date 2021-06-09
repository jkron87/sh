package com.spothero.kron.controller

import com.spothero.kron.api.ApiPrice
import com.spothero.kron.exceptions.BadRequestException
import com.spothero.kron.service.PriceService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import java.time.ZonedDateTime

@Controller
@RequestMapping("/api/price")
@ResponseBody
@Api("Pricing for parking")
class PriceController(private val priceService: PriceService) {

    @GetMapping
    @ApiOperation(value = "Get the price for parking for a time range")
    fun getPrice(
            @RequestParam("start")
            @ApiParam(
                    name = "start",
                    type = "String",
                    value = "Start time for parking in ISO-8601",
                    example = "2015-07-01T07:00:00-05:00",
                    required = true)
            start: String,
            @RequestParam("end")
            @ApiParam(
                    name = "end",
                    type = "String",
                    value = "End time for parking in ISO-8601",
                    example = "2015-07-01T08:00:00-05:00",
                    required = true)
            end: String): Any {

        val filteredStart = start.replace(' ', '+')
        val filteredEnd = end.replace(' ', '+')

        val requestDateStart: ZonedDateTime?
        val requestDateEnd: ZonedDateTime?

        try {
            requestDateStart = ZonedDateTime.parse(filteredStart)
            requestDateEnd = ZonedDateTime.parse(filteredEnd)
        } catch (e: Exception) {
            throw BadRequestException("start and end must be in iso-8601 format (i.e.,'2015-07-01T07:00:00-05:00')")
        }

        if (requestDateStart.plusHours(24) < requestDateEnd) {
            throw BadRequestException("unavailable")
        }
        val price = priceService.findPrice(requestDateStart, requestDateEnd).orElse(null)

        return price?.let { ApiPrice(price) }
                ?: "unavailable"
    }

}