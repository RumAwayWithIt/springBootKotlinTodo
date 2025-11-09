package com.rum.springKotlinTodo

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.rum.springKotlinTodo.todoItemDto
import java.util.Optional

@Repository
interface todoItemRepository : JpaRepository<todoItemDto, Long> {
    override fun findById(id: Long): Optional<todoItemDto?>
    fun findByIsDone(isDone: Boolean): List<todoItemDto>
    fun findByContentContaining(content: String?): List<todoItemDto>
}