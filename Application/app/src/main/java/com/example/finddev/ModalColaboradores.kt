package com.example.finddev

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.finddev.App.model.VagaColaboradores

class ModalColaboradores : DialogFragment() {

    companion object {
        fun newInstance(
            vaga: VagaColaboradores
        ): ModalColaboradores {
            val args = Bundle().apply {
                putString("titulo", vaga.titulo)
                putString("nomeDev", vaga.nome_dev)
                putString("frenteDesenvolvimento", vaga.frenteDesenvolvimento)
                putString("senioridade", vaga.senioridade)
                putString("descricao", vaga.descricao)
            }
            val fragment = ModalColaboradores()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.ModalVagaStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_modal_colaboradores, container, false)
        dialog?.setCanceledOnTouchOutside(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obter os dados da vaga do bundle
        val titulo = arguments?.getString("titulo")
        val nomeDev = arguments?.getString("nomeDev")
        val frenteDesenvolvimento = arguments?.getString("frenteDesenvolvimento")
        val senioridade = arguments?.getString("senioridade")
        val descricao = arguments?.getString("descricao")

        // Configurar os dados nos elementos do modal
        view.findViewById<TextView>(R.id.txtModalTitulo).text = titulo
        view.findViewById<TextView>(R.id.txtNomeDev).text = nomeDev
        view.findViewById<TextView>(R.id.txtModalFrenteDesenvolvimento).text = frenteDesenvolvimento
        view.findViewById<TextView>(R.id.txtModalSenioridade).text = senioridade
        view.findViewById<TextView>(R.id.txtModalDescricao).text = descricao

        // Configurar o botão "Candidatar-se"
        view.findViewById<Button>(R.id.btnEncerrarContrato).setOnClickListener {
            // Lógica para lidar com o clique do botão "Candidatar-se"

            // Exibir o ModalAvaliacaoVagaEncerrada
            val modalAvaliacaoColaboradores = ModalAvaliacaoColaboradores()
            modalAvaliacaoColaboradores.show(parentFragmentManager, "modal_avaliacao_colaboradores")

            dismiss() // Fechar o modal após o clique no botão
        }
    }
}
