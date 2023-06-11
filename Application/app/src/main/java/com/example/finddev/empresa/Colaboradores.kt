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
import com.example.finddev.App.model.VagaColaboradores
import com.example.finddev.R
import com.example.finddev.empresa.fragment.ModalColaboradores

class Colaboradores : AppCompatActivity() {

    private lateinit var recyclerViewVagasColaboradores: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_colaboradores)

        // Configurar o RecyclerView
        recyclerViewVagasColaboradores = findViewById(R.id.recyclerViewVagasColaboradores)
        recyclerViewVagasColaboradores.layoutManager = LinearLayoutManager(this)
        recyclerViewVagasColaboradores.adapter = VagasAdapter(getListaVagas()) // Chame o método getListaVagas() para obter a lista de vagas
    }

    // Método para obter a lista de vagas (substitua com seus dados reais)
    private fun getListaVagas(): List<VagaColaboradores> {
        // Simule uma lista de vagas
        val vagas = mutableListOf<VagaColaboradores>()
        vagas.add(VagaColaboradores(2, 1, "título 1","Carlos" ,"Subtítulo 1", "FRONTEND", "JUNIOR","Tet"))
        vagas.add(VagaColaboradores(1, 2, "título 2", "Danilo","Subtítulo 2","BACKEND", "PLENO","Teste"))
        vagas.add(VagaColaboradores(11, 3, "título 3", "Paulo","Subtítulo 3","FULLSTACK", "SENIOR","Teste"))
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
    private inner class VagasAdapter(private val listaVagas: List<VagaColaboradores>) : RecyclerView.Adapter<VagaViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VagaViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_vaga_colaboradores, parent, false)
            return VagaViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: VagaViewHolder, position: Int) {
            val vaga = listaVagas[position]
            holder.imgVaga.setImageResource(R.mipmap.logo) // Defina a imagem correta para cada vaga
            holder.txtTitulo.text = vaga.titulo
            holder.txtNomeDev.text = vaga.nome_dev

            // Configurar o clique no item da lista para abrir o ModalVagasEncerradas
            holder.itemView.setOnClickListener {
                val modalColaboradores = ModalColaboradores.newInstance(vaga) // Crie uma instância do ModalVagasEncerradas e passe a vaga selecionada
                modalColaboradores.show(supportFragmentManager, "ModalColaboradores") // Exiba o modal
            }
        }

        override fun getItemCount(): Int {
            return listaVagas.size
        }
    }
}
