package hu.henko.async.data

import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    suspend fun findAll(): Flow<Item>
    suspend fun deleteAll()
    suspend fun save(item: Item)
    suspend fun init(items: Flow<Item>)
}
