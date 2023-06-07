package com.example.finddev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finddev.App.model.dtos.Vaga

interface OnItemClickListener {
    fun onItemClick(vaga: Vaga)
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
        val vagasCompativeis = getVagasCompativeis()

        // Configura o adapter com a lista de vagas
        vagasAdapter = VagasAdapter(vagasCompativeis, object : OnItemClickListener {
            override fun onItemClick(vaga: Vaga) {
                val modalVaga = ModalVaga.newInstance(
                    vaga.titulo,
                    vaga.subtitulo,
                    vaga.valor,
                    vaga.frenteDesenvolvimento,
                    vaga.senioridade,
                    vaga.descricao
                )

                // Abra o modal utilizando o FragmentManager
                modalVaga.show(supportFragmentManager, "modal_vaga")
            }
        })
        recyclerViewVagas.adapter = vagasAdapter
    }

    private fun getVagasCompativeis(): List<Vaga> {
        val frenteDesenvolvimentoSelecionada = intent.getStringExtra("frenteDesenvolvimento")
        val senioridadeSelecionada = intent.getStringExtra("senioridade")

        // Faz a lógica para filtrar as vagas compatíveis com os critérios selecionados
        val vagasCompativeis = mutableListOf<Vaga>()
        val listaVagas = getListaVagas() // Obtém todas as vagas

        // Filtra as vagas com base nos critérios selecionados
        for (vaga in listaVagas) {
            if (vaga.frenteDesenvolvimento == frenteDesenvolvimentoSelecionada &&
                vaga.senioridade == senioridadeSelecionada
            ) {
                vagasCompativeis.add(vaga)
            }
        }

        return vagasCompativeis
    }

    // Método para obter a lista de vagas (substitua com seus dados reais)
    private fun getListaVagas(): List<Vaga> {
        // Simule uma lista de vagas
        val vagas = mutableListOf<Vaga>()
        vagas.add(Vaga(2,1, "título 1", "Subtítulo 1", "Valor: R\$ 1.000", "FRONTEND", "JUNIOR","Teste"))
        vagas.add(Vaga(1,2, "título 2", "Subtítulo 2", "Valor: R\$ 2.000", "BACKEND", "PLENO","Testr"))
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
    private inner class VagasAdapter(private val listaVagas: List<Vaga>, private val listener: OnItemClickListener) :
        RecyclerView.Adapter<VagaViewHolder>(), View.OnClickListener {

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

            // Define o clique no item da lista
            holder.itemView.setOnClickListener(this)
            holder.itemView.tag = vaga
        }

        override fun getItemCount(): Int {
            return listaVagas.size
        }

        override fun onClick(view: View) {
            val vaga = view.tag as Vaga
            listener.onItemClick(vaga)
        }
    }
}
