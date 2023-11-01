package projects.school.comunication.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import projects.school.comunication.R
import projects.school.comunication.adapter.SearchRecyclerAdapter
import projects.school.comunication.model.HomeChildItem

class SearchFragment : Fragment() {

    private lateinit var recycler: RecyclerView
    private lateinit var searchAdapter: SearchRecyclerAdapter

    private val dataset = listOf<HomeChildItem>(
        HomeChildItem("Header title 1", R.drawable.im1, false),
        HomeChildItem("Header title 2", R.drawable.im2, false),
        HomeChildItem("Header title 3", R.drawable.im3, false),
        HomeChildItem("Header title 4", R.drawable.im4, false),
        HomeChildItem("Header title 5", R.drawable.im5, false),
        HomeChildItem("Header title 6", R.drawable.im6, false),
        HomeChildItem("Header title 7", R.drawable.im3, false),
        HomeChildItem("Header title 8", R.drawable.im4, false)
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,  savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_search, container, false)

        recycler = view.findViewById(R.id.search_recycler)
        searchAdapter = SearchRecyclerAdapter(dataset)
        recycler.adapter = searchAdapter
        recycler.layoutManager = GridLayoutManager(context, 2)

        return view
    }

}