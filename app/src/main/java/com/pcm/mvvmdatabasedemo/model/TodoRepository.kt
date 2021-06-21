package com.pcm.mvvmdatabasedemo.model

import com.pcm.mvvmdatabasedemo.db.dao.TodoDao
import com.pcm.mvvmdatabasedemo.db.entity.TodoEntity
import kotlinx.coroutines.flow.Flow

class TodoRepository(private val todoDao: TodoDao) {

    //val allTodo: LiveData<List<TodoEntity>> = todoDao.getAll()

    val allTodo: Flow<List<TodoEntity>> = todoDao.getAll()

    suspend fun insert(todo: TodoEntity) {
        todoDao.insertTodos(todo)
    }

    suspend fun delete(todo: TodoEntity) {
        todoDao.deleteTodo(todo)
    }

}