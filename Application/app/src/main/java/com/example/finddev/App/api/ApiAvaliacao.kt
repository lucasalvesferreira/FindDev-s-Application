package com.example.finddev.App.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.UUID

interface ApiAvaliacao {

    @GET("avaliacoes/media/{idUser}")
    fun getMediaAvaliacoes(@Path("idUser") idUser: UUID): Call<Double>
}