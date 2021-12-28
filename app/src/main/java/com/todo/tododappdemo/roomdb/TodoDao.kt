package com.todo.tododappdemo.roomdb

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDao {

    // below is the insert method for
    // adding a new entry to our database.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(todo : Todo)

    // below is the delete method
    // for deleting our todo.
    @Delete
    suspend fun delete(todo: Todo)

    // below is the method to read all the notes
    // from our database we have specified the query for it.
    // inside the query we are arranging it in ascending
    // order on below line and we are specifying
    // the table name from which
    // we have to get the data.
    @Query("Select * from notesTable order by id ASC")
    fun getAllTodo(): LiveData<List<Todo>>

    // below method is use to update the todo.
    @Update
    suspend fun update(todo: Todo)
}