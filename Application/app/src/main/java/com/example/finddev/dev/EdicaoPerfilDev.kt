package com.example.finddev.dev

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.finddev.App.api.Apis
import com.example.finddev.App.model.UsuarioModel
import com.example.finddev.App.model.dtos.PerfilRequest
import com.example.finddev.App.sharedpreferences.getIdUser
import com.example.finddev.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class EdicaoPerfilDev : AppCompatActivity() {

    private lateinit var edtTitulo: EditText
    private lateinit var edtBiografia: EditText
    private lateinit var btnEditar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edicao_perfil_dev)

        btnEditar.setOnClickListener {
            atualizarPerfil()
        }
    }

    private fun atualizarPerfil() {
        val idUsuario = UUID.fromString(getIdUser(applicationContext))
        val biografia = edtTitulo.text.toString().trim()
        val experiencia = edtBiografia.text.toString().trim()

        val request = PerfilRequest(idUsuario, biografia, experiencia)
        val call = Apis.getApiUsuario().atualizarPerfil(request)

        call.enqueue(object : Callback<UsuarioModel> {
            override fun onResponse(call: Call<UsuarioModel>, response: Response<UsuarioModel>) {
                if (response.isSuccessful) {
                    Toast.makeText(
                        this@EdicaoPerfilDev,
                        "Perfil atualizado com sucesso",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                } else {
                    println("status code ${response.code()}")
                    Toast.makeText(
                        this@EdicaoPerfilDev,
                        "Erro ao atualizar o perfil",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<UsuarioModel>, t: Throwable) {
                Toast.makeText(
                    this@EdicaoPerfilDev,
                    "Erro de conexão",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}
