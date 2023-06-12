package com.example.finddev.App.api

import com.example.finddev.App.model.dtos.VagaRequest
import com.example.finddev.App.model.dtos.VagaResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.UUID

interface ApiVagas {

    @POST("vagas")
    fun createVaga(@Body vagaRequest: VagaRequest): Call<VagaResponse>

    @GET("vagas/busca-filtrada/{funcao}/{senioridade}")
    fun getVagaFiltrada(
        @Path("funcao") funcao: String,
        @Path("senioridade") senioridade: String
    ): Call<List<VagaResponse>>

    @GET("vagas/empresa/{idEmpresa}")
    fun getVagasEmpresa(@Path("idEmpresa") idEmpresa: UUID): Call<List<VagaResponse>>
}
