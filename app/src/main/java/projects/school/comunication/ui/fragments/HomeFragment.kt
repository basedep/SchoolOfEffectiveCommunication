package projects.school.comunication.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import projects.school.comunication.R
import projects.school.comunication.adapter.HomeParentRecyclerAdapter
import projects.school.comunication.model.Course
import projects.school.comunication.model.HomeParentItem

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: HomeParentRecyclerAdapter

    /*private val childList: List<Course> = listOf(
        Course("Header 1", 0, true, null),
        Course("Header 2", 0, false, null),
        Course("Header 3", 1, true, null),
        Course("Header 4", 1, true, null),
        Course("Header 5", 1, false, null),
        Course("Header 6", 1, true, null),
        Course("Header 7", 1, true, null)
    )

    private val parentList: List<HomeParentItem> = listOf(
        HomeParentItem("TITLE 1", childList),
        HomeParentItem("TITLE 2", childList),
        HomeParentItem("TITLE 3", childList)
    )
*/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)


        val layout = LinearLayoutManager(context)
      /*  recyclerAdapter = HomeParentRecyclerAdapter(parentList)

        recyclerView = view.findViewById(R.id.home_recycler)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = layout*/


        return view
    }


}