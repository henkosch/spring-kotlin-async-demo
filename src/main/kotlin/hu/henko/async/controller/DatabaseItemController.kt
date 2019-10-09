package hu.henko.async.controller

import hu.henko.async.data.DatabaseItemRepository
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/db")
class DatabaseItemController(databaseItemRepository: DatabaseItemRepository): ItemController(databaseItemRepository)
