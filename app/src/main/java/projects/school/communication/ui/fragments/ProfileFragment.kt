package projects.school.communication.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_profile.exitLinearLayout
import kotlinx.android.synthetic.main.fragment_profile.profileLoginButton
import kotlinx.android.synthetic.main.fragment_profile.profileSignInButton
import kotlinx.android.synthetic.main.fragment_profile.profile_exit_button
import kotlinx.android.synthetic.main.fragment_profile.registerLinearLayout
import kotlinx.android.synthetic.main.fragment_profile.user_email
import kotlinx.android.synthetic.main.fragment_profile.user_name
import kotlinx.android.synthetic.main.fragment_sign_in.signInButton
import projects.school.communication.R
import projects.school.communication.ui.activities.MainActivity
import projects.school.communication.utils.SessionUtil
import projects.school.communication.viewmodel.CourseViewModel

class ProfileFragment : BaseFragment() {

    override var bottomNavigationVisibility: Int = View.VISIBLE

    private var userSession: SessionUtil? = null
    private lateinit var viewModel: CourseViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = (activity as MainActivity).viewModel

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userSession = SessionUtil(view.context)
        val name = userSession?.getPreference("userName")
        val email = userSession?.getPreference("userEmail")
        if (name!!.isNotBlank() && email!!.isNotBlank()){
            user_name.text = name
            user_email.text = email
            registerLinearLayout.visibility = View.GONE
            exitLinearLayout.visibility = View.VISIBLE
        }else{
           baseProfileUIView()
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

        profile_exit_button.setOnClickListener {
            viewModel.deleteSession(userSession?.getPreference("sessionId").toString())
            userSession?.clearSessionPreferences()
            Toast.makeText(requireContext(), "Выход из аккаунта", Toast.LENGTH_SHORT).show()
            baseProfileUIView()
        }
    }

    private fun baseProfileUIView(){
        user_name.text = getString(R.string.name_placeholder)
        user_email.text = getString(R.string.email_placeholder)
        registerLinearLayout.visibility = View.VISIBLE
        exitLinearLayout.visibility = View.GONE
    }

}