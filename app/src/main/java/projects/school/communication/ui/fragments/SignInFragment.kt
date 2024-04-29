package projects.school.communication.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import io.appwrite.ID
import io.appwrite.exceptions.AppwriteException
import kotlinx.android.synthetic.main.fragment_sign_in.email
import kotlinx.android.synthetic.main.fragment_sign_in.name
import kotlinx.android.synthetic.main.fragment_sign_in.password
import kotlinx.android.synthetic.main.fragment_sign_in.phone
import kotlinx.android.synthetic.main.fragment_sign_in.signInButton
import kotlinx.coroutines.Job
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import projects.school.communication.R
import projects.school.communication.model.User
import projects.school.communication.ui.activities.MainActivity
import projects.school.communication.viewmodel.CourseViewModel
import java.util.UUID
import kotlin.coroutines.CoroutineContext

class SignInFragment : BaseFragment() {

    override var bottomNavigationVisibility: Int = View.GONE

    private lateinit var viewModel: CourseViewModel
    private var user: User? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = (activity as MainActivity).viewModel

        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signInButton.setOnClickListener {
            if (name.text?.isNotEmpty()!! and name.text?.isNotBlank()!! and
                email.text?.isNotEmpty()!! and email.text?.isNotBlank()!! and
                phone.text?.isNotEmpty()!! and phone.text?.isNotBlank()!! and
                password.text?.isNotEmpty()!! and password.text?.isNotBlank()!!
            ) {

                if (password.text?.length!! >= 8)
                    register()
                else
                    Toast.makeText(
                        view.context,
                        "Пароль должен быть не менее 8 символов",
                        Toast.LENGTH_SHORT
                    ).show()

            } else
                Toast.makeText(view.context, "Поля не заполнены", Toast.LENGTH_SHORT).show()
        }

    }


    private fun register() {

        user = User(
            fullName = name.text.toString(),
            phoneNumber = phone.text.toString(),
            email = email.text.toString(),
            password = password.text.toString()
        )

        lifecycleScope.launch {
            val register = viewModel.registerUser(user!!, ID.custom(UUID.randomUUID().toString()))
            register.join()

            if (!register.isCancelled) {
                Toast.makeText(requireContext(), "Аккаунт успешно создан", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_signInFragment_to_profileFragment2)
            } else
                Toast.makeText(requireContext(), "Такой пользователь уже существует", Toast.LENGTH_SHORT).show()
        }
    }


}
