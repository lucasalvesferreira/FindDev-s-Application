package com.example.finddev

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.finddev.App.api.Apis
import com.example.finddev.App.model.UsuarioModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

class CadastroDev : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_dev)
    }

    fun cadastrarDev(componente: View){
        var validado = true

        val email = findViewById<EditText>(R.id.et_email)

        val senha = findViewById<EditText>(R.id.et_senha)

        val confirmacao = findViewById<EditText>(R.id.et_confirmacao)

        val nome = findViewById<EditText>(R.id.et_nome)

        val telefone = findViewById<EditText>(R.id.et_telefone)

        val cpf = findViewById<EditText>(R.id.et_cpf)

        val estado = findViewById<EditText>(R.id.et_estado)

        val cidade = findViewById<EditText>(R.id.et_cidade)

        val cadastroStep3 = Intent(applicationContext, ParabensDesenvolvedorCadastro::class.java)

        val emailRegex = Regex("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,})+\$")
        val namePattern = Pattern.compile("^[\\p{L} .'-]+\$")
        val numeroPattern = Pattern.compile("[0-9]+")

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

        if (confirmacao.text.isEmpty()) {
            confirmacao.error = "Campo obrigatório"
            validado = false
        }else if(confirmacao.text == senha.text){
            confirmacao.error = "A senha deve ser igual a confirmação de senha"
            validado = false
        }

        if (nome.text.isEmpty()) {
            nome.error = "Campo obrigatório"
            validado = false
        }
//        else if(
//            namePattern.matcher(nome.text).matches() ||
//            nome.text.split("\\s+".toRegex()).size < 2
//        ){
//            nome.error = "Por favor, digite seu nome completo usando apenas letras, pontos, espaços, hífens e apóstrofos."
//            validado = false
//        }

        if (telefone.text.isEmpty()) {
            telefone.error = "Campo obrigatório"
            validado = false
        }else if (telefone.text.length != 11) {
            telefone.error = "Por favor, digite um número de telefone válido com o formato (DD)NNNNN-NNNN e 11 dígitos."
        }

        if (cpf.text.isEmpty()) {
            cpf.error = "Campo obrigatório"
            validado = false
        }else if (!numeroPattern.matcher(cpf.text).matches()) {
            cpf.error = "Por favor, digite apenas números no campo de CPF."
            validado = false
        }else if (cpf.text.length != 11) {
            cpf.error = "Por favor, digite um CPF válido com 11 dígitos."
            validado = false
        }

        if (estado.text.isEmpty()) {
            estado.error = "Campo obrigatório"
            validado = false
        }

        if (cidade.text.isEmpty()) {
            cidade.error = "Campo obrigatório"
            validado = false
        }


        if (validado) {
            val dev = UsuarioModel(
                nome = nome.text.toString(),
                email = email.text.toString(),
                senha = senha.text.toString(),
                estado = estado.text.toString(),
                cidade = cidade.text.toString(),
                telefone = telefone.text.toString(),
                cpf = cpf.text.toString()
            )

            val apiDesenvolvedores = Apis.getApiDesenvolvedor()
            val chamadaPost = apiDesenvolvedores.createDeveloper(dev)


            chamadaPost.enqueue(object : Callback<UsuarioModel> {


                override fun onResponse(call: Call<UsuarioModel>, response: Response<UsuarioModel>) {
                    if (response.isSuccessful) { // status 2xx (200, 201, 204 etc)
                        val findDev = response.body()
                        val logar = Intent(applicationContext, cadastroStep3::class.java)
                        startActivity(logar)
                    } else {
                        var code = response.code()
//                        idResponse.text = "Erro ao cadastrar"+ "${code}"
                        Toast.makeText(
                            baseContext, "Ops, algo deu errado!",
                            Toast.LENGTH_SHORT
                        ).show()
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