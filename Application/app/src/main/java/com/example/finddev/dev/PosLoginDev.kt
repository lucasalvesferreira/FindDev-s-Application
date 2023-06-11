package com.example.finddev.dev

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.finddev.R

class posLoginDev : AppCompatActivity() {
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