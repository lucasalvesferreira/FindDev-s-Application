package com.example.finddev.dev.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.finddev.R
import com.example.finddev.dev.BuscaVagaDev

class ModalConfirmacaoCandidatura : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.ModalVagaStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_modal_confirmacao_candidatura, container, false)
        dialog?.setCanceledOnTouchOutside(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Fechar o modal após 1 segundo
        Handler().postDelayed({
            dismiss()
            // Navegar para a tela "buscaVagaDev" após o fechamento do modal
            val buscaVagaDevIntent = Intent(requireContext(), BuscaVagaDev::class.java)
            startActivity(buscaVagaDevIntent)
        }, 1000)
    }
}