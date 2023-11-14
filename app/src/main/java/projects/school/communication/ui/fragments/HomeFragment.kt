package projects.school.communication.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import projects.school.communication.R
import projects.school.communication.adapter.CourseRecyclerAdapter
import projects.school.communication.adapter.HomeParentRecyclerAdapter
import projects.school.communication.model.Course
import projects.school.communication.model.HomeParentItem

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: HomeParentRecyclerAdapter
    private lateinit var childRecyclerAdapter: CourseRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val layout = LinearLayoutManager(context)

        //адаптер для дочернего листа
        childRecyclerAdapter = CourseRecyclerAdapter()
        childRecyclerAdapter.differ.submitList(listOf()) //отправляем в него лист

        //адаптер для листа родителя
        recyclerAdapter = HomeParentRecyclerAdapter(listOf(), childRecyclerAdapter)

        recyclerView = view.findViewById(R.id.home_recycler)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = layout


        return view
    }

}