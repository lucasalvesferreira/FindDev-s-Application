package com.example.finddev.empresa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finddev.App.model.Vaga
import com.example.finddev.R
import com.example.finddev.empresa.fragment.ModalVagasPublicadasEmpresa

class VagasPublicadasEmpresa : AppCompatActivity() {

    private lateinit var recyclerViewVagasPublicadas: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vagas_publicadas_empresa)

        // Configurar o RecyclerView
        recyclerViewVagasPublicadas = findViewById(R.id.recyclerViewVagasPublicadas)
        recyclerViewVagasPublicadas.layoutManager = LinearLayoutManager(this)
        recyclerViewVagasPublicadas.adapter = VagasAdapter(getListaVagas()) // Chame o método getListaVagas() para obter a lista de vagas
    }

    // Método para obter a lista de vagas (substitua com seus dados reais)
    private fun getListaVagas(): List<Vaga> {
        // Simule uma lista de vagas
        val vagas = mutableListOf<Vaga>()
        vagas.add(Vaga(2, 1, "título 90", "Subtítulo 1", "FRONTEND", "JUNIOR","Tet"))
        vagas.add(Vaga(1, 2, "título 2", "Subtítulo 2","BACKEND", "PLENO","Teste"))
        vagas.add(Vaga(11, 3, "título 3", "Subtítulo 3","FULLSTACK", "SENIOR","Teste"))
        // Adicione mais vagas se necessário
        return vagas
    }

    // Classe do ViewHolder para o item da lista
    private class VagaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgVaga: ImageView = itemView.findViewById(R.id.imgVaga)
        val txtTitulo: TextView = itemView.findViewById(R.id.txtTitulo)
        val txtSubtitulo: TextView = itemView.findViewById(R.id.txtSubtitulo)
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
            holder.txtFrenteDesenvolvimento.text = vaga.frenteDesenvolvimento
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
}