package com.example.finddev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

class PublicacaoDeVagas : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publicacao_de_vagas)
        spinners()
    }

    fun spinners() {
        val senioridadeArray = arrayOf("Junior", "Pleno", "Senior")
        val frenteArray = arrayOf("Front End", "Back End", "Devops")

        var spinnerSenioridade: Spinner = findViewById(R.id.sp_senioridade)
        var spinnerFrente: Spinner = findViewById(R.id.sp_frente)

        val adapterSenioridade = ArrayAdapter(this, android.R.layout.simple_spinner_item, senioridadeArray)
        adapterSenioridade.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val adapterfrente = ArrayAdapter(this, android.R.layout.simple_spinner_item, frenteArray)
        adapterfrente.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerSenioridade.adapter = adapterSenioridade
        spinnerFrente.adapter = adapterfrente


        spinnerSenioridade.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = senioridadeArray[position]
                // Lógica para tratar a seleção do primeiro spinner
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Lógica para tratamento quando nada é selecionado
            }
        }

        spinnerFrente.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = frenteArray[position]
                // Lógica para tratar a seleção do segundo spinner
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Lógica para tratamento quando nada é selecionado
            }
        }

    }

    fun voltarMenu(componente: View){
        val Menu = Intent(applicationContext, posLoginEmpresa::class.java)
        startActivity(Menu)
    }
}