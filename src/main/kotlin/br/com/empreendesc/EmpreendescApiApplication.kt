package br.com.empreendesc
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EmpreendescApiApplication

fun main(args: Array<String>) {
    runApplication<EmpreendescApiApplication>(*args)
}
