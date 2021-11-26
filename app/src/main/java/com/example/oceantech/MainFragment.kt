package com.example.oceantech

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.oceantech.databinding.FragmentMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainFragment : Fragment() {

    private var binding: FragmentMainBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.let {
            with(it) {

                bottomNavigation.menu.findItem(R.id.navHome).isChecked = true

                bottomNavigation.setOnNavigationItemSelectedListener { item ->
                    when (item.itemId) {
                        R.id.navHome -> {
                            true
                        }
                        R.id.navSearch -> {
                            findNavController()
                                .navigate(
                                    R.id.action_mainFragment_to_searchFragment
                                )
                            true
                        }
                        R.id.navInfo -> {
                            findNavController()
                                .navigate(
                                    R.id.action_mainFragment_to_infoFragment
                                )
                            true
                        }
                        R.id.navExit -> {

                            FirebaseAuth.getInstance().signOut()

                            findNavController()
                                .navigate(
                                    R.id.action_mainFragment_to_loginFragment
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