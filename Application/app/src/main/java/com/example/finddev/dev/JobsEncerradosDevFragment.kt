package com.example.finddev.dev
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finddev.App.model.Vaga
import com.example.finddev.R
import com.example.finddev.dev.fragment.ModalVagasEncerradas

class JobsEncerradosDevFragment : Fragment() {

    private lateinit var recyclerViewVagas: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_jobs_encerrados_dev, container, false)

        recyclerViewVagas = view.findViewById(R.id.recyclerViewVagas)
        recyclerViewVagas.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewVagas.adapter = VagasAdapter(getListaVagas())

        return view
    }

    private fun getListaVagas(): List<Vaga> {
        val vagas = mutableListOf<Vaga>()
        vagas.add(Vaga(2, 1, "título 1","Subtítulo 1", "FRONTEND", "JUNIOR","Tet"))
        vagas.add(Vaga(1, 2, "título 2", "Subtítulo 2","BACKEND", "PLENO","Teste"))
        vagas.add(Vaga(11, 3, "título 3", "Subtítulo 3","FULLSTACK", "SENIOR","Teste"))
        return vagas
    }

    private class VagaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgVaga: ImageView = itemView.findViewById(R.id.imgVaga)
        val txtTitulo: TextView = itemView.findViewById(R.id.txtTitulo)
        val txtSubtitulo: TextView = itemView.findViewById(R.id.txtSubtitulo)
        val txtFrenteDesenvolvimento: TextView = itemView.findViewById(R.id.txtFrenteDesenvolvimento)
        val txtSenioridade: TextView = itemView.findViewById(R.id.txtSenioridade)
    }

    private inner class VagasAdapter(private val listaVagas: List<Vaga>) : RecyclerView.Adapter<VagaViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VagaViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_vaga_jobs_encerrados, parent, false)
            return VagaViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: VagaViewHolder, position: Int) {
            val vaga = listaVagas[position]
            holder.imgVaga.setImageResource(R.mipmap.logo)
            holder.txtTitulo.text = vaga.titulo
            holder.txtSubtitulo.text = vaga.subtitulo
            holder.txtFrenteDesenvolvimento.text = vaga.frenteDesenvolvimento
            holder.txtSenioridade.text = vaga.senioridade

            holder.itemView.setOnClickListener {
                val modalVagasEncerradas = ModalVagasEncerradas.newInstance(vaga)
                modalVagasEncerradas.show(requireFragmentManager(), "ModalVagasEncerradas")
            }
        }

        override fun getItemCount(): Int {
            return listaVagas.size
        }
    }
}
