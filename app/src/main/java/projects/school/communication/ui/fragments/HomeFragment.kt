package projects.school.communication.ui.fragments

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import projects.school.communication.R
import projects.school.communication.adapter.CourseRecyclerAdapter
import projects.school.communication.adapter.HomeParentRecyclerAdapter
import projects.school.communication.model.Course
import projects.school.communication.model.HomeParentItem
import projects.school.communication.ui.activities.MainActivity
import projects.school.communication.viewmodel.CourseViewModel

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: HomeParentRecyclerAdapter
    private lateinit var childRecyclerAdapter: CourseRecyclerAdapter
    private lateinit var viewModel: CourseViewModel
    private var parentList: MutableList<HomeParentItem> = mutableListOf(
        HomeParentItem("Работа")
    )


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val layout = LinearLayoutManager(context)

        viewModel = (activity as MainActivity).viewModel   //view model from MainActivity

        //адаптер для дочернего листа
        childRecyclerAdapter = CourseRecyclerAdapter()

        //observe our courses and send list to adapter
        viewModel.allCourses.observe(viewLifecycleOwner) {
           childRecyclerAdapter.differ.submitList(it)

        }

        //адаптер для листа родителя
        recyclerAdapter = HomeParentRecyclerAdapter(parentList, childRecyclerAdapter)

        recyclerView = view.findViewById(R.id.home_recycler)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = layout


        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        childRecyclerAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("courseObject", it)
            }

            findNavController().navigate(
                R.id.action_homeFragment_to_courseFragment,
                bundle
            )
        }
    }

}