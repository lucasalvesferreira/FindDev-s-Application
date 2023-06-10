package com.example.finddev

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class PerfilEmpresa : AppCompatActivity() {

    private lateinit var botaoVagasAbertas: Button
    private lateinit var botaoColaboradores: Button
    private lateinit var botaoContratar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_empresa)

        botaoVagasAbertas = findViewById(R.id.botaoVagasAbertas)
        botaoVagasAbertas.setOnClickListener {
            val intent = Intent(this, VagasPublicadasEmpresa::class.java)
            startActivity(intent)
        }
        botaoColaboradores = findViewById(R.id.botaoColaboradores)
        botaoColaboradores.setOnClickListener {
            val intent = Intent(this, Colaboradores::class.java)
            startActivity(intent)
        }
        botaoContratar = findViewById(R.id.botaoContratar)
        botaoContratar.setOnClickListener {
            val intent = Intent(this, Candidatos::class.java)
            startActivity(intent)
        }
    }

    fun telaEditarEmpresa(componente: View){
        val telaPerfilEmpresa= Intent(applicationContext, EdicaoPerfilEmpresa::class.java)

        startActivity(telaPerfilEmpresa)
    }
}