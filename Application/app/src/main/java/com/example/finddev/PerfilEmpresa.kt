package com.example.finddev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class PerfilEmpresa : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_empresa)
    }

    fun telaEditarEmpresa(componente: View){
        val telaPerfilEmpresa= Intent(applicationContext, EdicaoPerfilEmpresa::class.java)

        startActivity(telaPerfilEmpresa)
    }
}