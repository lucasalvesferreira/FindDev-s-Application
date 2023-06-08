package com.example.finddev.App.api

import com.example.finddev.App.model.UsuarioModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiEmpresa {
    @POST("empresa")
    fun createEmpresa(@Body usuarioModel: UsuarioModel): Call<UsuarioModel>
}