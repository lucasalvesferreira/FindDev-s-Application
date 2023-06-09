package com.example.finddev

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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface OnItemClickListener {
    fun onItemClick(vaga: VagaResponse)
}

class ListaBuscaVagasDev : AppCompatActivity() {
    private lateinit var recyclerViewVagas: RecyclerView
    private lateinit var vagasAdapter: VagasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_busca_vagas_dev)

        // Inicializa a RecyclerView
        recyclerViewVagas = findViewById(R.id.recyclerViewVagas)
        recyclerViewVagas.layoutManager = LinearLayoutManager(this)

        // Obtém a lista de vagas compatíveis
        val frenteDesenvolvimentoSelecionada = intent.getStringExtra("frenteDesenvolvimento").toString()
        val senioridadeSelecionada = intent.getStringExtra("senioridade").toString()

        buscarVagasFiltradas(frenteDesenvolvimentoSelecionada, senioridadeSelecionada)
    }

    // Classe do ViewHolder para o item da lista
    private class VagaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgVaga: ImageView = itemView.findViewById(R.id.imgVaga)
        val txtTitulo: TextView = itemView.findViewById(R.id.txtTitulo)
        val txtSubtitulo: TextView = itemView.findViewById(R.id.txtSubtitulo)
        val txtValor: TextView = itemView.findViewById(R.id.txtValor)
        val txtFrenteDesenvolvimento: TextView = itemView.findViewById(R.id.txtFrenteDesenvolvimento)
        val txtSenioridade: TextView = itemView.findViewById(R.id.txtSenioridade)
    }

    // Adaptador para o RecyclerView
    private inner class VagasAdapter(private val listaVagas: List<VagaResponse>, private val listener: OnItemClickListener) :
        RecyclerView.Adapter<VagaViewHolder>(), View.OnClickListener {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VagaViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_vaga, parent, false)
            return VagaViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: VagaViewHolder, position: Int) {
            val vaga = listaVagas[position]
            holder.imgVaga.setImageResource(R.mipmap.logo) // Defina a imagem correta para cada vaga
            holder.txtTitulo.text = vaga.titulo
            holder.txtSubtitulo.text = vaga.descricao
            holder.txtFrenteDesenvolvimento.text = vaga.funcao
            holder.txtSenioridade.text = vaga.senioridade

            // Define o clique no item da lista
            holder.itemView.setOnClickListener(this)
            holder.itemView.tag = vaga
        }

        override fun getItemCount(): Int {
            return listaVagas.size
        }

        override fun onClick(view: View) {
            val vaga = view.tag as VagaResponse
            listener.onItemClick(vaga)
        }
    }

    private fun buscarVagasFiltradas(funcao: String, senioridade: String) {
        val apiVagas = Apis.getApiVagas()
        val chamadaGet = apiVagas.getVagaFiltrada(funcao, senioridade)

        chamadaGet.enqueue(object : Callback<List<VagaResponse>> {
            override fun onResponse(
                call: Call<List<VagaResponse>>,
                response: Response<List<VagaResponse>>
            ) {
                if (response.isSuccessful) {
                    vagasAdapter = VagasAdapter(response.body()!!, object : OnItemClickListener {
                        override fun onItemClick(vaga: VagaResponse) {
                            val modalVaga = ModalVaga.newInstance(
                                vaga.titulo,
                                vaga.descricao,
                                vaga.funcao,
                                vaga.senioridade
                            )

                            // Abra o modal utilizando o FragmentManager
                            modalVaga.show(supportFragmentManager, "modal_vaga")
                        }
                    })
                    recyclerViewVagas.adapter = vagasAdapter
                } else {
                    Toast.makeText(
                        baseContext, "Ops, algo deu errado!",
                        Toast.LENGTH_SHORT
                    ).show()
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
