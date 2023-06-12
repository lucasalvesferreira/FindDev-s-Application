package com.example.finddev.App.model.dtos

import java.util.UUID

data class PerfilRequest(
    val idUsuario: UUID,
    val titulo: String,
    val descricao: String
)
