package hu.henko.async.config

import hu.henko.async.data.DatabaseItemRepository
import hu.henko.async.data.Item
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.getBean
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.await

class InitDatabase : ApplicationListener<ContextRefreshedEvent> {
    private val items = listOf(
        Item("1", "This"),
        Item("2", "is"),
        Item("3", "from"),
        Item("4", "the"),
        Item("5", "database"))

    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        runBlocking {
            event.applicationContext
                .getBean<DatabaseClient>()
                .execute("CREATE TABLE IF NOT EXISTS item (id varchar PRIMARY KEY, name varchar);")
                .await()
        }

        val repository = event.applicationContext.getBean<DatabaseItemRepository>()
        repository.deleteAll()
        items.forEach { repository.save(it) }
    }
}
