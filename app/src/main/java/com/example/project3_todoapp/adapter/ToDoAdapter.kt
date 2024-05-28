package com.example.project3_todoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.project3_todoapp.Fragments.ToDoFragment
import com.example.project3_todoapp.MVVM.Todo
import com.example.project3_todoapp.databinding.TodoLayoutBinding

class ToDoAdapter:RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    class ToDoViewHolder(val itemBinding: TodoLayoutBinding):RecyclerView.ViewHolder(itemBinding.root)

    private val differCallBack = object :DiffUtil.ItemCallback<Todo>(){
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.title == newItem.title &&
                    oldItem.description == newItem.description &&
                    oldItem.time == newItem.time
        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return  oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(
            TodoLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        var currentTodo = differ.currentList[position]

        holder.itemBinding.txtTitle.text = currentTodo.title
        holder.itemBinding.txtDescription.text = currentTodo.description
        holder.itemBinding.txtTime.text = currentTodo.time.toString()

        holder.itemView.setOnClickListener{

        }


    }


}