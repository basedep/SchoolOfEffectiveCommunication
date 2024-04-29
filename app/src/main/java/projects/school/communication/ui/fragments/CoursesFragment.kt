package projects.school.communication.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_courses.animation_empty
import projects.school.communication.R
import projects.school.communication.adapter.CourseRecyclerAdapter
import projects.school.communication.ui.activities.MainActivity
import projects.school.communication.utils.SessionUtil
import projects.school.communication.viewmodel.CourseViewModel

class CoursesFragment : BaseFragment() {

    override var bottomNavigationVisibility: Int = View.VISIBLE

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: CourseRecyclerAdapter
    private lateinit var viewModel: CourseViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_courses, container, false)

        viewModel = (activity as MainActivity).viewModel

        val layout = LinearLayoutManager(context)
        recyclerAdapter = CourseRecyclerAdapter()

        recyclerView = view.findViewById(R.id.courses_recycler)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = layout

        return view
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllUserAddedCourses(SessionUtil(requireContext()).getPreference("userId"))

        viewModel.allUserAddedCourses.observe(viewLifecycleOwner) {
            recyclerAdapter.differ.submitList(it)

            if (it.isNotEmpty())
                animation_empty.visibility = View.GONE
            else
                animation_empty.visibility = View.VISIBLE
        }

        recyclerAdapter.setOnItemClickListener {
                val bundle = Bundle().apply {
                    putSerializable("courseObject", it)
                }

                findNavController().navigate(
                    R.id.action_coursesFragment_to_courseFragment,
                    bundle
                )
            }
        }

}