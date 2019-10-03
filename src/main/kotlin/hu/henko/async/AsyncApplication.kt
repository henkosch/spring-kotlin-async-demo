package hu.henko.async

import hu.henko.async.config.BeanConfiguration
import hu.henko.async.config.InitDatabase
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AsyncApplication

fun main(args: Array<String>) {
	runApplication<AsyncApplication>(*args) {
        addInitializers(BeanConfiguration())
        addListeners(InitDatabase())
    }
}
