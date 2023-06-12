package com.example.finddev.empresa

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.finddev.R

class PerfilEmpresa : AppCompatActivity() {

    private lateinit var botaoVagasAbertas: Button
    private lateinit var botaoColaboradores: Button
    private lateinit var botaoContratar: Button
    private lateinit var tvNomeEmpresa: TextView
    private lateinit var tvResumo: TextView
    private lateinit var tvBiografia: TextView
    private lateinit var tvEstado: TextView
    private lateinit var tvCidade: TextView
    private lateinit var tvBairro: TextView
    private lateinit var tvEndereço: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_empresa)

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
        tvEndereço = findViewById(R.id.tvEndereco)
        tvNomeEmpresa = findViewById(R.id.tvNomeEmpresa)
        tvResumo = findViewById(R.id.tvResumo)
        tvBiografia = findViewById(R.id.tvBiografia)

        // Obter as informações enviadas pela tela de login
        val estado = intent.getStringExtra("estado")
        val cidade = intent.getStringExtra("cidade")
        val bairro = intent.getStringExtra("bairro")
        val endereco = intent.getStringExtra("endereco")
        val nomeCompleto = intent.getStringExtra("nomeEmpresa")
        val descricaoResumo = intent.getStringExtra("descricaoResumo")
        val descricaoBiografia = intent.getStringExtra("descricaoBiografia")

        // Definir as informações nos elementos da tela
        tvEstado.text = estado
        tvCidade.text = cidade
        tvBairro.text = bairro
        tvEndereço.text = endereco
        tvNomeEmpresa.text = nomeCompleto
        tvResumo.text = descricaoResumo
        tvBiografia.text = descricaoBiografia

    }

    fun telaEditarEmpresa(componente: View){
        val telaPerfilEmpresa= Intent(applicationContext, EdicaoPerfilEmpresa::class.java)

        startActivity(telaPerfilEmpresa)
    }
}