package com.example.finddev.App.api

import com.example.finddev.App.model.EmpresaModel
import com.example.finddev.App.model.dtos.LoginModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiLogin {


    @GET("usuarios")
    fun getPorLoginSenha(@Query("login") email:LoginModel) : Call<List<EmpresaModel>>
}