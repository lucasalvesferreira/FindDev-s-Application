package com.example.finddev.App.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Apis {
    var BASE_URL = "https://ec2-44-214-40-119.compute-1.amazonaws.com:8080/api/v1"

    fun getApiDesenvolvedor() : ApiDesenvolvedor {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        return retrofit.create(ApiDesenvolvedor::class.java)
    }
}