package com.bugcollection.beetle

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BeetleManagementServiceApplication

fun main(args: Array<String>) {
	runApplication<BeetleManagementServiceApplication>(*args)
}
