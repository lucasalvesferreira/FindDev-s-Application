package com.example.finddev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finddev.App.model.Vaga

class JobsEncerradosDev : AppCompatActivity() {

    private lateinit var recyclerViewVagas: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jobs_encerrados_dev)

        // Configurar o RecyclerView
        recyclerViewVagas = findViewById(R.id.recyclerViewVagas)
        recyclerViewVagas.layoutManager = LinearLayoutManager(this)
        recyclerViewVagas.adapter = VagasAdapter(getListaVagas()) // Chame o método getListaVagas() para obter a lista de vagas
    }

    // Método para obter a lista de vagas (substitua com seus dados reais)
    private fun getListaVagas(): List<Vaga> {
        // Simule uma lista de vagas
        val vagas = mutableListOf<Vaga>()
        vagas.add(Vaga(2,1, "título 1", "Subtítulo 1", "Valor: R\$ 1.000", "FRONTEND", "JUNIOR","Tet"))
        vagas.add(Vaga(1,2, "título 2", "Subtítulo 2", "Valor: R\$ 2.000", "BACKEND", "PLENO","Teste"))
        vagas.add(Vaga(11,3, "título 3", "Subtítulo 3", "Valor: R$ 3.000", "FULLSTACK", "SENIOR","Teste"))
        // Adicione mais vagas se necessário
        return vagas
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
    private inner class VagasAdapter(private val listaVagas: List<Vaga>) : RecyclerView.Adapter<VagaViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VagaViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_vaga, parent, false)
            return VagaViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: VagaViewHolder, position: Int) {
            val vaga = listaVagas[position]
            holder.imgVaga.setImageResource(R.mipmap.logo) // Defina a imagem correta para cada vaga
            holder.txtTitulo.text = vaga.titulo
            holder.txtSubtitulo.text = vaga.subtitulo
            holder.txtValor.text = vaga.valor
            holder.txtFrenteDesenvolvimento.text = vaga.frenteDesenvolvimento
            holder.txtSenioridade.text = vaga.senioridade
        }

        override fun getItemCount(): Int {
            return listaVagas.size
        }
    }
}