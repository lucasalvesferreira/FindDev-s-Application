package com.example.finddev.App.model

import com.example.finddev.App.enums.StatusPerfil

data class PerfilModel (
    val idPerfil:Int,
    val titulo:String,
    val descricao:String,
    val status:StatusPerfil,
)