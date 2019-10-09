package hu.henko.async.data

import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    fun deleteAll()
    fun save(item: Item): Item
    fun findAll(): List<Item>

    suspend fun findAllAsync(): List<Item>
    suspend fun findAllAsyncFlow(): Flow<Item>
}
