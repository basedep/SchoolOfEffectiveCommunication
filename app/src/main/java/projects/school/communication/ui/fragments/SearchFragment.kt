package projects.school.communication.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_search.search_bar
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import projects.school.communication.R
import projects.school.communication.adapter.CourseRecyclerAdapter
import projects.school.communication.ui.activities.MainActivity
import projects.school.communication.utils.Constants.SEARCH_DELAY
import projects.school.communication.viewmodel.CourseViewModel
import java.time.Duration

class SearchFragment : BaseFragment() {

    override var bottomNavigationVisibility: Int = View.VISIBLE

    private lateinit var recycler: RecyclerView
    private lateinit var courseRecyclerAdapter: CourseRecyclerAdapter
    private lateinit var viewModel: CourseViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,  savedInstanceState: Bundle?): View? {
        viewModel = (activity as MainActivity).viewModel
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        recycler = view.findViewById(R.id.search_recycler)
        setupRecycler()

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        courseRecyclerAdapter.setOnItemClickListener{
            val bundle = Bundle().apply{
                putSerializable("courseObject", it)
            }

            findNavController().navigate(
                R.id.action_searchFragment_to_courseFragment, //action
                bundle    //передаем объект Article во фрагмент
            )
        }



        var job: Job? = null
        search_bar.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                job?.cancel()               //отменяем текущий Job и создаем новую
                job = MainScope().launch {
                    query?.let {
                        if (it.isNotEmpty())
                            viewModel.searchCourse(it)  //делаем запрос с задержкой в 500
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                job?.cancel()               //отменяем текущий Job и создаем новую
                job = MainScope().launch {
                    delay(SEARCH_DELAY)
                    newText.let {
                        if (it.isNotEmpty())
                            viewModel.searchCourse(it)  //делаем запрос с задержкой в 500
                    }
                }
                return true
            }
        })


        viewModel.searchCourse.observe(viewLifecycleOwner) { response ->
            courseRecyclerAdapter.differ.submitList(response)
        }


    }

    private fun setupRecycler(){
        courseRecyclerAdapter = CourseRecyclerAdapter()
        recycler.apply{
            adapter = courseRecyclerAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
    }


}