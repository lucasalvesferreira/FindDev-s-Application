package com.example.finddev.App.api

import com.example.finddev.App.model.UsuarioModel
import com.example.finddev.App.model.dtos.LoginModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiUsuario {

    @POST("user/login")
    fun logIn(
        @Body loginModel: LoginModel) : Call<UsuarioModel>
}