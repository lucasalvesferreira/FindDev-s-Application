package com.example.finddev.App.model.dtos

data class VagaRequest(
    val id_empresa: String,
    val titulo: String,
    val descricao: String,
    val funcao: String,
    val senioridade: String
)
