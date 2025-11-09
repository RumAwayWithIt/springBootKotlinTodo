package com.rum.springKotlinTodo
import org.springframework.data.repository.*
import org.springframework.stereotype.Service

@Service
class todoService(private val todoItemRepository: todoItemRepository) {

    fun findAll(): List<todoItemDto> = todoItemRepository.findAll()

    fun findById(id: Long): todoItemDto? = todoItemRepository.findByIdOrNull(id)

    fun save(todoItem: todoItemDto): todoItemDto = todoItemRepository.save(todoItem)

    fun deleteById(id: Long) = todoItemRepository.deleteById(id)

    fun findByisDone(isDone: Boolean): List<todoItemDto> = todoItemRepository.findByIsDone(isDone)

    fun findByContentContains(content: String?): List<todoItemDto?> = todoItemRepository.findByContentContaining(content)


}
