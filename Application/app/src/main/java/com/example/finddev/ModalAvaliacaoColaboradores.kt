package com.example.finddev

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RatingBar
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class ModalAvaliacaoColaboradores : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.ModalVagaStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_modal_avaliacao_colaboradores, container, false)
        dialog?.setCanceledOnTouchOutside(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ratingBar: RatingBar = view.findViewById(R.id.ratingBar)

        // Configurar o clique no botão "Avaliar"
        view.findViewById<Button>(R.id.btnAvaliarColaborador).setOnClickListener {
            val rating = ratingBar.rating
            val comentario = "Adicione o código para obter o comentário do usuário aqui"

            // Verificar se o usuário selecionou uma avaliação
            if (rating > 0) {
                // TODO: Enviar a avaliação e o comentário para o servidor ou realizar outra ação necessária
                // Exemplo de exibição da avaliação e do comentário
                Toast.makeText(requireContext(), "Avaliação: $rating estrela(s)\nComentário: $comentario", Toast.LENGTH_SHORT).show()

                // Fechar o modal após um breve atraso
                Handler().postDelayed({
                    dismiss()

                    // Navegar para a tela "buscaVagaDev" após o fechamento do modal
                    val buscaVagaDevIntent = Intent(requireContext(), Colaboradores::class.java)
                    startActivity(buscaVagaDevIntent)
                }, 1000) // Tempo em milissegundos
            } else {
                Toast.makeText(requireContext(), "Por favor, selecione uma avaliação", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
