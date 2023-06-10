package com.example.finddev.App.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Apis {
    val BASE_URL = "http://44.214.40.119:8080/api/v1/"

    fun getApiUsuario(): ApiUsuario {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        return retrofit.create(ApiUsuario::class.java)
    }

    fun getApiDesenvolvedor(): ApiDesenvolvedor {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        return retrofit.create(ApiDesenvolvedor::class.java)
    }

    fun getApiEmpresa(): ApiEmpresa {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        return retrofit.create(ApiEmpresa::class.java)
    }

    fun getApiVagas(): ApiVagas {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        return retrofit.create(ApiVagas::class.java)
    }

    fun getApiCandidatura(): ApiCandidatura {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        return retrofit.create(ApiCandidatura::class.java)
    }
}