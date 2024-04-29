package projects.school.communication.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import projects.school.communication.R
import projects.school.communication.adapter.CourseRecyclerAdapter
import projects.school.communication.ui.activities.MainActivity
import projects.school.communication.viewmodel.CourseViewModel

class HomeFragment : BaseFragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: CourseRecyclerAdapter
    private lateinit var viewModel: CourseViewModel

    override var bottomNavigationVisibility: Int = View.VISIBLE

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val layout = LinearLayoutManager(context)

        viewModel = (activity as MainActivity).viewModel   //view model from MainActivity

        recyclerAdapter = CourseRecyclerAdapter()

        //observe our courses and send list to adapter
        viewModel.allCourses.observe(viewLifecycleOwner) {
           recyclerAdapter.differ.submitList(it)
        }
        recyclerView = view.findViewById(R.id.home_recycler)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = layout

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerAdapter.setOnItemClickListener {
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