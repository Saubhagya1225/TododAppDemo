package com.todo.tododappdemo.repo

import androidx.lifecycle.LiveData
import com.todo.tododappdemo.roomdb.Todo
import com.todo.tododappdemo.roomdb.TodoDao

class TodoRepository(private val todoDao: TodoDao) {

    val allNotes: LiveData<List<Todo>> = todoDao.getAllTodo()

    suspend fun insert(todo: Todo) {
        todoDao.insert(todo)
    }

    // on below line we are creating a delete method
    // for deleting our todo from database.
    suspend fun delete(todo: Todo){
        todoDao.delete(todo)
    }

    // on below line we are creating a update method for
    // updating our todo from database.
    suspend fun update(todo: Todo){
        todoDao.update(todo)
    }
}