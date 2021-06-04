package com.spothero.kron

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KronApplication

fun main(args: Array<String>) {
	runApplication<KronApplication>(*args)
}
