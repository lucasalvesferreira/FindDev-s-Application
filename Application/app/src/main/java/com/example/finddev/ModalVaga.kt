package com.example.finddev

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.finddev.R

class ModalVaga : DialogFragment() {

    companion object {
        fun newInstance(
            titulo: String,
            subtitulo: String,
            valor: String,
            frenteDesenvolvimento: String,
            senioridade: String,
            descricao: String
        ): ModalVaga {
            val args = Bundle().apply {
                putString("titulo", titulo)
                putString("subtitulo", subtitulo)
                putString("valor", valor)
                putString("frenteDesenvolvimento", frenteDesenvolvimento)
                putString("senioridade", senioridade)
                putString("descricao", descricao)
            }

            val fragment = ModalVaga()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.ModalVagaStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_modal_vaga, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obter os dados da vaga do bundle
        val titulo = arguments?.getString("titulo")
        val subtitulo = arguments?.getString("subtitulo")
        val valor = arguments?.getString("valor")
        val frenteDesenvolvimento = arguments?.getString("frenteDesenvolvimento")
        val senioridade = arguments?.getString("senioridade")
        val descricao = arguments?.getString("descricao")

        // Configurar os dados nos elementos do modal
        view.findViewById<TextView>(R.id.txtModalTitulo).text = titulo
        view.findViewById<TextView>(R.id.txtModalSubtitulo).text = subtitulo
        view.findViewById<TextView>(R.id.txtModalValor).text = valor
        view.findViewById<TextView>(R.id.txtModalFrenteDesenvolvimento).text = frenteDesenvolvimento
        view.findViewById<TextView>(R.id.txtModalSenioridade).text = senioridade
        view.findViewById<TextView>(R.id.txtModalDescricao).text = descricao

        // Configurar o botão "Candidatar-se"
        view.findViewById<Button>(R.id.btnCandidatar).setOnClickListener {
            // Lógica para lidar com o clique do botão "Candidatar-se"
            // Implemente aqui a ação desejada
            dismiss() // Fechar o modal após o clique no botão
        }
    }
}