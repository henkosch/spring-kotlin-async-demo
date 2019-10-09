package hu.henko.async.data.jpa

import hu.henko.async.data.Item
import org.springframework.data.jpa.repository.JpaRepository

interface SyncDatabaseItemRepository : JpaRepository<Item, String>
