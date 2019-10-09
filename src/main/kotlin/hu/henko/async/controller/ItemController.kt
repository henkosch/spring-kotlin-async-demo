package hu.henko.async.controller

import hu.henko.async.data.ItemRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
abstract class ItemController(private val itemRepository: ItemRepository) {
    // Synchronous blocking IO
    @GetMapping("/items/sync")
    fun findItems() = itemRepository.findAll()

    // Asynchronous non-blocking IO
    @GetMapping("/items/async")
    suspend fun findItemsAsync() = itemRepository.findAllAsync()

    // Asynchronous non-blocking IO with flow
    @GetMapping("/items/async-flow")
    suspend fun findItemsAsyncFlow() = itemRepository.findAllAsyncFlow()
}
