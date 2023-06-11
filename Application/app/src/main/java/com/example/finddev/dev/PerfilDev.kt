package com.example.finddev.dev

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.finddev.R

class PerfilDev : AppCompatActivity() {
    private lateinit var tvNomeCompleto: TextView
    private lateinit var tvCompetenciasCompleto: TextView
    private lateinit var tvBiografiaCompleto: TextView
    private lateinit var tvEstado: TextView
    private lateinit var tvCidade: TextView
    private lateinit var btnEditar: Button
    private lateinit var botaoColaboradores: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_dev)

        // Inicializa os elementos visuais
//        tvNomeCompleto = findViewById(R.id.tvNomeCompleto)
//        tvNomeCompleto = findViewById(R.id.siglaNome)
//        tvBiografiaCompleto = findViewById(R.id.biografia)
//        tvEstado = findViewById(R.id.tvEstado)
//        tvCidade = findViewById(R.id.tvCidade)
//        btnEditar = findViewById(R.id.btnEditar)

        // Define os valores nos elementos visuais
//        val perfil = obterPerfilUsuario()
//        tvNomeCompleto.text = perfil.idPerfil.toString()
//        tvCompetenciasCompleto.text = perfil.titulo
//        tvBiografiaCompleto.text = perfil.descricao
//        tvEstado.text = perfil.status.toString()
//        tvCidade.text = perfil.status.toString()
//
//        // Define o listener para o botão "Editar"
//        btnEditar.setOnClickListener {
//            val intent = Intent(this, EdicaoPerfilDev::class.java)
//            intent.putExtra("perfil", perfil)
//            startActivity(intent)
//        }

        botaoColaboradores = findViewById(R.id.botaoColaboradores)
        botaoColaboradores.setOnClickListener {
            val intent = Intent(this, JobsEncerradosDev::class.java)
            startActivity(intent)
        }
//    }
//
//    private fun obterPerfilUsuario(): PerfilModel {
//        // Aqui você deve implementar a lógica para obter o perfil do usuário
//        // de acordo com a sua aplicação. Pode ser obtido de um banco de dados,
//        // de um serviço web, etc.
//        // Por enquanto, vou apenas retornar um perfil de exemplo.
//        return PerfilModel(1,
//            "Desenvolvedor Android",
//            "Sou um desenvolvedor Android com experiência em Kotlin e Java.",
//            StatusPerfil.CONTRATANDO
//        )
    }

    fun telaEditar(componente: View){
        val telaPerfilDev = Intent(applicationContext, EdicaoPerfilDev::class.java)

        startActivity(telaPerfilDev)
    }
}
