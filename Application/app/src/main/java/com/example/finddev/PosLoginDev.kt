package com.example.finddev

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class PosLoginDev : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pos_login_dev)
    }

    fun Encontre(componente: View){
        val telaBuscaVaga = Intent(applicationContext, BuscaVagaDev::class.java)

        startActivity(telaBuscaVaga)
    }

    fun perfil(componente: View){
        val telaPerfilDev = Intent(applicationContext, PerfilDev::class.java)

        startActivity(telaPerfilDev)
    }
}