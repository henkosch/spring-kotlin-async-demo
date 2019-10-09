package hu.henko.async.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.asType
import org.springframework.data.r2dbc.core.await
import org.springframework.data.r2dbc.core.flow

interface SyncDatabaseItemRepository : JpaRepository<Item, String>

class DatabaseItemRepository(
    private val asyncDb: DatabaseClient,
    private val syncDb: SyncDatabaseItemRepository
) : ItemRepository {
    private val table = "item"

    override fun findAllSync(): List<Item> = syncDb.findAll()

    override suspend fun findAllAsync(): List<Item> =
        asyncDb.select()
            .from(table)
            .asType<Item>()
            .fetch()
            .all()
            .collectList()
            .awaitSingle()

    override suspend fun findAllAsyncFlow(): Flow<Item> =
        asyncDb.select()
            .from(table)
            .asType<Item>()
            .fetch()
            .flow()

    override suspend fun deleteAll() =
        asyncDb.execute("DELETE FROM $table")
            .await()

    override suspend fun save(item: Item) =
        asyncDb.insert()
            .into(Item::class.java)
            .table(table)
            .using(item)
            .await()

    override suspend fun init(items: Flow<Item>) {
        asyncDb.execute("CREATE TABLE IF NOT EXISTS $table (id varchar PRIMARY KEY, name varchar);")
            .await()
        deleteAll()
        items.collect { save(it) }
    }
}
