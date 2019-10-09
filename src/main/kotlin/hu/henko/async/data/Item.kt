package hu.henko.async.data
import javax.persistence.Entity

@Entity
data class Item(
    @javax.persistence.Id
    @org.springframework.data.annotation.Id
    val id: String,
    val name: String
)
