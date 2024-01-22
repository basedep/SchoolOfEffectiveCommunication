package projects.school.communication.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_course.category_text
import kotlinx.android.synthetic.main.fragment_course.course_description_text
import kotlinx.android.synthetic.main.fragment_course.duration
import kotlinx.android.synthetic.main.fragment_course.imageView
import kotlinx.android.synthetic.main.fragment_course.passed_tests
import kotlinx.android.synthetic.main.fragment_course.titleText
import kotlinx.android.synthetic.main.fragment_course.watched_videos
import projects.school.communication.R
import projects.school.communication.adapter.VideoAdapter
import projects.school.communication.model.Course
import projects.school.communication.ui.activities.MainActivity
import projects.school.communication.viewmodel.CourseViewModel


class CourseFragment : BaseFragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: CourseViewModel
    private lateinit var recyclerAdapter: VideoAdapter
    private val args: CourseFragmentArgs by navArgs()

    override var bottomNavigationVisibility: Int = View.GONE

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_course, container, false)
        val layout = LinearLayoutManager(context)

        viewModel = (activity as MainActivity).viewModel   //view model from MainActivity

        val course = args.courseObject

        recyclerAdapter = VideoAdapter(course.video)

        recyclerView = view.findViewById(R.id.recycler)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = layout


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val course = args.courseObject


        recyclerAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("videoObject", it)
            }

            findNavController().navigate(R.id.action_courseFragment_to_videoActivity, bundle)
        }

            Picasso.with(context)
                .load(course.imageURL)
                .placeholder(R.drawable.ic_image_placeholder_24)
                .fit()
                .centerCrop()
                .into(imageView)

            setCourseContent(course)
    }


    private fun setCourseContent(course: Course) {
        titleText.text = course.header
        category_text.text = course.category
        duration.text = "${course.duration} мин"
        course_description_text.text = course.description
        watched_videos.text = course.watchedVideos.toString()
        passed_tests.text = course.passedTests.toString()
    }
}