package projects.school.comunication.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import projects.school.comunication.R
import projects.school.comunication.adapter.CoursesRecyclerAdapter
import projects.school.comunication.adapter.HomeParentRecyclerAdapter
import projects.school.comunication.model.Course

class CoursesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: CoursesRecyclerAdapter

    private val courses: List<Course> = listOf(
        Course("Header 1", 0, true, 5),
        Course("Header 2", 0, false, 100),
        Course("Header 3", 1, true, 1),
        Course("Header 4", 1, true, 0),
        Course("Header 5", 1, false, 0),
        Course("Header 6", 1, true, 34),
        Course("Header 7", 1, true, 89)
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_courses, container, false)

        val layout = LinearLayoutManager(context)
        recyclerAdapter = CoursesRecyclerAdapter(courses)

        recyclerView = view.findViewById(R.id.courses_recycler)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = layout

        return view
    }

}