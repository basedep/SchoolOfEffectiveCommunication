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

class CoursesFragment : BaseFragment() {

    override var bottomNavigationVisibility: Int = View.VISIBLE

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: CourseRecyclerAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_courses, container, false)

        val layout = LinearLayoutManager(context)
        recyclerAdapter = CourseRecyclerAdapter()

        recyclerView = view.findViewById(R.id.courses_recycler)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = layout

        return view
    }

}