package projects.school.communication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import io.appwrite.ID
import kotlinx.android.synthetic.main.fragment_sign_in.email
import kotlinx.android.synthetic.main.fragment_sign_in.name
import kotlinx.android.synthetic.main.fragment_sign_in.password
import kotlinx.android.synthetic.main.fragment_sign_in.phone
import kotlinx.android.synthetic.main.fragment_sign_in.signInButton
import projects.school.communication.R
import projects.school.communication.model.User
import projects.school.communication.ui.activities.MainActivity
import projects.school.communication.viewmodel.CourseViewModel
import java.util.UUID

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
            register()
        }

    }


    private fun register() {
        if (name.text?.isNotEmpty()!! and name.text?.isNotBlank()!! and
            email.text?.isNotEmpty()!! and email.text?.isNotBlank()!! and
            phone.text?.isNotEmpty()!! and phone.text?.isNotBlank()!! and
            password.text?.isNotEmpty()!! and password.text?.isNotBlank()!!
        ) {
            user = User(
                fullName = name.text.toString(),
                phoneNumber = phone.text.toString(),
                email = email.text.toString(),
                password = password.text.toString(),
                courses = null
            )

            viewModel.registerUser(user!!, ID.custom(UUID.randomUUID().toString()))
            viewModel.isRegisterSuccessful.observe(viewLifecycleOwner){ status ->
                status?.let{
                    viewModel.isRegisterSuccessful.value = null

                    if (it) {
                        findNavController().navigate(R.id.action_signInFragment_to_profileFragment2)
                        Toast.makeText(
                            requireContext(),
                            "Аккаунт успешно создан",
                            Toast.LENGTH_SHORT
                        ).show()
                    }else{
                        Toast.makeText(requireContext(), "Такой пользователь уже существует", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }
    }



}