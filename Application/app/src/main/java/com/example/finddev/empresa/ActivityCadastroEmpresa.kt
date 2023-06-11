package com.example.finddev.empresa

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.finddev.App.api.Apis
import com.example.finddev.App.model.UsuarioModel
import com.example.finddev.R
import com.example.finddev.cadastro.ActivityCadastroStep3
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

class ActivityCadastroEmpresa : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_empresa)
    }

    fun cadastroEmpresa(componente: View){
        var validado = true

        val email = findViewById<EditText>(R.id.et_email)

        val senha = findViewById<EditText>(R.id.et_senha)

        val confirmacao = findViewById<EditText>(R.id.et_confirmacao)

        val nome = findViewById<EditText>(R.id.et_nome)

        val telefone = findViewById<EditText>(R.id.et_telefone)

        val cnpj = findViewById<EditText>(R.id.et_cnpj)

        val cep = findViewById<EditText>(R.id.et_cep)

        val estado = findViewById<EditText>(R.id.et_estado)

        val cidade = findViewById<EditText>(R.id.et_cidade)

        val bairro = findViewById<EditText>(R.id.et_bairro)

        val endereco = findViewById<EditText>(R.id.et_logradouro)

        val cadastroStep3 = Intent(applicationContext, ParabensEmpresaCadastro::class.java)

        val emailRegex = Regex("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,})+\$")
        val namePattern = Pattern.compile("^[\\p{L} .'-]+\$")
        val cepPattern = Pattern.compile("[0-9]+")
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

        if (cnpj.text.isEmpty()) {
            cnpj.error = "Campo obrigatório"
            validado = false
        }

        if (cep.text.isEmpty()) {
            cep.error = "Campo obrigatório"
            validado = false
        }else if(!cepPattern.matcher(cep.text).matches()){
            cep.error = "Por favor, digite apenas números no campo de CEP"
            validado = false
        }else if(cep.text.length != 8){
            cep.error = "Por favor, digite um CEP válido com 8 dígitos"
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

        if (bairro.text.isEmpty()) {
            bairro.error = "Campo obrigatório"
            validado = false
        }

        if (endereco.text.isEmpty()) {
            endereco.error = "Campo obrigatório"
            validado = false
        }

//        else if (numero == null || numero.text.toInt() > 0) {
//            numero.error = "Por favor, digite um número residencial válido." // TODO esta dando erro
//            validado = false
//        }

        if (validado) {
            val company = UsuarioModel(
                nome = nome.text.toString(),
                email = email.text.toString(),
                senha = senha.text.toString(),
                estado = estado.text.toString(),
                cidade = cidade.text.toString(),
                bairro = bairro.text.toString(),
                endereco = endereco.text.toString(),
                telefone = telefone.text.toString(),
                cnpj = cnpj.text.toString(),
            )

            val apiEmpresa = Apis.getApiEmpresa()
            val chamadaPost = apiEmpresa.createEmpresa(company)

//            var idResponse = findViewById<TextView>(R.id.id_response) TODO colocar na tela de cadastro campo para aparecer mensagem de erro
            chamadaPost.enqueue(object : Callback<UsuarioModel> {
                override fun onResponse(
                    call: Call<UsuarioModel>,
                    response: Response<UsuarioModel>
                ) {
                    if (response.isSuccessful) {
                        val intent = Intent(applicationContext, cadastroStep3::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            baseContext, "Ops, algo deu errado! response: $response",
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