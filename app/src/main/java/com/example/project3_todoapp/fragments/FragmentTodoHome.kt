import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notesroompractice.R
import com.example.notesroompractice.databinding.FragmentTodoHomeBinding
import com.example.project3_todoapp.MainActivity
import com.example.project3_todoapp.mvvm.Todo
import com.example.project3_todoapp.adapter.ToDoAdapter
import com.example.project3_todoapp.mvvm.ToDoViewModel


class FragmentTodoHome : Fragment(R.layout.fragment_todo_home) {

    private var toDobinding: FragmentTodoHomeBinding? = null
    private val binding get() = toDobinding!!

    //private val toDosViewModel:ToDoViewModel by activityViewModels()
    private lateinit var toDosViewModel: ToDoViewModel
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
            it.findNavController().navigate(R.id.action_fragmentTodoHome_to_fragmentAddTodo)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        toDobinding = FragmentTodoHomeBinding.inflate(inflater, container,false)
        return binding.root
    }

    private fun updateUI(listTodo :List<Todo>?){
        if (listTodo!= null){
            if(listTodo.isNotEmpty()){
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
                    allTodo -> todoAdapter.differ.submitList(allTodo)
                updateUI(allTodo)
            }
        }

    }



}

