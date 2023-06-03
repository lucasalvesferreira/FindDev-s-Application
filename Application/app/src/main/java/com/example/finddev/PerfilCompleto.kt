package com.example.finddev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.finddev.App.enums.StatusPerfil
import com.example.finddev.App.model.Perfil
import com.example.finddev.App.model.PerfilModel

class PerfilCompleto : AppCompatActivity() {
    private lateinit var tvNomeCompleto: TextView
    private lateinit var tvCompetenciasCompleto: TextView
    private lateinit var tvBiografiaCompleto: TextView
    private lateinit var tvExperienciaCompleto: TextView
    private lateinit var btnEditar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_completo)

        // Inicializa os elementos visuais
        tvNomeCompleto = findViewById(R.id.tvNomeCompleto)
        tvCompetenciasCompleto = findViewById(R.id.tvCompetenciasCompleto)
        tvBiografiaCompleto = findViewById(R.id.tvBiografiaCompleto)
        tvExperienciaCompleto = findViewById(R.id.tvExperienciaCompleto)
        btnEditar = findViewById(R.id.btnAvaliar)

        // Define os valores nos elementos visuais
        val perfil = obterPerfilUsuario()
        tvNomeCompleto.text = perfil.idPerfil.toString()
        tvCompetenciasCompleto.text = perfil.titulo
        tvBiografiaCompleto.text = perfil.descricao
        tvExperienciaCompleto.text = perfil.status.toString()

        // Define o listener para o botão "Editar"
        btnEditar.setOnClickListener {
            val intent = Intent(this, PerfilCompleto::class.java)
            intent.putExtra("perfil", perfil)
            startActivity(intent)
        }
    }

    private fun obterPerfilUsuario(): PerfilModel {
        // Aqui você deve implementar a lógica para obter o perfil do usuário
        // de acordo com a sua aplicação. Pode ser obtido de um banco de dados,
        // de um serviço web, etc.
        // Por enquanto, vou apenas retornar um perfil de exemplo.
        return PerfilModel(1,
            "Desenvolvedor Android",
            "Sou um desenvolvedor Android com experiência em Kotlin e Java.",
            StatusPerfil.CONTRATANDO
        )
    }
}