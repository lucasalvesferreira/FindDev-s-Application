package com.example.finddev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.finddev.App.api.ApiUsuario
import com.example.finddev.App.api.Apis
import com.example.finddev.App.model.UsuarioModel
import com.example.finddev.App.model.dtos.LoginModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun logar(componente: View){
        var validado = true

        val email = findViewById<EditText>(R.id.et_email)

        val senha = findViewById<EditText>(R.id.et_senha)

        val cadastroStep3 = Intent(applicationContext, ActivityCadastroStep3::class.java)

        val emailRegex = Regex("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,})+\$")

        if (email.text.toString().isEmpty()) {
            email.error = "Campo obrigatório"
            validado = false
        } else if (!emailRegex.matches(email.text.toString())) {
            email.error = "Email inválido"
            validado = false
        }

        if (senha.text.isEmpty()) {
            senha.error = "Campo obrigatório"
            validado = false
        }else if(senha.text.length < 6){
            senha.error = "A senha deve ter pelo menos 6 caracteres."
            validado = false
        }

        if (validado) {
            val loginModel = LoginModel(
                email = email.text.toString(),
                senha = senha.text.toString()
            )
            val apiUsuario = Apis.getApiUsuario()
            val chamadaGet = apiUsuario.logIn(loginModel)

            chamadaGet.enqueue(object : Callback<List<UsuarioModel>> {
                override fun onResponse(
                    call: Call<List<UsuarioModel>>,
                    response: Response<List<UsuarioModel>>
                ) {
                    if (response.isSuccessful) {
                        val usuarios = response.body()
                        if (usuarios?.isNotEmpty()!!) {
                            startActivity(cadastroStep3) // TODO mudar quando criar tela de login
                        }
                    }
                }

                override fun onFailure(call: Call<List<UsuarioModel>>, t: Throwable) {
                    Toast.makeText(
                        baseContext, "Erro na API: ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                    t.printStackTrace()
                }
            })
        }

    }

}