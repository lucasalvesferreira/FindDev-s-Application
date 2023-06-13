package com.example.finddev.dev

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.finddev.App.api.Apis
import com.example.finddev.App.model.dtos.PerfilDevRequest
import com.example.finddev.App.model.dtos.PerfilDevResponse
import com.example.finddev.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EdicaoPerfilDev : AppCompatActivity() {

    private lateinit var edtBiografia: EditText
    private lateinit var edtExperiencia: EditText
    private lateinit var btnEditar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edicao_perfil_dev)

        edtBiografia = findViewById(R.id.edtBiografia)
        edtExperiencia = findViewById(R.id.edtResumo)
        btnEditar = findViewById(R.id.btnEditar)

        btnEditar.setOnClickListener {
            val biografia = edtBiografia.text.toString().trim()
            val experiencia = edtExperiencia.text.toString().trim()

            if (biografia.isEmpty() || experiencia.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            } else {
                atualizarPerfil(biografia, experiencia)
            }
        }
    }

    private fun atualizarPerfil(biografia: String, experiencia: String) {
        val idUsuario = "idDoUsuario" // Substitua "idDoUsuario" pelo ID do usuário atual

        val request = PerfilDevRequest(biografia, experiencia)
        val call = Apis.getApiPerfilDev().atualizarPerfil(idUsuario, request)

        call.enqueue(object : Callback<PerfilDevResponse> {
            override fun onResponse(call: Call<PerfilDevResponse>, response: Response<PerfilDevResponse>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@EdicaoPerfilDev, "Perfil atualizado com sucesso", Toast.LENGTH_SHORT).show()
                    finish() // Fecha a tela de edição após o sucesso
                } else {
                    Toast.makeText(this@EdicaoPerfilDev, "Erro ao atualizar o perfil", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<PerfilDevResponse>, t: Throwable) {
                Toast.makeText(this@EdicaoPerfilDev, "Erro de conexão", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
