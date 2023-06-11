package com.example.finddev

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class BuscaVagaDev : AppCompatActivity() {

    private lateinit var spinnerFrenteDesenvolvimento: Spinner
    private lateinit var spinnerSenioridade: Spinner
    private lateinit var btnProcurar: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_busca_vaga_dev)

        spinnerFrenteDesenvolvimento = findViewById(R.id.spinnerFrenteDesenvolvimento)
        spinnerSenioridade = findViewById(R.id.spinnerSenioridade)
        btnProcurar = findViewById(R.id.btnProcurar)


        val backButton = findViewById<ImageView>(R.id.backButton)
        backButton.setOnClickListener {
            onBackPressed() // Volta para a tela anterior
        }

        // Configurar os adapters para os spinners
        val frenteDesenvolvimentoAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.frentes_desenvolvimento,
            R.layout.spinner_item
        )
        frenteDesenvolvimentoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFrenteDesenvolvimento.adapter = frenteDesenvolvimentoAdapter

        val senioridadeAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.senioridades,
            R.layout.spinner_item
        )
        senioridadeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSenioridade.adapter = senioridadeAdapter

        // Definir o listener para o botão "Procurar"
        btnProcurar.setOnClickListener {
            // Obter os valores selecionados nos spinners
            val frenteDesenvolvimento = spinnerFrenteDesenvolvimento.selectedItem.toString()
            val senioridade = spinnerSenioridade.selectedItem.toString()

            // Criar um Intent para iniciar a ListaVagasActivity
            val intent = Intent(this, ListaBuscaVagasDev::class.java)
            // Passar os valores selecionados como extras para a próxima Activity
            intent.putExtra("frenteDesenvolvimento", frenteDesenvolvimento)
            intent.putExtra("senioridade", senioridade)
            // Iniciar a próxima Activity
            startActivity(intent)
        }
    }
}


