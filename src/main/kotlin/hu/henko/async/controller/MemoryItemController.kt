package hu.henko.async.controller

import hu.henko.async.data.MemoryItemRepository
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/memory")
class MemoryItemController(memoryItemRepository: MemoryItemRepository): ItemController(memoryItemRepository)
