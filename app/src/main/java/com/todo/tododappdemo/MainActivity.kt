package com.todo.tododappdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.todo.tododappdemo.dapter.TodoAdapter
import com.todo.tododappdemo.roomdb.Todo
import com.todo.tododappdemo.viewmodel.TodoViewModal
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TodoAdapter.TodoClickInterface,
    TodoAdapter.TodoClickDeleteInterface {

    lateinit var viewModal: TodoViewModal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        todoRecycler.layoutManager = LinearLayoutManager(this)


        val todoAdapter = TodoAdapter(this, this, this)

        todoRecycler.adapter = todoAdapter

        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(TodoViewModal::class.java)


        viewModal.allNotes.observe(this, Observer { list ->
            list?.let {
                // on below line we are updating our list.
                todoAdapter.updateList(it)
            }
        })

        idFAB.setOnClickListener {
            val intent = Intent(this@MainActivity, AddTodoActivity::class.java)
            startActivity(intent)
            this.finish()
        }

    }


    override fun onDeleteIconClick(todo: Todo) {
        viewModal.deleteTodo(todo)
        // displaying a toast message
        Toast.makeText(this, "${todo.todoTitle} Deleted", Toast.LENGTH_LONG).show()
    }

    override fun onTodoClick(todo: Todo) {
        val intent = Intent(this@MainActivity, AddTodoActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", todo.todoTitle)
        intent.putExtra("noteDescription", todo.todoDescription)
        intent.putExtra("noteId", todo.id)
        startActivity(intent)
        this.finish()
    }
}