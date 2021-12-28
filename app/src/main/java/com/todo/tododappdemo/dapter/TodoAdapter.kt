package com.todo.tododappdemo.dapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.todo.tododappdemo.roomdb.Todo
import com.todo.tododappdemo.R
import kotlinx.android.synthetic.main.list_todo.view.*

class TodoAdapter(
    val context: Context,
    val todoClickDeleteInterface: TodoClickDeleteInterface,
    val todoClickInterface: TodoClickInterface
) : RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    private val allTodo = ArrayList<Todo>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        /* val todoNote = itemView.idTodoNote
         val dateTV = itemView.idTodoDate*/
       // val deleteIV = itemView.idIVDelete
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.list_todo,
            parent, false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TodoAdapter.ViewHolder, position: Int) {
        holder.itemView.idTodoNote.setText(allTodo.get(position).todoTitle)
        // holder.todoNote.setText(allNotes.get(position).noteTitle)
        holder.itemView.idTodoDate.setText("Last Updated : " + allTodo.get(position).timeStamp)

        holder.itemView.idIVDelete.setOnClickListener {

            todoClickDeleteInterface.onDeleteIconClick(allTodo.get(position))
        }


        holder.itemView.setOnClickListener {
            todoClickInterface.onTodoClick(allTodo.get(position))
        }

    }

    override fun getItemCount(): Int {
        return allTodo.size
    }

    fun updateList(newList: List<Todo>) {
        allTodo.clear()
        allTodo.addAll(newList)
        notifyDataSetChanged()
    }


    interface TodoClickDeleteInterface {

        fun onDeleteIconClick(todo: Todo)
    }

    interface TodoClickInterface {

        fun onTodoClick(todo: Todo)
    }

}