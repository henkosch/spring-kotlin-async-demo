package hu.henko.async.data

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Item(
    @Id
    val id: String,
    val name: String
)
