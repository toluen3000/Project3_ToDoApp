package com.example.project3_todoapp.Fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.project3_todoapp.MVVM.ToDoViewModel
import com.example.project3_todoapp.MVVM.Todo
import com.example.project3_todoapp.MainActivity
import com.example.project3_todoapp.R
import com.example.project3_todoapp.adapter.ToDoAdapter
import com.example.project3_todoapp.databinding.FragmentToDoBinding
import com.google.android.material.transition.MaterialElevationScale
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ToDoFragment : Fragment(R.layout.fragment_to_do) {

    private var toDobinding: FragmentToDoBinding? = null
    private val binding get() = toDobinding!!

    //private val toDosViewModel:ToDoViewModel by activityViewModels()
    private lateinit var toDosViewModel:ToDoViewModel
    private lateinit var todoAdapter: ToDoAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //menuprovider
//        val menuHost:MenuHost = requireActivity()
//        menuHost.addMenuProvider(this,viewLifecycleOwner,Lifecycle.State.RESUMED)
//        toDosViewModel = (activity as MainActivity).todoViewModel

        //toDobinding = FragmentToDoBinding.bind(view)
        toDosViewModel = (activity as MainActivity).todoViewModel
        setUpHomeRecyclerView()
//        val navController = Navigation.findNavController(view)
//        requireView()
//        CoroutineScope(Dispatchers.Main).launch {
//            delay(10)
//            activity.window.statusBarColor = Color.WHITE
//            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            activity.window.statusBarColor = Color.parseColor("#9E9D9D")
//        }

        toDobinding?.addNoteFab?.setOnClickListener {
            it.findNavController().navigate(R.id.action_addToDoFragment_to_toDoFragment)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        toDobinding = FragmentToDoBinding.inflate(inflater, container,false)
        return binding.root
    }

    private fun updateUI(todo:List<Todo>?){
        if (todo!= null){
            if(todo.isNotEmpty()){
                binding.recyclerView.visibility = View.VISIBLE
            }else{
                binding.recyclerView.visibility = View.GONE
            }
        }
    }

    private fun setUpHomeRecyclerView(){
        todoAdapter = ToDoAdapter()
        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter =todoAdapter
        }

        activity?.let {
            toDosViewModel.getAllTodos().observe(viewLifecycleOwner){
                todo -> todoAdapter.differ.submitList(todo)
                updateUI(todo)
            }
        }

    }



}