package hu.henko.async.data

import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    fun findAllSync(): List<Item>
    suspend fun findAllAsync(): List<Item>
    suspend fun findAllAsyncFlow(): Flow<Item>

    suspend fun deleteAll()
    suspend fun save(item: Item)
    suspend fun init(items: Flow<Item>)
}
