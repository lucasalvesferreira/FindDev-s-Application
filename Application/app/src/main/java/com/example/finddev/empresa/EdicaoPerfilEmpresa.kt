package com.example.finddev.empresa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.finddev.App.api.Apis
import com.example.finddev.App.model.UsuarioModel
import com.example.finddev.App.model.dtos.PerfilRequest
import com.example.finddev.App.model.dtos.VagaResponse
import com.example.finddev.App.sharedpreferences.getIdUser
import com.example.finddev.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class EdicaoPerfilEmpresa : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edicao_perfil_empresa)

        val botaoEditar = findViewById<Button>(R.id.btnEditar)
        botaoEditar.setOnClickListener {
            atualizarPerfil()
        }
    }

    private fun atualizarPerfil() {
        val idUsuario = UUID.fromString(getIdUser(applicationContext))
        val titulo = findViewById<EditText>(R.id.edtTitulo).text.toString()
        val descricao = findViewById<EditText>(R.id.edtBiografia).text.toString()

        val perfilRequest = PerfilRequest(idUsuario, titulo, descricao)

        val apiUsuario = Apis.getApiUsuario()
        val putMethod = apiUsuario.atualizarPerfil(perfilRequest)

        putMethod.enqueue(object : Callback<UsuarioModel> {
            override fun onResponse(call: Call<UsuarioModel>, response: Response<UsuarioModel>) {
                if (response.isSuccessful) {
                    val estado = intent.getStringExtra("estado")
                    val cidade = intent.getStringExtra("cidade")
                    val bairro = intent.getStringExtra("bairro")
                    val endereco = intent.getStringExtra("endereco")
                    val nome = intent.getStringExtra("nome")

                    val intent = Intent(applicationContext, PerfilEmpresa::class.java)
                    intent.putExtra("estado", estado)
                    intent.putExtra("cidade", cidade)
                    intent.putExtra("bairro", bairro)
                    intent.putExtra("endereco", endereco)
                    intent.putExtra("nomeEmpresa", nome)
                    intent.putExtra("titulo", titulo)
                    intent.putExtra("descricao", descricao)

                    startActivity(intent)
                } else {
                    println("status code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<UsuarioModel>, t: Throwable) {
                Toast.makeText(
                    baseContext, "Erro na API: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
                t.printStackTrace()
            }
        })
    }


}