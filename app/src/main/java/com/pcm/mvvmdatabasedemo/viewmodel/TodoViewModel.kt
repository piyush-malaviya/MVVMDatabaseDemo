package com.pcm.mvvmdatabasedemo.viewmodel

import androidx.lifecycle.*
import com.pcm.mvvmdatabasedemo.db.entity.TodoEntity
import com.pcm.mvvmdatabasedemo.model.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*class TodoViewModel(application: Application) : AndroidViewModel(application) {

    val allTodo: LiveData<List<TodoEntity>>
    private val todoDao = AppDatabase.invoke(application).todoDao()
    private val todoRepository = TodoRepository(todoDao)

    init {
        allTodo = todoRepository.allTodo
    }

    fun insert(todo: TodoEntity) = viewModelScope.launch {
        todoRepository.insert(todo)
    }
}*/


class TodoViewModel(private val repository: TodoRepository) : ViewModel() {
    val allTodo: LiveData<List<TodoEntity>> = repository.allTodo.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(todo: TodoEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(todo)
    }

    fun delete(todo: TodoEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(todo)
    }
}

class TodoViewModelFactory(private val repository: TodoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TodoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
