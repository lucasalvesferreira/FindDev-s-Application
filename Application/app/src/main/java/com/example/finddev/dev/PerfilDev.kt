package com.example.finddev.dev

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.finddev.R

class PerfilDev : AppCompatActivity() {

    private lateinit var botaoColaboradores: Button
    private lateinit var tvEstado: TextView
    private lateinit var tvCidade: TextView
    private lateinit var tvNomeCompleto: TextView
    private lateinit var resumo: TextView
    private lateinit var biografia: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_dev)

        botaoColaboradores = findViewById(R.id.botaoColaboradores)
        tvEstado = findViewById(R.id.tvEstado)
        tvCidade = findViewById(R.id.tvCidade)
        tvNomeCompleto = findViewById(R.id.tvNomeCompleto)
        resumo = findViewById(R.id.resumo)
        biografia = findViewById(R.id.biografia)

        botaoColaboradores.setOnClickListener {
            val intent = Intent(this, JobsEncerradosDev::class.java)
            startActivity(intent)
        }

        // Obter as informações enviadas pela tela de login
        val estado = intent.getStringExtra("estado")
        val cidade = intent.getStringExtra("cidade")
        val nomeCompleto = intent.getStringExtra("nomeDev")
        val descricaoResumo = intent.getStringExtra("descricaoResumo")
        val descricaoBiografia = intent.getStringExtra("descricaoBiografia")

        // Definir as informações nos elementos da tela
        tvEstado.text = estado
        tvCidade.text = cidade
        tvNomeCompleto.text = nomeCompleto
        resumo.text = descricaoResumo
        biografia.text = descricaoBiografia
    }

    fun telaEditar(componente: View) {
        val telaPerfilDev = Intent(applicationContext, EdicaoPerfilDev::class.java)
        startActivity(telaPerfilDev)
    }
}
