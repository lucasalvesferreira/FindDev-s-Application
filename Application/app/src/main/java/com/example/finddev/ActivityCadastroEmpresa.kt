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

        val numero = findViewById<EditText>(R.id.et_numero)

        val cadastroStep3 = Intent(applicationContext, ActivityCadastroStep3::class.java)

        val emailRegex = Regex("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,})+\$")
        val namePattern = Pattern.compile("^[\\p{L} .'-]+\$")
        val cepPattern = Pattern.compile("[0-9]+")
        val numeroPattern = Pattern.compile("[0-9]+")

        if (email.text.toString().isEmpty()) {
            email.error = getString(R.string.text_campo_obrigatorio)
            validado = false
        } else if (!emailRegex.matches(email.text.toString())) {
            email.error = getString(R.string.text_email_invalido)
            validado = false
        }

        if (senha.text.isEmpty()) {
            senha.error = getString(R.string.text_campo_obrigatorio)
            validado = false
        }else if(senha.text.length < 6){
            senha.error = getString(R.string.text_senha_seis_digitos)
            validado = false
        }

        if (confirmacao.text.isEmpty()) {
            confirmacao.error = getString(R.string.text_campo_obrigatorio)
            validado = false
        }else if(confirmacao.text == senha.text){
            confirmacao.error = getString(R.string.text_deve_ser_igual)
            validado = false
        }

        if (nome.text.isEmpty()) {
            nome.error = getString(R.string.text_campo_obrigatorio)
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
            telefone.error = getString(R.string.text_campo_obrigatorio)
            validado = false
        }else if (telefone.text.length != 11) {
            telefone.error = getString(R.string.text_tel_valido)
        }

        if (cnpj.text.isEmpty()) {
            cnpj.error = getString(R.string.text_campo_obrigatorio)
            validado = false
        }

        if (cep.text.isEmpty()) {
            cep.error = getString(R.string.text_campo_obrigatorio)
            validado = false
        }else if(!cepPattern.matcher(cep.text).matches()){
            cep.error = getString(R.string.text_somente_numeros)
            validado = false
        }else if(cep.text.length != 8){
            cep.error = getString(R.string.text_oito_digitos)
            validado = false
        }

        if (estado.text.isEmpty()) {
            estado.error = getString(R.string.text_campo_obrigatorio)
            validado = false
        }

        if (cidade.text.isEmpty()) {
            cidade.error = getString(R.string.text_campo_obrigatorio)
            validado = false
        }

        if (bairro.text.isEmpty()) {
            bairro.error = getString(R.string.text_campo_obrigatorio)
            validado = false
        }

        if (endereco.text.isEmpty()) {
            endereco.error = getString(R.string.text_campo_obrigatorio)
            validado = false
        }

        if (numero.text.isEmpty()) {
            numero.error = getString(R.string.text_campo_obrigatorio)
            validado = false
        }else if (!numeroPattern.matcher(numero.text).matches()) {
            numero.error = getString(R.string.text_somente_numeros_numero)
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
            val chamadaPost = apiEmpresa.createDeveloper(company)

//            var idResponse = findViewById<TextView>(R.id.id_response) TODO colocar na tela de cadastro campo para aparecer mensagem de erro
            chamadaPost.enqueue(object : Callback<UsuarioModel> {
                override fun onResponse(
                    call: Call<UsuarioModel>,
                    response: Response<UsuarioModel>
                ) {
                    if (response.isSuccessful) { // status 2xx (200, 201, 204 etc)
                        val findCompany = response.body()
                        val logar = Intent(applicationContext, ActivityCadastroStep3::class.java)
                        startActivity(logar)
                    } else {
                        var code = response.code()
//                        idResponse.text = "Erro ao cadastrar"+ "${code}"
                        Toast.makeText(
                            baseContext, getString(R.string.text_ops),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<UsuarioModel>, t: Throwable) {
                    Toast.makeText(
                        baseContext, getString(R.string.text_erro_api) + "${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                    t.printStackTrace()
                }
            })


        }



    }
}