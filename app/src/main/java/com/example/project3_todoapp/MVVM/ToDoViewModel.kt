package com.example.project3_todoapp.MVVM

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import com.example.project3_todoapp.MVVM.Todo
import java.util.Date


class ToDoViewModel(private val todoDao: TodoDAO):ViewModel() {

    private fun insertToDo(todo: Todo){
        viewModelScope.launch{
            todoDao.insert(todo)
        }
    }

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

class InventoryViewModelFactory(private val todoDao: TodoDAO):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ToDoViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
                return ToDoViewModel(todoDao) as T

        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}