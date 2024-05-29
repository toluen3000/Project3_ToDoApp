package com.example.project3_todoapp.MVVM

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import com.example.project3_todoapp.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import java.util.Date


class ToDoViewModel(app: Application,private val repository: ToDoRepository):AndroidViewModel(app) {

    private fun insertToDo(todo: Todo){
        viewModelScope.launch(Dispatchers.IO){
            repository.addTodo(todo)
        }

    }    private fun deleteToDo(todo: Todo){
        viewModelScope.launch(Dispatchers.IO){
            repository.delete(todo)
        }
    }

    fun getAllTodos(): LiveData<List<Todo>> = repository.getTodo()

    //Trong lớp InventoryViewModel, hãy thêm một hàm riêng tư khác chứa 3 chuỗi và trả về phiên bản thể hiện Item.

    private fun getNewToDoEntry(title: String, description: String, time:Date):Todo{
        return Todo(
            title = title,
            description = description,
            time = time
        )
    }

    fun addNewToDoEntry(title: String, description: String, time:Date):Todo{
        return Todo(
            title = title,
            description = description,
            time = time
        )
    }

    fun addNewToDo(title: String, description: String, time:Date){
        val newTodo = getNewToDoEntry(title,description,time)
        insertToDo(newTodo)
    }


//Xin lưu ý rằng bạn không sử dụng viewModelScope.launch cho addNewItem()
// , nhưng vẫn phải thêm vào bên trên insertItem() khi bạn gọi phương thức DAO.
// Lý do là lệnh gọi hàm tạm ngưng chỉ được phép đến từ một coroutine hoặc một hàm tạm ngưng khác.
// Hàm itemDao.insert(item) là hàm tạm ngưng.



}

