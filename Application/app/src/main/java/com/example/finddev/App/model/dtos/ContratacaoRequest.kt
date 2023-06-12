package com.example.finddev.App.model.dtos

import java.util.UUID

data class ContratacaoRequest(
    val idVaga: Int,
    val idDesenvolvedor: UUID
)
