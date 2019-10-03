package hu.henko.async.controller

import hu.henko.async.data.ItemRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ItemController(val itemRepository: ItemRepository) {
    @GetMapping("/items")
    suspend fun items() = itemRepository.findAll()
}
