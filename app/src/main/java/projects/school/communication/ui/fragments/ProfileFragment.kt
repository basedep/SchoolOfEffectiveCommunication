package projects.school.communication.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_profile.profileLoginButton
import kotlinx.android.synthetic.main.fragment_profile.profileSignInButton
import kotlinx.android.synthetic.main.fragment_profile.user_email
import kotlinx.android.synthetic.main.fragment_profile.user_name
import kotlinx.android.synthetic.main.fragment_sign_in.signInButton
import projects.school.communication.R
import projects.school.communication.utils.SessionUtil

class ProfileFragment : BaseFragment() {

    override var bottomNavigationVisibility: Int = View.VISIBLE


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userSession = SessionUtil(view.context)
        val name = userSession.getPreference("userName")
        val email = userSession.getPreference("userEmail")
        if (name.isNotBlank() && email.isNotBlank()){
            user_name.text = name
            user_email.text = email
        }

        profileSignInButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_profileFragment_to_signInFragment,
                null)
        }

        profileLoginButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_profileFragment_to_logInFragment,
                null
            )
        }

    }


}