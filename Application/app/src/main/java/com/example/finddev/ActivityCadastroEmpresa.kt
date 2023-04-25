package com.example.finddev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
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

        val logradouro = findViewById<EditText>(R.id.et_logradouro)

        val numero = findViewById<EditText>(R.id.et_numero)

        val cadastroStep3 = Intent(applicationContext, ActivityCadastroStep3::class.java)

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
        }else if(
            namePattern.matcher(nome.text).matches() ||
            nome.text.split("\\s+".toRegex()).size < 2
        ){
            nome.error = "Por favor, digite seu nome completo usando apenas letras, pontos, espaços, hífens e apóstrofos."
            validado = false
        }

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

        if (logradouro.text.isEmpty()) {
            logradouro.error = "Campo obrigatório"
            validado = false
        }

        if (numero.text.isEmpty()) {
            numero.error = "Campo obrigatório"
            validado = false
        }else if (!numeroPattern.matcher(numero.text).matches()) {
            numero.error = "Por favor, digite apenas números no campo de número "
            validado = false
        }else if (numero == null || numero.toString().toInt() > 0) {
            numero.error = "Por favor, digite um número residencial válido."
            validado = false
        }

        if (validado) {
            startActivity(cadastroStep3)
        }
    }
}