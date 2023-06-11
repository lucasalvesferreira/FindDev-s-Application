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
import com.example.finddev.App.model.VagaCandidato
import com.example.finddev.R
import com.example.finddev.empresa.fragment.ModalCandidatos

class Candidatos : AppCompatActivity() {

    private lateinit var recyclerViewVagasCandidatos: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_candidatos)

        // Configurar o RecyclerView
        recyclerViewVagasCandidatos = findViewById(R.id.recyclerViewVagasCandidatos)
        recyclerViewVagasCandidatos.layoutManager = LinearLayoutManager(this)
        recyclerViewVagasCandidatos.adapter = VagasAdapter(getListaVagas()) // Chame o método getListaVagas() para obter a lista de vagas
    }

    // Método para obter a lista de vagas (substitua com seus dados reais)
    private fun getListaVagas(): List<VagaCandidato> {
        // Simule uma lista de vagas
        val vagas = mutableListOf<VagaCandidato>()
        vagas.add(VagaCandidato(2, 1, "Desenvolvedor Front-end Javascript","Carlos", "FRONTEND", "JUNIOR","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc sed luctus turpis. Nullam ut odio sit amet nibh ullamcorper pulvinar. Etiam ac molestie sapien. Donec ut porttitor est. Donec iaculis faucibus sodales. Fusce vel nunc tincidunt, luctus augue in, interdum mauris. Mauris faucibus rhoncus nisi non ultricies. Suspendisse accumsan turpis nec euismod congue. Nullam porttitor, orci at tempus ultricies, odio dui iaculis leo, at accumsan nunc ante sit amet diam."))
        vagas.add(VagaCandidato(1, 2, "Desenvolvedor FullStack Kotlin", "Danilo","BACKEND", "PLENO","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc sed luctus turpis. Nullam ut odio sit amet nibh ullamcorper pulvinar. Etiam ac molestie sapien. Donec ut porttitor est. Donec iaculis faucibus sodales. Fusce vel nunc tincidunt, luctus augue in, interdum mauris. Mauris faucibus rhoncus nisi non ultricies. Suspendisse accumsan turpis nec euismod congue. Nullam porttitor, orci at tempus ultricies, odio dui iaculis leo, at accumsan nunc ante sit amet diam."))
        vagas.add(VagaCandidato(11, 3, "Desenvolvedor Front-end React", "Paulo","FULLSTACK", "SENIOR","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc sed luctus turpis. Nullam ut odio sit amet nibh ullamcorper pulvinar. Etiam ac molestie sapien. Donec ut porttitor est. Donec iaculis faucibus sodales. Fusce vel nunc tincidunt, luctus augue in, interdum mauris. Mauris faucibus rhoncus nisi non ultricies. Suspendisse accumsan turpis nec euismod congue. Nullam porttitor, orci at tempus ultricies, odio dui iaculis leo, at accumsan nunc ante sit amet diam."))
        // Adicione mais vagas se necessário
        return vagas
    }

    // Classe do ViewHolder para o item da lista
    private class VagaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgVaga: ImageView = itemView.findViewById(R.id.imgVaga)
        val txtTitulo: TextView = itemView.findViewById(R.id.txtTitulo)
        val txtNomeDev: TextView = itemView.findViewById(R.id.txtNomeDev)
    }

    // Adaptador para o RecyclerView
    private inner class VagasAdapter(private val listaVagas: List<VagaCandidato>) : RecyclerView.Adapter<VagaViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VagaViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_vaga_candidatos, parent, false)
            return VagaViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: VagaViewHolder, position: Int) {
            val vaga = listaVagas[position]
            holder.imgVaga.setImageResource(R.mipmap.logo) // Defina a imagem correta para cada vaga
            holder.txtTitulo.text = vaga.titulo
            holder.txtNomeDev.text = vaga.nome_dev

            // Configurar o clique no item da lista para abrir o ModalVagasEncerradas
            holder.itemView.setOnClickListener {
                val modalCandidatos = ModalCandidatos.newInstance(vaga) // Crie uma instância do ModalVagasEncerradas e passe a vaga selecionada
                modalCandidatos.show(supportFragmentManager, "ModalCandidatos") // Exiba o modal
            }
        }

        override fun getItemCount(): Int {
            return listaVagas.size
        }
    }
}
