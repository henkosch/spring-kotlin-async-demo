package hu.henko.async

import hu.henko.async.config.InitDatabase
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@SpringBootApplication
@EnableJpaRepositories("hu.henko.async.data.jpa")
@EnableR2dbcRepositories("hu.henko.async.data.r2dbc")
class AsyncApplication

fun main(args: Array<String>) {
	runApplication<AsyncApplication>(*args) {
        addListeners(InitDatabase())
    }
}
