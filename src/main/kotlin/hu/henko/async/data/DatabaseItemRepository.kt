package hu.henko.async.data

import hu.henko.async.data.jpa.SyncDatabaseItemRepository
import hu.henko.async.data.r2dbc.AsyncDatabaseItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.stereotype.Repository

@Repository
class DatabaseItemRepository(
    private val asyncDb: AsyncDatabaseItemRepository,
    private val syncDb: SyncDatabaseItemRepository
) : ItemRepository {
    override fun deleteAll() {
        syncDb.deleteAll()
    }

    override fun save(item: Item): Item =
        syncDb.save(item)

    override fun findAll(): List<Item> =
        syncDb.findAll()

    override suspend fun findAllAsync(): List<Item> =
        asyncDb.findAll()
            .collectList()
            .awaitSingle()

    override suspend fun findAllAsyncFlow(): Flow<Item> =
        asyncDb.findAll()
            .asFlow()
}
