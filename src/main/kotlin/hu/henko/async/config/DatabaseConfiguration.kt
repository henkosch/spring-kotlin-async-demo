package hu.henko.async.config

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration
import io.r2dbc.postgresql.PostgresqlConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.DefaultReactiveDataAccessStrategy
import org.springframework.data.r2dbc.dialect.PostgresDialect

@Configuration
class DatabaseConfiguration {
    @Bean
    fun postgresqlConnectionConfiguration() =
        PostgresqlConnectionConfiguration
            .builder()
            .host("localhost")
            .port(7432)
            .database("async")
            .username("postgres")
            .password("postgres")
            .build()

    @Bean
    fun postgresqlConnectionFactory(postgresqlConnectionConfiguration: PostgresqlConnectionConfiguration) =
        PostgresqlConnectionFactory(postgresqlConnectionConfiguration)

    @Bean
    fun databaseClient(postgresqlConnectionFactory: PostgresqlConnectionFactory) =
        DatabaseClient.create(postgresqlConnectionFactory)

    @Bean
    fun reactiveDataAccessStrategy() =
        DefaultReactiveDataAccessStrategy(PostgresDialect())
}
