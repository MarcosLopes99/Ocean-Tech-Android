package com.example.oceantech

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.oceantech.databinding.FragmentSearchBinding
import com.google.firebase.auth.FirebaseAuth

class SearchFragment : Fragment() {

    private var binding: FragmentSearchBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.let {
            with(it) {

                val relatorioType = resources.getStringArray(R.array.relatorio_type)
                val arrayAdapterRelatorioType =
                    ArrayAdapter(requireContext(), R.layout.dropdown_relatorio_type, relatorioType)
                binding!!.actvSelectRelatorio.setAdapter(arrayAdapterRelatorioType)

                btSearchSearch.setOnClickListener {
                    val relatorioEscolhido = actvSelectRelatorio.text
                    if (relatorioEscolhido.isEmpty()) {
                        Toast.makeText(
                            activity,
                            getString(R.string.choose_location),
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    } else {
                        val bundle = bundleOf("relatorioEscolhido" to relatorioEscolhido.toString())
                        findNavController()
                            .navigate(
                                R.id.action_searchFragment_to_resultFragment, bundle
                            )
                    }
                }

                bottomNavigation.menu.findItem(R.id.navSearch).isChecked = true

                bottomNavigation.setOnNavigationItemSelectedListener { item ->
                    when (item.itemId) {
                        R.id.navHome -> {
                            findNavController()
                                .navigate(
                                    R.id.action_searchFragment_to_mainFragment
                                )
                            true
                        }
                        R.id.navSearch -> {
                            true
                        }
                        R.id.navInfo -> {
                            findNavController()
                                .navigate(
                                    R.id.action_searchFragment_to_infoFragment
                                )
                            true
                        }
                        R.id.navExit -> {

                            FirebaseAuth.getInstance().signOut()

                            findNavController()
                                .navigate(
                                    R.id.action_searchFragment_to_loginFragment
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