package com.example.finddev.App.model.dtos

import java.io.Serializable

data class VagaModel(
    var id:Int,
    var titulo:String,
    var descricao:String,
    var valor:Double
) : Serializable {
}

data class Vaga(
    val id:Int,
    val imagem: Int,
    val titulo: String,
    val subtitulo: String,
    val valor: String,
    val frenteDesenvolvimento: String,
    val senioridade: String,
    var descricao:String
    )