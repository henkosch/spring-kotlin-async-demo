package hu.henko.async.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.springframework.stereotype.Repository

@Repository
class MemoryItemRepository : ItemRepository {
    private val items = listOf(
        Item("1", "This"),
        Item("2", "is"),
        Item("3", "from"),
        Item("4", "memory")
    )

    private val time = 100L

    override fun deleteAll() = Unit
    override fun save(item: Item) = item

    override fun findAll(): List<Item> {
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
}
