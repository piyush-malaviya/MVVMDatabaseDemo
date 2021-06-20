package com.pcm.mvvmdatabasedemo.db.dao

import androidx.room.*
import com.pcm.mvvmdatabasedemo.db.entity.TodoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    /*@Query("SELECT * FROM entity_todo")
    fun getAll(): LiveData<List<TodoEntity>>*/

    @Query("SELECT * FROM entity_todo")
    fun getAll(): Flow<List<TodoEntity>>

    @Query("SELECT * FROM entity_todo WHERE title LIKE :title")
    fun findByTitle(title: String): TodoEntity

    @Insert
    fun insertTodos(vararg todo: TodoEntity)

    @Delete
    fun deleteTodo(todo: TodoEntity)

    @Update
    fun updateTodo(vararg todos: TodoEntity)
}