package projects.school.communication.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import projects.school.communication.R
import projects.school.communication.adapter.CourseRecyclerAdapter

class SearchFragment : Fragment() {

    private lateinit var recycler: RecyclerView
    private lateinit var courseRecyclerAdapter: CourseRecyclerAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,  savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_search, container, false)

        recycler = view.findViewById(R.id.search_recycler)
        courseRecyclerAdapter = CourseRecyclerAdapter()
        //courseRecyclerAdapter.differ.submitList()
        recycler.adapter = courseRecyclerAdapter
        recycler.layoutManager = GridLayoutManager(context, 2)

        return view
    }

}