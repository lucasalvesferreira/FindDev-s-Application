package com.example.finddev.empresa

import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finddev.App.api.Apis
import com.example.finddev.App.model.dtos.VagaResponse
import com.example.finddev.App.sharedpreferences.getIdUser
import com.example.finddev.R
import com.example.finddev.empresa.fragment.ModalCandidatos
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class Candidatos : AppCompatActivity() {

    private lateinit var recyclerViewVagasCandidatos: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_candidatos)

        // Configurar o RecyclerView
        recyclerViewVagasCandidatos = findViewById(R.id.recyclerViewVagasCandidatos)
        recyclerViewVagasCandidatos.layoutManager = LinearLayoutManager(this)

        getCandidatos()
    }

    // Classe do ViewHolder para o item da lista
    private class VagaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitulo: TextView = itemView.findViewById(R.id.txtTitulo)
        val containerNomesDev = itemView.findViewById<LinearLayout>(R.id.containerNomesDev)
    }

    // Adaptador para o RecyclerView
    private inner class VagasAdapter(private val listaVagas: List<VagaResponse>) : RecyclerView.Adapter<VagaViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VagaViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_vaga_candidatos, parent, false)
            return VagaViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: VagaViewHolder, position: Int) {
            val vaga = listaVagas[position]
            holder.txtTitulo.text = vaga.titulo

            // Limpa as visualizações de nome do desenvolvedor
            holder.containerNomesDev.removeAllViews()

            // Itera sobre as candidaturas e exibe o nome do desenvolvedor para cada uma
            for (candidatura in vaga.candidaturas) {
                val nomeDev = candidatura.desenvolvedor.nome

                val textView = TextView(holder.itemView.context)
                textView.text = nomeDev
                textView.textSize = 10 * Resources.getSystem().displayMetrics.scaledDensity
                textView.setTextColor(Color.parseColor("#fff"))

                holder.containerNomesDev.addView(textView)

                holder.itemView.setOnClickListener {
                    val modalCandidatos = ModalCandidatos.newInstance(vaga, candidatura.desenvolvedor)
                    modalCandidatos.show(supportFragmentManager, "ModalCandidatos")
                }
            }
        }

        override fun getItemCount(): Int {
            return listaVagas.size
        }
    }

    private fun getCandidatos() {
        val apiVagas = Apis.getApiVagas()
        val getMethod = apiVagas.getVagasEmpresa(UUID.fromString(getIdUser(applicationContext)))

        getMethod.enqueue(object : Callback<List<VagaResponse>> {
            override fun onResponse(
                call: Call<List<VagaResponse>>,
                response: Response<List<VagaResponse>>
            ) {
                if (response.isSuccessful) {
                    val vagasComCandidatos = response.body()?.filter {
                        it.candidaturas.isNotEmpty()
                    }
                    vagasComCandidatos?.let {
                        recyclerViewVagasCandidatos.adapter = VagasAdapter(it)
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
