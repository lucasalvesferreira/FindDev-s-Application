package com.example.finddev.App.api

import com.example.finddev.App.model.UsuarioModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiDesenvolvedor {

    @POST("dev")
    fun createDeveloper(@Body usuarioModel: UsuarioModel): Call<UsuarioModel>
}