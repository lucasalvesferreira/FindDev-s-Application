package com.example.finddev.App.model.dtos

import java.io.Serializable

data class VagaModel(
    var id:Int,
    var titulo:String,
    var descricao:String
) : Serializable {
}