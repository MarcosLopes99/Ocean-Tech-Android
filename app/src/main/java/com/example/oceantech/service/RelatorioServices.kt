package com.example.oceantech.service

import com.example.oceantech.models.Relatorio
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.http.Path

interface RelatorioInterface {

    @GET("relatorio/{relatorioEscolhido}")
    fun getRelatorios(
        @Path("relatorioEscolhido") relatorioEscolhido: String
    ): Call<List<Relatorio>>

}

class RelatorioConnection {

    private var retrofit = Retrofit.Builder()
        .baseUrl("https://demo5886765.mockable.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service = retrofit.create(RelatorioInterface::class.java)

}