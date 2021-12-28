package com.todo.tododappdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.todo.tododappdemo.roomdb.Todo
import com.todo.tododappdemo.viewmodel.TodoViewModal
import kotlinx.android.synthetic.main.activity_add_note.*
import java.text.SimpleDateFormat
import java.util.*

class AddTodoActivity : AppCompatActivity() {


    lateinit var viewModal: TodoViewModal
    var noteID = -1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)


        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(TodoViewModal::class.java)


        val noteType = intent.getStringExtra("noteType")
        if (noteType.equals("Edit")) {
            // on below line we are setting data to edit text.
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDescription = intent.getStringExtra("noteDescription")
            noteID = intent.getIntExtra("noteId", -1)
            idBtn.setText("Update Note")
            todoName.setText(noteTitle)
            todoDescription.setText(noteDescription)
        } else {
            idBtn.setText("Save Note")
        }


        idBtn.setOnClickListener {

            val noteTitle = todoName.text.toString()
            val noteDescription = todoDescription.text.toString()
            // on below line we are checking the type
            // and then saving or updating the data.
            if (noteType.equals("Edit")) {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime: String = sdf.format(Date())
                    val updatedNote = Todo(noteTitle, noteDescription, currentDateAndTime)
                    updatedNote.id = noteID
                    viewModal.updateTodo(updatedNote)
                    Toast.makeText(this, "Note Updated..", Toast.LENGTH_LONG).show()
                }
            } else {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime: String = sdf.format(Date())
                    // if the string is not empty we are calling a
                    // add note method to add data to our room database.
                    viewModal.addTodo(Todo(noteTitle, noteDescription, currentDateAndTime))
                    Toast.makeText(this, "$noteTitle Added", Toast.LENGTH_LONG).show()
                }
            }
            // opening the new activity on below line
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }

    }
}