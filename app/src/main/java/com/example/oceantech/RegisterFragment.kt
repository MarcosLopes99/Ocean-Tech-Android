package com.example.oceantech

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.oceantech.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterFragment : Fragment() {

    private val ref = FirebaseAuth.getInstance()

    private var binding: FragmentRegisterBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.let {
            with(it) {

                btBackRegister.setOnClickListener {
                    findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                }

                btRegisterRegister.setOnClickListener {
                    val email = tieRegisterEmail.text.toString()
                    val password = tieRegisterPassword.text.toString()
                    val passwordConfirm = tieConfirmPassword.text.toString()

                    if (email.isBlank()) {
                        tilRegisterEmail.error = getString(R.string.field_empty_invalid)
                    } else {
                        tilRegisterEmail.isErrorEnabled = false
                    }

                    if (password.isBlank()) {
                        tilRegisterPassword.error = getString(R.string.field_empty_invalid)
                    } else {
                        tilRegisterPassword.isErrorEnabled = false
                    }

                    if (passwordConfirm.isBlank()) {
                        tilConfirmPassword.error = getString(R.string.field_empty_invalid)
                    } else {
                        tilConfirmPassword.isErrorEnabled = false
                    }

                    if (email.isNotBlank() && password.isNotBlank() && passwordConfirm.isNotBlank()) {

                        if (password == passwordConfirm) {

                            ref.createUserWithEmailAndPassword(
                                email.trim(),
                                password.trim()
                            ).addOnSuccessListener {

                                Toast.makeText(
                                    activity,
                                    getString(R.string.register_sucessful),
                                    Toast.LENGTH_SHORT
                                ).show()

                                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)

                            }.addOnFailureListener {
                                Toast.makeText(
                                    activity,
                                    getString(R.string.register_failed),
                                    Toast.LENGTH_LONG
                                ).show()
                            }

                        } else {
                            Toast.makeText(
                                activity,
                                getString(R.string.passwords_must_be_the_same),
                                Toast.LENGTH_SHORT
                            )
                                .show()
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