package com.todo.tododappdemo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.todo.tododappdemo.roomdb.Todo
import com.todo.tododappdemo.roomdb.TodoDatabase
import com.todo.tododappdemo.repo.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModal(application: Application) : AndroidViewModel(application) {

    val allNotes: LiveData<List<Todo>>
    val repository: TodoRepository


    init {
        val dao = TodoDatabase.getDatabase(application).getNotesDao()
        repository = TodoRepository(dao)
        allNotes = repository.allNotes
    }


    fun deleteTodo(todo: Todo) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(todo)
    }


    fun updateTodo(todo: Todo) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(todo)
    }

    fun addTodo(todo: Todo) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(todo)
    }

}