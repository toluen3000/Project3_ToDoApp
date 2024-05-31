package com.example.project3_todoapp.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import com.example.notesroompractice.R
import com.example.notesroompractice.databinding.FragmentAddTodoBinding
import com.example.project3_todoapp.MainActivity
import com.example.project3_todoapp.mvvm.Todo
import com.example.project3_todoapp.mvvm.ToDoViewModel
import java.text.SimpleDateFormat
import java.util.Calendar

class FragmentAddTodo : Fragment(R.layout.fragment_add_todo), MenuProvider {


    private  var addToDoBinding: FragmentAddTodoBinding? = null
    @SuppressLint("SimpleDateFormat")
    private var dateType = SimpleDateFormat("dd/MM/yyyy hh:mm:ss a")
    private val binding get() = addToDoBinding !!
    private val calendar = Calendar.getInstance()
    private lateinit var toDosViewModel: ToDoViewModel
    private lateinit var addToDoView: View

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
        addToDoBinding = FragmentAddTodoBinding.inflate(inflater)

        return binding.root
        //return inflater.inflate(R.layout.fragment_add_to_do, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost:MenuHost = requireActivity()
        menuHost.addMenuProvider(this,viewLifecycleOwner,Lifecycle.State.RESUMED)
        toDosViewModel = (activity as MainActivity).todoViewModel
        addToDoView = view
    }

    private fun saveTodo(view: View){
        val todoTile = binding.edtTitle.text.toString().trim()
        val todoDescription = binding.edtDesciption.toString().trim()
        val timeNow = calendar.time

        val time = dateType.format(timeNow).toString().trim()


        if (todoTile.isNotEmpty()){
           // val todo = Todo(0,todoTile,todoDescription,time)
            //k chạy thì dùng cả id
            toDosViewModel.addNewToDo(todoTile,todoDescription,time)

            Toast.makeText(addToDoView.context,"SAVED",Toast.LENGTH_SHORT).show()
            view.findNavController().popBackStack(R.id.fragmentTodoHome,false)
        }else{
            Toast.makeText(addToDoView.context,"Please enter the title",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.add_menu,menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId){
            R.id.saveTodo -> {
                saveTodo(addToDoView)
                true
            }
            else -> false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        addToDoBinding = null
    }


}



