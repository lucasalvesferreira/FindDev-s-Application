package com.example.finddev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class ActivityCadastroStep1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_step1)
    }

    fun cadastroEmpresa(componente: View){
        val telaCadastroEmpresa = Intent(applicationContext, ActivityCadastroEmpresa::class.java)

        startActivity(telaCadastroEmpresa)
    }

    fun cadastroDev(componente:View){
        val telaCadastroDev = Intent(applicationContext, CadastroDev::class.java)

        startActivity(telaCadastroDev)
    }
}