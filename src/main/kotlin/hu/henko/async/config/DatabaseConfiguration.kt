package hu.henko.async.config

import io.r2dbc.pool.PoolingConnectionFactoryProvider.MAX_SIZE
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration
import io.r2dbc.postgresql.PostgresqlConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.DefaultReactiveDataAccessStrategy
import org.springframework.data.r2dbc.dialect.PostgresDialect
import io.r2dbc.spi.ConnectionFactoryOptions
import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions.DATABASE
import io.r2dbc.spi.ConnectionFactoryOptions.DRIVER
import io.r2dbc.spi.ConnectionFactoryOptions.HOST
import io.r2dbc.spi.ConnectionFactoryOptions.PASSWORD
import io.r2dbc.spi.ConnectionFactoryOptions.PORT
import io.r2dbc.spi.ConnectionFactoryOptions.PROTOCOL
import io.r2dbc.spi.ConnectionFactoryOptions.USER


@Configuration
class DatabaseConfiguration {
    @Bean
    fun connectionFactory() = ConnectionFactories.get(ConnectionFactoryOptions.builder()
        .option(DRIVER, "pool")
        .option(PROTOCOL, "postgresql")
        .option(HOST, "localhost")
        .option(PORT, 7432)
        .option(USER, "postgres")
        .option(PASSWORD, "postgres")
        .option(DATABASE, "async")
        .option(MAX_SIZE, 1000)
        .build())

    @Bean
    fun databaseClient(connectionFactory: ConnectionFactory) =
        DatabaseClient.create(connectionFactory)

    @Bean
    fun reactiveDataAccessStrategy() =
        DefaultReactiveDataAccessStrategy(PostgresDialect())
}
