package com.example.finddev

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
import com.example.finddev.dev.PerfilDevFragment
import com.example.finddev.dev.PosLoginDevFragment
import com.example.finddev.empresa.PerfilEmpresaFragment
import com.example.finddev.empresa.PosLoginEmpresaFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun logar(componente: View) {
        println("Entrou")
        var validado = true

        val email = findViewById<EditText>(R.id.et_email)
        val senha = findViewById<EditText>(R.id.et_senha)
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
        } else if (senha.text.length < 6) {
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

                        val usuario = response.body()
                        usuario?.cpf?.let {
                            val perfilDevFragment = PerfilDevFragment()
                            val bundle = Bundle()
                            bundle.putString("nomeDev", usuario.nome)
                            bundle.putString("estado", usuario.estado)
                            bundle.putString("cidade", usuario.cidade)
                            bundle.putString("titulo", usuario.perfil?.titulo)
                            bundle.putString("descricao", usuario.perfil?.descricao)
                            perfilDevFragment.arguments = bundle

                            val posLoginDevFragment = PosLoginDevFragment()
                            posLoginDevFragment.arguments = bundle

                            supportFragmentManager.beginTransaction()
                                .replace(android.R.id.content, posLoginDevFragment)
                                .addToBackStack(null)
                                .commit()
                        } ?: run {
                            val perfilEmpresaFragment = PerfilEmpresaFragment()
                            val bundle = Bundle()
                            bundle.putString("nomeEmpresa", usuario?.nome)
                            bundle.putString("estado", usuario?.estado)
                            bundle.putString("cidade", usuario?.cidade)
                            bundle.putString("bairro", usuario?.bairro)
                            bundle.putString("endereco", usuario?.endereco)
                            bundle.putString("titulo", usuario?.perfil?.titulo)
                            bundle.putString("descricao", usuario?.perfil?.descricao)
                            perfilEmpresaFragment.arguments = bundle

                            val posLoginEmpresaFragment = PosLoginEmpresaFragment()
                            posLoginEmpresaFragment.arguments = bundle

                            supportFragmentManager.beginTransaction()
                                .replace(android.R.id.content, posLoginEmpresaFragment)
                                .addToBackStack(null)
                                .commit()
                        }
                    } else {
                        val code = response.code()
                        Log.d("TAG", "Erro ao logar: Status = $code")
                        idResponse_main.text = "Usuário e senha não encontrados!"
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
