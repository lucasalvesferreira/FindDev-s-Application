package com.example.finddev.App.model

import com.example.finddev.App.enums.Planos
import java.util.*

data class DesenvolvedorModel(
    val id: UUID? = null,
    val nome:String,
    val email:String,
    val senha:String,
    val estado:String,
    val cidade:String,
    val telefone:String,
    val planoAssinatura: Planos? = null,
    val cpf: String
)
