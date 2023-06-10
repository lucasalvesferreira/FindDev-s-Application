package com.example.finddev

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

class ModalCandidatoContratado : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.ModalVagaStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_modal_candidato_contratado, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Fechar o modal após 1 segundo
        Handler().postDelayed({
            dismiss()
            // Navegar para a tela "buscaVagaDev" após o fechamento do modal
            val buscaVagaDevIntent = Intent(requireContext(), Candidatos::class.java)
            startActivity(buscaVagaDevIntent)
        }, 2000)
    }
}
