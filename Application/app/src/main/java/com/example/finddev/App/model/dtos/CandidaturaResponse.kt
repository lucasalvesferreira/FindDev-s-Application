package com.example.finddev.App.model.dtos

import com.example.finddev.App.model.UsuarioModel

data class CandidaturaResponse(
    val idCandidatura: Int,
    val desenvolvedor: UsuarioModel,
    val idVaga: Int
)
