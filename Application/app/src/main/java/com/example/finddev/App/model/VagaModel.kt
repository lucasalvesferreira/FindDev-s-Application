package com.example.finddev.App.model

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
    val frenteDesenvolvimento: String,
    val senioridade: String,
    var descricao:String
    )

data class VagaColaboradores(
    val id:Int,
    val imagem: Int,
    val titulo: String,
    val nome_dev: String? = null,
    val subtitulo: String? = null,
    val frenteDesenvolvimento: String,
    val senioridade: String,
    var descricao:String
)

data class VagaCandidato(
    val id:Int,
    val imagem: Int,
    val titulo: String,
    val nome_dev: String? = null,
    val frenteDesenvolvimento: String,
    val senioridade: String,
    var experiencia:String
)