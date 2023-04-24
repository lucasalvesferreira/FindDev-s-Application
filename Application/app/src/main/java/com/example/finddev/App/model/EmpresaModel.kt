package com.example.finddev.App.model

import java.util.UUID

data class EmpresaModel(

    val id:UUID,
    val nome:String,
    val email:String,
    val senha:String,
    val estado:String,
    val cidade:String,
    val telefone:String,
    val bairro:String,
    val endereco:String,
    val cnpj:String,
    val perfil:PerfilModel
)
