package projects.school.communication.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import io.appwrite.exceptions.AppwriteException
import io.appwrite.models.Session
import kotlinx.android.synthetic.main.fragment_log_in.email
import kotlinx.android.synthetic.main.fragment_log_in.logInButton
import kotlinx.android.synthetic.main.fragment_log_in.password
import kotlinx.coroutines.launch
import projects.school.communication.R
import projects.school.communication.model.User
import projects.school.communication.ui.activities.MainActivity
import projects.school.communication.utils.SessionUtil
import projects.school.communication.viewmodel.CourseViewModel

class LogInFragment : BaseFragment() {

    override var bottomNavigationVisibility: Int = View.GONE

    private lateinit var viewModel: CourseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = (activity as MainActivity).viewModel

        return inflater.inflate(R.layout.fragment_log_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        logInButton.setOnClickListener {
            if (email.text?.isNotEmpty()!! and email.text?.isNotBlank()!! and
                password.text?.isNotEmpty()!! and password.text?.isNotBlank()!!
            ) {
                login()
            } else
                Toast.makeText(requireContext(), "Неполные данные для входа", Toast.LENGTH_SHORT)
                    .show()
        }
    }

    private fun login() {

        lifecycleScope.launch {
            try {
                val loginUser = viewModel.loginUser(email.text.toString(), password.text.toString())
                val userSession = loginUser.await() as Session
                Log.d("communication", "login: $userSession")

                val requiredUserData = viewModel.getUserData(userSession.userId)

                val userData = requiredUserData.await()
                Log.d("communication", "login: ${userData.fullName}")
                SessionUtil(requireContext()).saveSessionPreferences(
                    userSession.id,
                    userSession.userId,
                    userData.fullName,
                    userData.email
                )
            } catch (e: Exception) {

                when (e) {
                    is AppwriteException, is ClassCastException -> {
                        Toast.makeText(requireContext(), "Пользователь не найден", Toast.LENGTH_SHORT).show()
                        Log.d("communication", "login exception: $e") }
                }
            }
        }
    }


}