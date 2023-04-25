package com.example.finddev.App.api

import com.example.finddev.App.model.UsuarioModel
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiDesenvolvedor {

    @POST("/dev")
    fun createDeveloper(
        @Query("desenvolvedor") usuarioModel: UsuarioModel
    ): Call<List<UsuarioModel>>
}