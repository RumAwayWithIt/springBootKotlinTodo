package com.rum.springKotlinTodo
import jakarta.persistence.*

@Entity
data class todoItemDto(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val content: String?,
    val isDone: Boolean,
)
