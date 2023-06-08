package com.example.finddev.App.model.dtos

data class VagaRequest(
    val idEmpresa: String,
    val titulo: String,
    val descricao: String,
    val funcao: String,
    val senioridade: String
)
