package com.example.finddev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner

class PublicacaoDeVagas : AppCompatActivity() {
    private lateinit var etTitulo: EditText
    private lateinit var spinnerSenioridade: Spinner
    private lateinit var spinnerFrente: Spinner
    private lateinit var etDescricao: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publicacao_de_vagas)

        etTitulo = findViewById(R.id.et_titulo)
        spinnerSenioridade = findViewById(R.id.sp_senioridade)
        spinnerFrente = findViewById(R.id.sp_frente)
        etDescricao = findViewById(R.id.et_descricao)

        spinners()

        val btnPublicar: Button = findViewById(R.id.btn_publicar)
        btnPublicar.setOnClickListener {
            publicarVaga()
        }
    }

    private fun spinners() {
        val senioridadeArray = arrayOf("Junior", "Pleno", "Senior")
        val frenteArray = arrayOf("Front End", "Back End", "Devops")

        val adapterSenioridade = ArrayAdapter(this, android.R.layout.simple_spinner_item, senioridadeArray)
        adapterSenioridade.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val adapterFrente = ArrayAdapter(this, android.R.layout.simple_spinner_item, frenteArray)
        adapterFrente.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerSenioridade.adapter = adapterSenioridade
        spinnerFrente.adapter = adapterFrente

        spinnerSenioridade.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = senioridadeArray[position]
                // Lógica para tratar a seleção do primeiro spinner
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Lógica para tratamento quando nada é selecionado
            }
        }

        spinnerFrente.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = frenteArray[position]
                // Lógica para tratar a seleção do segundo spinner
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Lógica para tratamento quando nada é selecionado
            }
        }
    }

    private fun publicarVaga() {
        val titulo = etTitulo.text.toString()
        val senioridade = spinnerSenioridade.selectedItem.toString()
        val frenteDesenvolvimento = spinnerFrente.selectedItem.toString()
        val descricao = etDescricao.text.toString()

        // Lógica para publicar a vaga com os dados obtidos

        // Exemplo de impressão dos dados:
        println("Título: $titulo")
        println("Senioridade: $senioridade")
        println("Frente de Desenvolvimento: $frenteDesenvolvimento")
        println("Descrição: $descricao")
    }

    fun voltarMenu(componente: View) {
        val menuIntent = Intent(this, posLoginEmpresa::class.java)
        startActivity(menuIntent)
    }
}
