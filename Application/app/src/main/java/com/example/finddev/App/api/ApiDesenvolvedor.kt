package com.example.finddev.App.api

import com.example.finddev.App.model.UsuarioModel
import com.example.finddev.App.model.dtos.PerfilDevRequest
import com.example.finddev.App.model.dtos.PerfilDevResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiDesenvolvedor {

    @POST("dev")
    fun createDeveloper(@Body usuarioModel: UsuarioModel): Call<UsuarioModel>

    @PUT("dev/{idUsuario}")
    fun atualizarPerfil(@Path("idUsuario") idUsuario: String, @Body request: PerfilDevRequest): Call<PerfilDevResponse>

}