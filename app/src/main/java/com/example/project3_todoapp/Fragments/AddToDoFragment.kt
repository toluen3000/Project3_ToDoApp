package com.example.project3_todoapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.project3_todoapp.InventoryApplication
import com.example.project3_todoapp.MVVM.InventoryViewModelFactory
import com.example.project3_todoapp.MVVM.ToDoViewModel
import com.example.project3_todoapp.MVVM.Todo
import com.example.project3_todoapp.R

class AddToDoFragment : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_to_do, container, false)
    }

    private val viewModel: ToDoViewModel by activityViewModels {
        InventoryViewModelFactory(
            (activity?.application as InventoryApplication).database.todoDao()
        )
    }
    lateinit var todo:Todo

}