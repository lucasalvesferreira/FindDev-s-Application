package com.example.finddev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class PerfilDev : AppCompatActivity() {

    private lateinit var imgPerfil: ImageView
    private lateinit var ratingBar: RatingBar
    private lateinit var edtNome: EditText
    private lateinit var edtCompetencias: EditText
    private lateinit var edtBiografia: EditText
    private lateinit var edtExperiencia: EditText
    private lateinit var btnAvaliar: Button
    private lateinit var btnEditar: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_dev)

        btnEditar = findViewById(R.id.btnEditar)
        btnEditar.setOnClickListener {
            val intent = Intent(this, PerfilCompleto::class.java)
            startActivity(intent)
        }

        val headerSetaVoltar = HeaderSetaVoltar()
        supportFragmentManager.beginTransaction()
            .add(R.id.header_seta_voltar, headerSetaVoltar)
            .commit()


        // Inicializar as views
        imgPerfil = findViewById(R.id.imgPerfil)
        ratingBar = findViewById(R.id.ratingBar)
        edtNome = findViewById(R.id.edtNome)
        edtCompetencias = findViewById(R.id.edtCompetencias)
        edtBiografia = findViewById(R.id.edtBiografia)
        edtExperiencia = findViewById(R.id.edtExperiencia)
        btnAvaliar = findViewById(R.id.btnAvaliar)

        // Configurar ação do botão Avaliar
        btnAvaliar.setOnClickListener {
            // Abrir a tela de avaliação dos contratantes
            // Implemente a lógica desejada para essa ação
        }
    }
}