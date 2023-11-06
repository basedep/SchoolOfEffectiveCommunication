package projects.school.communication.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import projects.school.communication.R
import projects.school.communication.adapter.HomeParentRecyclerAdapter

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: HomeParentRecyclerAdapter
    //private lateinit var database: Databases

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

        /*val client = container?.context?.let {
            io.appwrite.Client(it)
                .setEndpoint("https://cloud.appwrite.io/v1")
                .setProject("654657b465128136b2a3")
        }*/

        //database = client?.let { Databases(it) }!!

       // Log.d("myLog", "onCreateView: ${getData(database)}" )

      /*  recyclerAdapter = HomeParentRecyclerAdapter(parentList)

        recyclerView = view.findViewById(R.id.home_recycler)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = layout*/


        return view
    }

   /* private fun getData(db: Databases) = GlobalScope.launch{
            getlist(db)
    }

    suspend fun getlist(db: Databases): DocumentList<Map<String, Any>>{

        val list: List<String> = listOf("header", "description")
        return db.listDocuments("654658b043def3aebce3","654659d1856d03067b7f",
            listOf(Query.select(list))
        )
    }*/


}