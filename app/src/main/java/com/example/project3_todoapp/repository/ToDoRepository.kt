package com.example.project3_todoapp.repository

import com.example.project3_todoapp.MVVM.Todo
import com.example.project3_todoapp.database.TodoDatabase

class ToDoRepository(private val db: TodoDatabase) {
    fun getTodo() = db.todoDao().getAllToDos()

    suspend fun addTodo(todo: Todo) = db.todoDao().insert(todo)
    suspend fun delete(todo: Todo) = db.todoDao().delete(todo)

}