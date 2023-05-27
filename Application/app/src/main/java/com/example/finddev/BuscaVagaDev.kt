package com.example.finddev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.commit
import com.example.finddev.App.model.dtos.VagaModel

class BuscaVagaDev : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        var spinner1: Spinner
        var spinner2: Spinner

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_busca_vaga_dev)
        carregarVagas()

        spinner1 = findViewById(R.id.sp_front_of_dev)
        spinner2 = findViewById(R.id.sp_senior)

        val spinnerItems = resources.getStringArray(R.array.Teste)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerItems)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner1.adapter = adapter
        spinner2.adapter = adapter

        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = spinnerItems[position]
                // Lógica para tratar a seleção do primeiro spinner
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Lógica para tratamento quando nada é selecionado
            }
        }

        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = spinnerItems[position]
                // Lógica para tratar a seleção do segundo spinner
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Lógica para tratamento quando nada é selecionado
            }
        }
    }

    fun carregarVagas() {

        supportFragmentManager.beginTransaction()

        // aqui seria a chamada p/ API
        val vagas = listOf<VagaModel>(
            VagaModel(1, "vaga A", "aaaa ", 3000.00),
            VagaModel(2, "vaga B", "bbbb  ", 2000.00),
            VagaModel(3, "vaga C", "cc ccc cc", 1500.00)
        )

        supportFragmentManager.commit {

            vagas.forEach {
                val fragmento = VagaFragment()

                val argumentos = Bundle()
                argumentos.putSerializable("vaga", it)

                fragmento.arguments = argumentos

                add(R.id.ll_vagas, fragmento)
                setReorderingAllowed(true)
            }
        }
    }

}