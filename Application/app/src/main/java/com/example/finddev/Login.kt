package com.example.finddev

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.finddev.App.api.Apis
import com.example.finddev.App.model.UsuarioModel
import com.example.finddev.App.model.dtos.LoginModel
import com.example.finddev.App.sharedpreferences.saveIdUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun logar(componente: View){
        println("Entrou")
        var validado = true

        val email = findViewById<EditText>(R.id.et_email)

        val senha = findViewById<EditText>(R.id.et_senha)

        val cadastroStep3 = Intent(applicationContext, ParabensEmpresaCadastro::class.java)

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

            var idResponse_main = findViewById<TextView>(R.id.id_response_main)
            chamadaGet.enqueue(object : Callback<UsuarioModel> {
                override fun onResponse(
                    call: Call<UsuarioModel>,
                    response: Response<UsuarioModel>
                ) {
                    if (response.isSuccessful) {
                        saveIdUser(applicationContext, response.body()?.id.toString())

                        response.body()?.cnpj?.let {
                            startActivity(Intent(applicationContext, PosLoginEmpresa::class.java))
                        } ?: startActivity(Intent(applicationContext, PosLoginDev::class.java))
                    }else {
                        val code = response.code()
                        Log.d("TAG", "Erro ao logar : Status = "+ "${code}")
                        idResponse_main.text = "Usuario e senha não encontrados!"
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
}