package com.example.finddev

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class posLoginEmpresa : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pos_login_empresa)
    }

    fun redirecionarCriacaoVagas(componente: View){
        val criacaoDeVagas = Intent(applicationContext, PublicacaoDeVagas::class.java)
        startActivity(criacaoDeVagas)
    }

    fun redirecionarPerfil(componente: View){
        val criacaoDeVagas = Intent(applicationContext, PublicacaoDeVagas::class.java)
        startActivity(criacaoDeVagas)
    }
}