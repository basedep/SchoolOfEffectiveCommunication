package projects.school.communication.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_profile.profileSignInButton
import kotlinx.android.synthetic.main.fragment_sign_in.signInButton
import projects.school.communication.R

class ProfileFragment : BaseFragment() {

    override var bottomNavigationVisibility: Int = View.VISIBLE


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileSignInButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_profileFragment_to_signInFragment,
                null)
        }

    }


}