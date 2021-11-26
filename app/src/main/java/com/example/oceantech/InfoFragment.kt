package com.example.oceantech

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.oceantech.databinding.FragmentInfoBinding
import com.google.firebase.auth.FirebaseAuth

class InfoFragment : Fragment() {

    private var binding: FragmentInfoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.let {
            with(it) {

                bottomNavigation.menu.findItem(R.id.navInfo).isChecked = true

                bottomNavigation.setOnNavigationItemSelectedListener { item ->
                    when (item.itemId) {
                        R.id.navHome -> {
                            findNavController()
                                .navigate(
                                    R.id.action_infoFragment_to_mainFragment
                                )
                            true
                        }
                        R.id.navSearch -> {
                            findNavController()
                                .navigate(
                                    R.id.action_infoFragment_to_searchFragment
                                )
                            true
                        }
                        R.id.navInfo -> {
                            true
                        }
                        R.id.navExit -> {

                            FirebaseAuth.getInstance().signOut()

                            findNavController()
                                .navigate(
                                    R.id.action_infoFragment_to_loginFragment
                                )
                            true
                        }
                        else -> false
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