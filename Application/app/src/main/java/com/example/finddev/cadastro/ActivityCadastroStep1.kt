package com.example.finddev.cadastro

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.finddev.R
import com.example.finddev.dev.CadastroDev
import com.example.finddev.empresa.CadastroEmpresa

class ActivityCadastroStep1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_step1)
    }

    fun cadastroEmpresa(componente: View){
        val telaCadastroEmpresa = Intent(applicationContext, CadastroEmpresa::class.java)

        startActivity(telaCadastroEmpresa)
    }

    fun cadastroDev(componente:View){
        val telaCadastroDev = Intent(applicationContext, CadastroDev::class.java)

        startActivity(telaCadastroDev)
    }
}