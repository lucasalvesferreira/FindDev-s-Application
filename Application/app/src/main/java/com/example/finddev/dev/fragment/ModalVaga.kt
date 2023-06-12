package com.example.finddev.dev.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.finddev.App.api.Apis
import com.example.finddev.App.model.dtos.CandidaturaRequest
import com.example.finddev.App.model.dtos.CandidaturaResponse
import com.example.finddev.App.sharedpreferences.getIdUser
import com.example.finddev.App.sharedpreferences.getIdVaga
import com.example.finddev.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModalVaga : DialogFragment() {

    companion object {
        fun newInstance(
            titulo: String,
            descricao: String,
            funcao: String,
            senioridade: String
        ): ModalVaga {
            val args = Bundle().apply {
                putString("titulo", titulo)
                putString("subtitulo", descricao)
                putString("frenteDesenvolvimento", funcao)
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
        view.findViewById<Button>(R.id.btnCandidatar).setOnClickListener {
            criarCandidatura()

            val confirmacaoCandidatura = ModalConfirmacaoCandidatura()

            confirmacaoCandidatura.show(parentFragmentManager, "ModalConfirmacaoCandidatura")

            dismiss()
        }
    }

    private fun criarCandidatura() {
        val idDesenvolvedor = getIdUser(context!!)
        val idVaga = getIdVaga(context!!)

        val candidaturaRequest = CandidaturaRequest(idDesenvolvedor, idVaga)

        val apiCandidatura = Apis.getApiCandidatura()
        val createCandidatura = apiCandidatura.createCandidatura(candidaturaRequest)

        createCandidatura.enqueue(object : Callback<CandidaturaResponse> {
            override fun onResponse(
                call: Call<CandidaturaResponse>,
                response: Response<CandidaturaResponse>
            ) {
                println("body ${response.body()}")
                println("id dev $idDesenvolvedor - id vaga $idVaga")
                if (!response.isSuccessful) {
                    println("error code ${response.code()}")
                }
            }

            override fun onFailure(call: Call<CandidaturaResponse>, t: Throwable) {
                Toast.makeText(
                    context, "Erro na API: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
                t.printStackTrace()
            }
        })
    }
}
