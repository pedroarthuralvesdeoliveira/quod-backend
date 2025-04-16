package br.com.fiap.quodbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class QuodBackendApplication

fun main(args: Array<String>) {
    runApplication<QuodBackendApplication>(*args)
}
