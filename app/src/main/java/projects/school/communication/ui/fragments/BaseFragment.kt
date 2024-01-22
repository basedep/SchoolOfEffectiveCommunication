package projects.school.communication.ui.fragments

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import projects.school.communication.ui.activities.MainActivity

abstract class BaseFragment : Fragment() {

    protected open var bottomNavigationVisibility = View.VISIBLE
    private lateinit var mainActivity: MainActivity

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if(activity is MainActivity){
            mainActivity = activity as MainActivity
            mainActivity.setVisibilityForBottomNavigationView(bottomNavigationVisibility)
        }
    }

    override fun onStart() {
        super.onStart()
        mainActivity.setVisibilityForBottomNavigationView(bottomNavigationVisibility)
    }


}