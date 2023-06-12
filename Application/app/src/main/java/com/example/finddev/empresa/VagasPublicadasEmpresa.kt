package com.example.finddev.empresa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finddev.App.api.Apis
import com.example.finddev.App.model.dtos.VagaResponse
import com.example.finddev.App.sharedpreferences.getIdUser
import com.example.finddev.R
import com.example.finddev.empresa.fragment.ModalVagasPublicadasEmpresa
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class VagasPublicadasEmpresa : AppCompatActivity() {

    private lateinit var recyclerViewVagasPublicadas: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vagas_publicadas_empresa)

        // Configurar o RecyclerView
        recyclerViewVagasPublicadas = findViewById(R.id.recyclerViewVagasPublicadas)
        recyclerViewVagasPublicadas.layoutManager = LinearLayoutManager(this)

        getListaVagas()
    }

    // Classe do ViewHolder para o item da lista
    private class VagaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgVaga: ImageView = itemView.findViewById(R.id.imgVaga)
        val txtTitulo: TextView = itemView.findViewById(R.id.txtTitulo)
        val txtFrenteDesenvolvimento: TextView = itemView.findViewById(R.id.txtFrenteDesenvolvimento)
        val txtSenioridade: TextView = itemView.findViewById(R.id.txtSenioridade)
    }

    // Adaptador para o RecyclerView
    private inner class VagasAdapter(private val listaVagas: List<VagaResponse>) : RecyclerView.Adapter<VagaViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VagaViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_vaga, parent, false)
            return VagaViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: VagaViewHolder, position: Int) {
            val vaga = listaVagas[position]
            holder.imgVaga.setImageResource(R.mipmap.logo) // Defina a imagem correta para cada vaga
            holder.txtTitulo.text = vaga.titulo
            holder.txtFrenteDesenvolvimento.text = vaga.funcao
            holder.txtSenioridade.text = vaga.senioridade

            // Configurar o clique no item da lista para abrir o ModalVagasEncerradas
            holder.itemView.setOnClickListener {
                val modalVagasPublicadasEmpresa = ModalVagasPublicadasEmpresa.newInstance(vaga) // Crie uma instância do ModalVagasEncerradas e passe a vaga selecionada
                modalVagasPublicadasEmpresa.show(supportFragmentManager, "modal_vagas_publicadas_empresas") // Exiba o modal
            }
        }

        override fun getItemCount(): Int {
            return listaVagas.size
        }
    }

    private fun getListaVagas() {
        val apiVagas = Apis.getApiVagas()
        val getMethod = apiVagas.getVagasEmpresa(UUID.fromString(getIdUser(applicationContext)))

        getMethod.enqueue(object : Callback<List<VagaResponse>> {
            override fun onResponse(
                call: Call<List<VagaResponse>>,
                response: Response<List<VagaResponse>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        recyclerViewVagasPublicadas.adapter = VagasAdapter(it)
                    }
                } else {
                    println("status code ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<VagaResponse>>, t: Throwable) {
                Toast.makeText(
                    baseContext, "Erro na API: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
                t.printStackTrace()
            }
        })
    }
}