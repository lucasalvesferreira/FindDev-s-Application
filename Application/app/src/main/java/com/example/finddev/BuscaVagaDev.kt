package com.example.finddev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.commit
import com.example.finddev.App.model.dtos.VagaModel

class BuscaVagaDev : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_busca_vaga_dev)
        carregarVagas()


        // access the items of the list
        val languages = resources.getStringArray(R.array.Teste)

        // access the spinner
        val spinner = findViewById<Spinner>(R.id.sp_front_of_dev)
        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, languages)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    Toast.makeText(this@BuscaVagaDev,
                        getString(R.string.selected_item) + " " +
                                "" + languages[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }
    fun carregarVagas() {

        supportFragmentManager.beginTransaction()

        // aqui seria a chamada p/ API
        val vagas = listOf<VagaModel>(
            VagaModel(1, "vaga A", "aaaa "),
            VagaModel(2, "vaga B", "bbbb  "),
            VagaModel(3, "vaga C", "cc ccc cc")
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