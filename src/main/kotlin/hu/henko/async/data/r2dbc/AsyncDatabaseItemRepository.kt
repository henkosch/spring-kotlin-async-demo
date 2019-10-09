package hu.henko.async.data.r2dbc

import hu.henko.async.data.Item
import org.springframework.data.r2dbc.repository.R2dbcRepository

interface AsyncDatabaseItemRepository: R2dbcRepository<Item, String>
