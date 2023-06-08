package com.example.finddev.App.model.dtos

import com.example.finddev.App.enums.FuncaoDev
import com.example.finddev.App.enums.SenioridadeDev
import com.example.finddev.App.model.UsuarioModel

data class VagaResponse(
    val id: Int,
    val desenvolvedor: UsuarioModel? = null,
    val titulo: String,
    val descricao: String,
    val funcao: String,
    val senioridade: String,
    val encerrado: Boolean? = null,
    val avaliado: Boolean? = null,
    val candidaturas: List<CandidaturaResponse> = emptyList()
)
