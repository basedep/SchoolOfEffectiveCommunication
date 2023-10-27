package projects.school.comunication.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import projects.school.comunication.R
import projects.school.comunication.adapter.HomeParentRecyclerAdapter
import projects.school.comunication.model.HomeChildItem
import projects.school.comunication.model.HomeParentItem

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: HomeParentRecyclerAdapter

    private val childList: List<HomeChildItem> = listOf(
        HomeChildItem("Header 1", "", true),
        HomeChildItem("Header 2", "", false),
        HomeChildItem("Header 3", "", true),
        HomeChildItem("Header 4", "", true),
        HomeChildItem("Header 5", "", false),
        HomeChildItem("Header 6", "", true),
        HomeChildItem("Header 7", "", true)
    )

    private val parentList: List<HomeParentItem> = listOf(
        HomeParentItem("TITLE 1", childList),
        HomeParentItem("TITLE 2", childList),
        HomeParentItem("TITLE 3", childList)
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)


        val layout = LinearLayoutManager(context)
        recyclerAdapter = HomeParentRecyclerAdapter(parentList)

        recyclerView = view.findViewById(R.id.home_recycler)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = layout


        return view
    }


}