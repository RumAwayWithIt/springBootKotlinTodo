package com.rum.springKotlinTodo

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/")
class todoController(private val todoService: todoService) {

    @GetMapping
    fun home(): String {
        return "Welcome to your to-do list!" +
                "\n-> /all to view entire list" +
                "\n-> /todo to view unfinished items" +
                "\n-> /done to view completed items"
    }

    //Entire list
    @GetMapping("/all")
    fun getAllTodoItems(): List<todoItemDto> = todoService.findAll()

    //Finished items
    @GetMapping("/done")
    fun getAllCompletedItems(): List<todoItemDto> = todoService.findByisDone(true)

    //Unfinished items
    @GetMapping("/todo")
    fun getAllUncompletedItems(): List<todoItemDto> = todoService.findByisDone(false)

    //Finding by ID
    @GetMapping("/{idToGet}")
    fun getTodoItemById(@PathVariable idToGet: Long): ResponseEntity<todoItemDto> {
        val todoItem = todoService.findById(idToGet)
        if (todoItem != null) {
            return ResponseEntity.ok(todoItem)
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
    }

    //Search for todo entries containing specific words
    @GetMapping("/search")
    fun getItemsContaining(@RequestBody todoItem: todoItemDto): List<todoItemDto?> =
        todoService.findByContentContains(todoItem.content)

    //Posting new entry
    @PostMapping
    fun createTodoItem(@RequestBody todoItem: todoItemDto): todoItemDto = todoService.save(todoItem)


    //Deleting entries by ID
    @DeleteMapping("/{idToDelete}")
    fun deleteTodoItemById(@PathVariable idToDelete: Long): ResponseEntity<todoItemDto> {
        val todoItem = todoService.findById(idToDelete)
        if (todoItem != null) {
            todoService.deleteById(idToDelete)
            return ResponseEntity.ok(todoItem)
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
    }

    //Updating entries
    @PutMapping("/{idToUpdate}")
    fun updateTodoItem(@PathVariable idToUpdate: Long,
                       @RequestBody updatedTodoItem: todoItemDto): ResponseEntity<todoItemDto> {
        val existingItem = todoService.findById(idToUpdate)
        if (existingItem != null) {
            //update content and isDone properties
            if (updatedTodoItem.content != null) {
                val newlySavedItem = todoService.save(updatedTodoItem.copy(id = idToUpdate))
                return ResponseEntity.ok(newlySavedItem)
            } else { //Update only isDone property
                val newlySavedItem =
                    todoService.save(updatedTodoItem.copy(id = idToUpdate, content = existingItem.content))
                return ResponseEntity.ok(newlySavedItem)
            }


        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
    }
}