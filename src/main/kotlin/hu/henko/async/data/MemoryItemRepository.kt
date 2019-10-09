package hu.henko.async.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MemoryItemRepository : ItemRepository {
    private val items = listOf(
        Item("1", "This"),
        Item("2", "is"),
        Item("3", "from"),
        Item("4", "memory")
    )

    private val time = 200L

    override fun findAllSync(): List<Item> {
        Thread.sleep(time)
        return items.toList()
    }

    override suspend fun findAllAsync(): List<Item> {
        delay(time)
        return items.toList()
    }

    override suspend fun findAllAsyncFlow(): Flow<Item> {
        return flow {
            items.forEach {
                delay(time / items.count())
                emit(it)
            }
        }
    }

    override suspend fun deleteAll() = Unit
    override suspend fun save(item: Item) = Unit
    override suspend fun init(items: Flow<Item>) = Unit
}
