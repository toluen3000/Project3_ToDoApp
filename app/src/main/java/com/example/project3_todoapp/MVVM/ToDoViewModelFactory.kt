package com.example.project3_todoapp.MVVM

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.project3_todoapp.database.TodoDAO
import com.example.project3_todoapp.repository.ToDoRepository

@Suppress("UNCHECKED_CAST")
class ToDoViewModelFactory(val app:Application ,private val repository: ToDoRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ToDoViewModel(app , repository) as T
    }
}