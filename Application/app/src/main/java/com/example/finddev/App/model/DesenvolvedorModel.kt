package com.example.finddev.App.model

import com.example.finddev.App.enums.Planos
import java.util.*

data class DesenvolvedorModel(
    val id: UUID,
    val nome:String,
    val email:String,
    val senha:String,
    val estado:String,
    val cidade:String,
    val telefone:String,
    val planoAssinatura: Planos,
    val cpf: String
)
