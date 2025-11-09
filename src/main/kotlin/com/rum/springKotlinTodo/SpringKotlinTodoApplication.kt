package com.rum.springKotlinTodo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
class SpringKotlinTodoApplication

fun main(args: Array<String>) {
	runApplication<SpringKotlinTodoApplication>(*args)
}
