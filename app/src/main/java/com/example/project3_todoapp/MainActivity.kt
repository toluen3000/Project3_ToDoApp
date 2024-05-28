package com.example.project3_todoapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.project3_todoapp.MVVM.ToDoViewModel
import com.example.project3_todoapp.MVVM.ToDoViewModelFactory
import com.example.project3_todoapp.database.TodoDatabase
import com.example.project3_todoapp.databinding.ActivityMainBinding
import com.example.project3_todoapp.repository.ToDoRepository


class MainActivity : AppCompatActivity() {
    lateinit var todoViewModel: ToDoViewModel
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)

       try {
           setContentView(binding.root)
           val toDoRepository = ToDoRepository(TodoDatabase(this))
           val toDoViewModelFactory = ToDoViewModelFactory(toDoRepository)
           todoViewModel = ViewModelProvider(
               this,
               toDoViewModelFactory)[ToDoViewModel::class.java]

       }catch (e : Exception){
            Log.d("TAG","ERROR")

        }

    }
    private fun setupViewModel(){

    }
}