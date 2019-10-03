package hu.henko.async.config

import hu.henko.async.data.DatabaseItemRepository
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration
import io.r2dbc.postgresql.PostgresqlConnectionFactory
import org.springframework.beans.factory.getBean
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.registerBean
import org.springframework.data.r2dbc.core.DatabaseClient

class BeanConfiguration : ApplicationContextInitializer<GenericApplicationContext> {
    override fun initialize(context: GenericApplicationContext) = context.run {
        registerBean<DatabaseItemRepository>()
        registerBean {
            PostgresqlConnectionConfiguration
                .builder()
                .host("localhost")
                .port(7432)
                .database("async")
                .username("postgres")
                .password("postgres")
                .build()
        }
        registerBean {
            PostgresqlConnectionFactory(getBean())
        }
        registerBean {
            DatabaseClient.create(getBean<PostgresqlConnectionFactory>())
        }
    }
}
