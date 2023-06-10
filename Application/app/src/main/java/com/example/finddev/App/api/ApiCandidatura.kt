package com.example.finddev.App.api

import com.example.finddev.App.model.dtos.CandidaturaRequest
import com.example.finddev.App.model.dtos.CandidaturaResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiCandidatura {

    @POST("/candidaturas")
    fun createCandidatura(@Body candidatura: CandidaturaRequest): Call<CandidaturaResponse>
}