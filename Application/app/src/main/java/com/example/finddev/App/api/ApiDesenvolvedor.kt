package com.example.finddev.App.api

import com.example.finddev.App.model.DesenvolvedorModel
import com.example.finddev.App.model.dtos.LoginModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiDesenvolvedor {

    @GET("/dev")
    fun getAllDevelopers(): Call<List<DesenvolvedorModel>>

    @POST("/dev")
    fun createDeveloper(@Query("desenvolvedor") desenvolvedorModel: DesenvolvedorModel): Call<Unit>

    @POST("/user/login")
    fun logIn(@Query("login_model") loginModel: LoginModel) : Call<List<DesenvolvedorModel>>
}