package com.example.finddev.dev.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.finddev.App.model.dtos.VagaResponse
import com.example.finddev.R

class ModalVagasEncerradas : DialogFragment() {

    companion object {
        fun newInstance(
            vaga: VagaResponse
        ): ModalVagasEncerradas {
            val args = Bundle().apply {
                putString("titulo", vaga.titulo)
                // TODO adicionar nome da empresa
                putString("subtitulo", vaga.descricao)
                putString("frenteDesenvolvimento", vaga.funcao)
                putString("senioridade", vaga.senioridade)
            }
            val fragment = ModalVagasEncerradas()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.ModalVagaStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_modal_vagas_encerradas, container, false)
        dialog?.setCanceledOnTouchOutside(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obter os dados da vaga do bundle
        val titulo = arguments?.getString("titulo")
        val subtitulo = arguments?.getString("subtitulo")
        val frenteDesenvolvimento = arguments?.getString("frenteDesenvolvimento")
        val senioridade = arguments?.getString("senioridade")
        val descricao = arguments?.getString("descricao")

        // Configurar os dados nos elementos do modal
        view.findViewById<TextView>(R.id.txtModalTitulo).text = titulo
        view.findViewById<TextView>(R.id.txtModalSubtitulo).text = subtitulo
        view.findViewById<TextView>(R.id.txtModalFrenteDesenvolvimento).text = frenteDesenvolvimento
        view.findViewById<TextView>(R.id.txtModalSenioridade).text = senioridade
        view.findViewById<TextView>(R.id.txtModalDescricao).text = descricao

        // Configurar o botão "Candidatar-se"
        view.findViewById<Button>(R.id.btnAvaliar).setOnClickListener {
            // Lógica para lidar com o clique do botão "Candidatar-se"

            // Exibir o ModalAvaliacaoVagaEncerrada
            val modalAvaliacaoVagaEncerrada = ModalAvaliacaoVagaEncerrada()
            modalAvaliacaoVagaEncerrada.show(parentFragmentManager, "modal_avaliacao_vaga_encerrada")

            dismiss() // Fechar o modal após o clique no botão
        }
    }
}
