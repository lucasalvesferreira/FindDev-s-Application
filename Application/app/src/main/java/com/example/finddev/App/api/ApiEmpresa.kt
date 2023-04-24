package com.example.finddev.App.api

import com.example.finddev.App.model.EmpresaModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiEmpresa {

    @GET("/empresa")
    fun getAllCompanys() : Call<List<EmpresaModel>>




}