package jp.fukicycle.jashter.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JashterApplication

fun main(args: Array<String>) {
    runApplication<JashterApplication>(*args)
}