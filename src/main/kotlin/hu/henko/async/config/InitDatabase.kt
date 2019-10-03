package hu.henko.async.config

import hu.henko.async.data.Item
import hu.henko.async.data.ItemRepository
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.getBean
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent

class InitDatabase : ApplicationListener<ContextRefreshedEvent> {
    private val items = listOf(
        Item("1", "This"),
        Item("2", "is"),
        Item("3", "from"),
        Item("4", "the"),
        Item("5", "database"))

    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        runBlocking {
            event.applicationContext.getBean<ItemRepository>().init(items.asFlow())
        }
    }
}
