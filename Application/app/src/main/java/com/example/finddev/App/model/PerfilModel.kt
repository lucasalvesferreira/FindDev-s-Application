package com.example.finddev.App.model

import com.example.finddev.App.enums.StatusPerfil

import java.io.Serializable

data class Perfil(
    val idPerfil:Int,
    val titulo: String,
    val descricao: String,
    val biografia: String,
    val experiencia: String,
    val fotoPerfil: Int,
    val avaliacao: Float
) : Serializable

data class PerfilModel (
    val idPerfil:Int,
    val titulo:String,
    val descricao:String,
    val status:StatusPerfil,
)