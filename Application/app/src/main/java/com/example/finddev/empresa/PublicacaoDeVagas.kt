package com.example.finddev.empresa

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.finddev.App.api.Apis
import com.example.finddev.App.model.dtos.VagaRequest
import com.example.finddev.App.model.dtos.VagaResponse
import com.example.finddev.App.sharedpreferences.getIdUser
import com.example.finddev.App.sharedpreferences.saveIdVaga
import com.example.finddev.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        val senioridadeArray = arrayOf("Selecione...","JUNIOR", "PLENO", "SENIOR")
        val frenteArray = arrayOf("Selecione...","FRONTEND", "BACKEND", "FULLSTACK")

        val adapterSenioridade = ArrayAdapter(this,R.layout.spinner_item, senioridadeArray)
        adapterSenioridade.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val adapterFrente = ArrayAdapter(this,R.layout.spinner_item, frenteArray)
        adapterFrente.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerSenioridade.adapter = adapterSenioridade
        spinnerFrente.adapter = adapterFrente
    }

    private fun publicarVaga() {
        val titulo = etTitulo.text.toString()
        val senioridade = spinnerSenioridade.selectedItem.toString()
        val frenteDesenvolvimento = spinnerFrente.selectedItem.toString()
        val descricao = etDescricao.text.toString()

        val vagaRequest = VagaRequest(
            getIdUser(applicationContext),
            titulo,
            descricao,
            frenteDesenvolvimento,
            senioridade
        )

        val apiVagas = Apis.getApiVagas()
        val chamadaPost = apiVagas.createVaga(vagaRequest)

        chamadaPost.enqueue(object : Callback<VagaResponse> {
            override fun onResponse(call: Call<VagaResponse>, response: Response<VagaResponse>) {
                if (response.isSuccessful) {
                    saveIdVaga(applicationContext, response.body()?.id!!)
//                    voltarMenu()
                } else {
                    Toast.makeText(
                        baseContext, "Ops, algo deu errado!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<VagaResponse>, t: Throwable) {
                Toast.makeText(
                    baseContext, "Erro na API: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
                t.printStackTrace()
            }
        })
    }

//    fun voltarMenu() {
//        val menuIntent = Intent(this, PosLoginEmpresa::class.java)
//        startActivity(menuIntent)
//    }
}