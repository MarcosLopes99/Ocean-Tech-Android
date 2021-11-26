package com.example.oceantech

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.oceantech.adapter.RelatoriosAdapter
import com.example.oceantech.databinding.FragmentResultBinding
import com.example.oceantech.models.Relatorio
import com.example.oceantech.service.RelatorioConnection
import kotlinx.android.synthetic.main.fragment_result.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResultFragment : Fragment() {

    private var binding: FragmentResultBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        Toast.makeText(activity, getString(R.string.loading_data), Toast.LENGTH_SHORT).show()
        getRelatorios()

        super.onViewCreated(view, savedInstanceState)

        binding?.let {
            with(it) {

                btBackResult.setOnClickListener {
                    findNavController()
                        .navigate(
                            R.id.action_resultFragment_to_searchFragment
                        )
                }

            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null

    }

    private fun getRelatorios() {

        var relatorioEscolhido = arguments?.getString("relatorioEscolhido")

        if (relatorioEscolhido != null) {

            when (relatorioEscolhido) {
                "Todas" -> {
                    relatorioEscolhido = ""
                }
                "Belém" -> {
                    relatorioEscolhido = "belem"
                }
                "Recife" -> {
                    relatorioEscolhido = "recife"
                }
                "Salvador" -> {
                    relatorioEscolhido = "salvador"
                }
            }

            RelatorioConnection().service.getRelatorios(relatorioEscolhido)
                .enqueue(object : Callback<List<Relatorio>> {

                    override fun onResponse(
                        call: Call<List<Relatorio>>,
                        response: Response<List<Relatorio>>
                    ) {

                        if (!response.isSuccessful) {
                            Toast.makeText(
                                activity,
                                getString(R.string.failed_search),
                                Toast.LENGTH_SHORT
                            )
                                .show()
                            return
                        }

                        showData(response.body()!!)

                    }

                    override fun onFailure(call: Call<List<Relatorio>>, t: Throwable) {
                        Toast.makeText(
                            activity,
                            getString(R.string.failed_search),
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                })
        } else {
            Toast.makeText(activity, getString(R.string.failed_search), Toast.LENGTH_SHORT).show()
        }

    }

    private fun showData(relatorios: List<Relatorio>) {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = RelatoriosAdapter(relatorios)
        }
    }

}