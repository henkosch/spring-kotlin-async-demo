package hu.henko.async.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class StaticItemRepository : ItemRepository {
    override suspend fun findAll(): Flow<Item> {
        return flow {
            delay(500)
            emit(Item("1", "This"))
            delay(500)
            emit(Item("2", "is"))
            delay(500)
            emit(Item("3", "from"))
            delay(500)
            emit(Item("4", "memory"))
        }
    }

    override suspend fun deleteAll() = Unit
    override suspend fun save(item: Item) = Unit
    override suspend fun init(items: Flow<Item>) = Unit
}
