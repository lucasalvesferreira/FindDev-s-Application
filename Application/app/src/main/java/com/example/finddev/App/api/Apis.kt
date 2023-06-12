package com.example.finddev.App.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Apis {
    val BASE_URL = "http://44.214.40.119:8080/api/v1/"
    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    fun getApiUsuario(): ApiUsuario = retrofit.create(ApiUsuario::class.java)

    fun getApiDesenvolvedor(): ApiDesenvolvedor = retrofit.create(ApiDesenvolvedor::class.java)

    fun getApiEmpresa(): ApiEmpresa = retrofit.create(ApiEmpresa::class.java)

    fun getApiVagas(): ApiVagas = retrofit.create(ApiVagas::class.java)

    fun getApiCandidatura(): ApiCandidatura = retrofit.create(ApiCandidatura::class.java)

    fun getApiAvaliacao(): ApiAvaliacao = retrofit.create(ApiAvaliacao::class.java)
}
