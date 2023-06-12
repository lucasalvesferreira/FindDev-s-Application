package com.example.finddev.empresa.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.finddev.App.model.VagaCandidato
import com.example.finddev.R

class ModalCandidatos : DialogFragment() {

    companion object {
        fun newInstance(
            vaga: VagaCandidato
        ): ModalCandidatos {
            val args = Bundle().apply {
                putString("nomeDev", vaga.nome_dev)
                putString("frenteDesenvolvimento", vaga.frenteDesenvolvimento)
                putString("senioridade", vaga.senioridade)
                putString("experiencia", vaga.experiencia)
            }
            val fragment = ModalCandidatos()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.ModalVagaStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_modal_candidatos, container, false)
        dialog?.setCanceledOnTouchOutside(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obter os dados da vaga do bundle
        val nomeDev = arguments?.getString("nomeDev")
        val frenteDesenvolvimento = arguments?.getString("frenteDesenvolvimento")
        val senioridade = arguments?.getString("senioridade")
        val experiencia = arguments?.getString("experiencia")

        // Configurar os dados nos elementos do modal
        view.findViewById<TextView>(R.id.txtNomeDev).text = nomeDev
        view.findViewById<TextView>(R.id.txtModalFrenteDesenvolvimento).text = frenteDesenvolvimento
        view.findViewById<TextView>(R.id.txtModalSenioridade).text = senioridade
        view.findViewById<TextView>(R.id.txtExperiencia).text = experiencia

        // Configurar o botão "Candidatar-se"
        view.findViewById<Button>(R.id.btnContratar).setOnClickListener {
            // Lógica para lidar com o clique do botão "Candidatar-se"

            // Exibir o ModalAvaliacaoVagaEncerrada
            val modalCandidatoContratado = ModalCandidatoContratado()
            modalCandidatoContratado.show(parentFragmentManager, "modal_candidato_contratado")

            dismiss() // Fechar o modal após o clique no botão
        }
    }
}
