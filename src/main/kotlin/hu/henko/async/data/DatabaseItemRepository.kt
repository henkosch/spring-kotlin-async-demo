package hu.henko.async.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.asType
import org.springframework.data.r2dbc.core.await
import org.springframework.data.r2dbc.core.flow

class DatabaseItemRepository(private val db: DatabaseClient) : ItemRepository {
    private val table = "item"

    override suspend fun findAll(): Flow<Item> =
        db.select()
            .from(table)
            .asType<Item>()
            .fetch()
            .flow()

    override suspend fun deleteAll() =
        db.execute("DELETE FROM $table")
            .await()

    override suspend fun save(item: Item) =
        db.insert()
            .into(Item::class.java)
            .table(table)
            .using(item)
            .await()

    override suspend fun init(items: Flow<Item>) {
        db.execute("CREATE TABLE IF NOT EXISTS $table (id varchar PRIMARY KEY, name varchar);")
            .await()
        deleteAll()
        items.collect { save(it) }
    }
}
