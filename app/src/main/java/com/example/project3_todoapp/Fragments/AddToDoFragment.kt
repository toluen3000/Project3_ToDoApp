package com.example.project3_todoapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.navArgs
import com.example.project3_todoapp.Fragments.AddToDoFragmentDirections.ActionAddToDoFragmentToToDoFragment
import com.example.project3_todoapp.InventoryApplication
import com.example.project3_todoapp.MVVM.ToDoViewModel
import com.example.project3_todoapp.MVVM.ToDoViewModelFactory
import com.example.project3_todoapp.MVVM.Todo
import com.example.project3_todoapp.R
import com.example.project3_todoapp.databinding.ActivityMainBinding
import com.example.project3_todoapp.databinding.FragmentAddToDoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.text.SimpleDateFormat
import java.util.Date

class AddToDoFragment : Fragment(R.layout.fragment_add_to_do) {

    private lateinit var navController: NavController
    private lateinit var contentBinding: FragmentAddToDoBinding
    private var color = -1
    private var todo:Todo? = null

//    private val viewModel: ToDoViewModel by activityViewModels {
//        ToDoViewModelFactory(
//            (activity?.application as InventoryApplication).database.todoDao()
//        )
//    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_to_do, container, false)
    }



}