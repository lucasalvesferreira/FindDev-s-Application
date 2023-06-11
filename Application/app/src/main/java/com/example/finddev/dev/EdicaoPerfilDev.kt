package com.example.finddev.dev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.finddev.R

class EdicaoPerfilDev : AppCompatActivity() {

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
        setContentView(R.layout.activity_edicao_perfil_dev)
    }
}