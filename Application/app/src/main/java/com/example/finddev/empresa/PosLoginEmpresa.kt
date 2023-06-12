package com.example.finddev.empresa

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.finddev.R

class PosLoginEmpresa : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pos_login_empresa)
    }

    fun redirecionarCriacaoVagas(componente: View){
        val criacaoDeVagas = Intent(applicationContext, PublicacaoDeVagas::class.java)
        startActivity(criacaoDeVagas)
    }

    fun redirecionarPerfil(componente: View){
        val perfilEmpresa = Intent(applicationContext, PerfilEmpresa::class.java)
        startActivity(perfilEmpresa)
    }
}