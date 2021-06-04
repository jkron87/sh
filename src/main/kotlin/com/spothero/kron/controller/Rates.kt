package com.spothero.kron.controller

import com.spothero.kron.api.ApiRate
import com.spothero.kron.api.convertToApiResponse
import com.spothero.kron.service.RateService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/api/rates")
@ResponseBody
class Rates(private val rateService: RateService) {

    @GetMapping
    fun getRates(): Collection<ApiRate> {

        val rates = rateService.findAll()

        return rates.map { convertToApiResponse(it) }
    }

    @PutMapping
    fun putRate(@RequestBody rate: ApiRate): ResponseEntity<Nothing?> {
        rateService.save(rate)
        return ResponseEntity(null, HttpHeaders(), HttpStatus.OK)

    }


}