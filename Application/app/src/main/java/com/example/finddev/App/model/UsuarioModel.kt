package com.example.finddev.App.model

import com.example.finddev.App.enums.Planos
import java.util.UUID

data class UsuarioModel(
    val id: String? = null,
    val nome:String,
    val email:String,
    val senha:String,
    val estado:String,
    val cidade:String,
    val telefone:String,
    val planoAssinatura: Planos? = null,
    val cpf: String? = null,
    val bairro:String? = null,
    val endereco:String? = null,
    val cnpj:String? = null,
    val perfil:PerfilModel? = null,
)
