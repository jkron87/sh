package com.spothero.kron.controller

import com.spothero.kron.exceptions.BadRequestException
import org.springframework.http.HttpStatus
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@ControllerAdvice(basePackages = ["com.spothero.kron.controller"])
class ExceptionController() {

    @ExceptionHandler(Exception::class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleException(e: Exception, request: HttpServletRequest, response: HttpServletResponse, model: Model): String? {
        response.status = when (e) {
            is BadRequestException -> 400
            else -> 200
        }
        return e.message
    }
}