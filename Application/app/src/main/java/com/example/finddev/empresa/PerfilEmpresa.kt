package com.example.finddev.empresa

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.finddev.App.api.Apis
import com.example.finddev.App.sharedpreferences.getIdUser
import com.example.finddev.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class PerfilEmpresa : AppCompatActivity() {

    private lateinit var botaoVagasAbertas: Button
    private lateinit var botaoColaboradores: Button
    private lateinit var botaoContratar: Button
    private lateinit var tvNomeEmpresa: TextView
    private lateinit var tvTitulo: TextView
    private lateinit var tvBiografia: TextView
    private lateinit var tvEstado: TextView
    private lateinit var tvCidade: TextView
    private lateinit var tvBairro: TextView
    private lateinit var tvEndereco: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_empresa)

        getMediaAvaliacoes()

        botaoVagasAbertas = findViewById(R.id.botaoVagasAbertas)
        botaoVagasAbertas.setOnClickListener {
            val intent = Intent(this, VagasPublicadasEmpresa::class.java)
            startActivity(intent)
        }
        botaoColaboradores = findViewById(R.id.botaoColaboradores)
        botaoColaboradores.setOnClickListener {
            val intent = Intent(this, Colaboradores::class.java)
            startActivity(intent)
        }
        botaoContratar = findViewById(R.id.botaoContratar)
        botaoContratar.setOnClickListener {
            val intent = Intent(this, Candidatos::class.java)
            startActivity(intent)
        }
        tvEstado = findViewById(R.id.tvEstado)
        tvCidade = findViewById(R.id.tvCidade)
        tvBairro = findViewById(R.id.tvBairro)
        tvEndereco = findViewById(R.id.tvEndereco)
        tvNomeEmpresa = findViewById(R.id.tvNomeEmpresa)
        tvTitulo = findViewById(R.id.tvTitulo)
        tvBiografia = findViewById(R.id.tvBiografia)

        // Obter as informações enviadas pela tela de login
        val estado = intent.getStringExtra("estado")
        val cidade = intent.getStringExtra("cidade")
        val bairro = intent.getStringExtra("bairro")
        val endereco = intent.getStringExtra("endereco")
        val nomeEmpresa = intent.getStringExtra("nomeEmpresa")
        val titulo = intent.getStringExtra("titulo")
        val descricao = intent.getStringExtra("descricao")

        // Definir as informações nos elementos da tela
        tvEstado.text = estado
        tvCidade.text = cidade
        tvBairro.text = bairro
        tvEndereco.text = endereco
        tvNomeEmpresa.text = nomeEmpresa
        tvTitulo.text = titulo
        tvBiografia.text = descricao
    }

    fun telaEditarEmpresa(componente: View){
        val telaPerfilEmpresa= Intent(applicationContext, EdicaoPerfilEmpresa::class.java)

        val estado = intent.getStringExtra("estado")
        val cidade = intent.getStringExtra("cidade")
        val bairro = intent.getStringExtra("bairro")
        val endereco = intent.getStringExtra("endereco")
        val nomeEmpresa = intent.getStringExtra("nomeEmpresa")

        telaPerfilEmpresa.putExtra("estado", estado)
        telaPerfilEmpresa.putExtra("cidade", cidade)
        telaPerfilEmpresa.putExtra("bairro", bairro)
        telaPerfilEmpresa.putExtra("endereco", endereco)
        telaPerfilEmpresa.putExtra("nome", nomeEmpresa)

        startActivity(telaPerfilEmpresa)
    }

    private fun getMediaAvaliacoes() {
        val apiAvaliacao = Apis.getApiAvaliacao()
        val getMethod = apiAvaliacao.getMediaAvaliacoes(
            UUID.fromString(getIdUser(applicationContext))
        )
        CoroutineScope(Dispatchers.Main).launch {
            try {
                getMethod.enqueue(object : Callback<Double> {
                    override fun onResponse(call: Call<Double>, response: Response<Double>) {
                        if (response.isSuccessful) {
                            val tvAvaliacao = findViewById<TextView>(R.id.numero_classificacao)
                            tvAvaliacao.text = response.body().toString()
                        } else {
                            println("status code: ${response.code()}")
                        }
                    }

                    override fun onFailure(call: Call<Double>, t: Throwable) {
                        Toast.makeText(
                            baseContext, "Erro na API: ${t.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                        t.printStackTrace()
                    }
                })
            } catch (e: Exception) {
                println("exception message ${e.message}")
                e.printStackTrace()
                println("fim do stack trace")
            }
        }
    }
}