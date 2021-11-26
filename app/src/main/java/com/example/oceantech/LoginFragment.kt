package com.example.oceantech

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.oceantech.databinding.FragmentLoginBinding
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    private val ref = FirebaseAuth.getInstance()

    private var binding: FragmentLoginBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.let {
            with(it) {

                btRegister.setOnClickListener {
                    findNavController()
                        .navigate(
                            R.id.action_loginFragment_to_registerFragment
                        )
                }

                btLogin.setOnClickListener {
                    val email = tieLoginEmail.text.toString()
                    val password = tieLoginPassword.text.toString()

                    if (email.isBlank()) {
                        tilLoginEmail.error = getString(R.string.field_empty_invalid)
                    } else {
                        tilLoginEmail.isErrorEnabled = false
                    }

                    if (password.isBlank()) {
                        tilLoginPassword.error = getString(R.string.field_empty_invalid)
                    } else {
                        tilLoginPassword.isErrorEnabled = false
                    }

                    if (email.isNotBlank() && password.isNotBlank()) {

                        ref.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                            findNavController()
                                .navigate(
                                    R.id.action_loginFragment_to_mainFragment
                                )
                        }.addOnFailureListener {
                            Toast.makeText(
                                activity,
                                getString(R.string.email_and_or_password_incorrect),
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    }

                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}