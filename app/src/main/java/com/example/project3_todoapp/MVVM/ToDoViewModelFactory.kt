package com.example.project3_todoapp.MVVM

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.project3_todoapp.repository.ToDoRepository

@Suppress("UNCHECKED_CAST")
class ToDoViewModelFactory(private val repository: ToDoRepository):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ToDoViewModel(repository) as T
    }
}