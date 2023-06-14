package com.example.finddev.empresa.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.finddev.App.api.Apis
import com.example.finddev.App.model.UsuarioModel
import com.example.finddev.App.model.dtos.ContratacaoRequest
import com.example.finddev.App.model.dtos.VagaResponse
import com.example.finddev.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class ModalCandidatos : DialogFragment() {

    companion object {
        fun newInstance(
            vaga: VagaResponse,
            dev: UsuarioModel
        ): ModalCandidatos {
            val args = Bundle().apply {
                putString("idVaga", vaga.id.toString())
                putString("idDev", dev.id)
                putString("tituloVaga", vaga.titulo)
                putString("funcaoVaga", vaga.funcao)
                putString("senioridadeVaga", vaga.senioridade)
                putString("nomeDev", dev.nome)
                putString("emailDev", dev.email)
                putString("telefoneDev", dev.telefone)
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
        val tituloVaga = arguments?.getString("tituloVaga")
        val funcaoVaga = arguments?.getString("funcaoVaga")
        val senioridadeVaga = arguments?.getString("senioridadeVaga")
        val nomeDev = arguments?.getString("nomeDev")
        val emailDev = arguments?.getString("emailDev")
        val telefoneDev = arguments?.getString("telefoneDev")

        // Configurar os dados nos elementos do modal
        view.findViewById<TextView>(R.id.txtTituloVaga).text = tituloVaga
        view.findViewById<TextView>(R.id.txtFuncaoVaga).text = funcaoVaga
        view.findViewById<TextView>(R.id.txtSenioridadeVaga).text = senioridadeVaga
        view.findViewById<TextView>(R.id.txtNomeCandidato).text = nomeDev
        view.findViewById<TextView>(R.id.txtEmailCandidato).text = emailDev
        view.findViewById<TextView>(R.id.txtTelefoneCandidato).text = telefoneDev

        // Configurar o botão "Contratar"
        view.findViewById<Button>(R.id.btnContratar).setOnClickListener {
            contratar()
        }
    }

    private fun contratar() {
        val apiVagas = Apis.getApiVagas()
        val request = ContratacaoRequest(
            arguments?.getString("idVaga")?.toInt()!!,
            UUID.fromString(arguments?.getString("idDev"))
        )
        val patchMethod = apiVagas.contratar(request)

        patchMethod.enqueue(object : Callback<VagaResponse> {
            override fun onResponse(call: Call<VagaResponse>, response: Response<VagaResponse>) {
                if (response.isSuccessful) {
                    val modalCandidatoContratado = ModalCandidatoContratado()
                    modalCandidatoContratado.show(parentFragmentManager, "modal_candidato_contratado")

                    dismiss() // Fechar o modal após o clique no botão
                } else {
                    println("status code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<VagaResponse>, t: Throwable) {
                Toast.makeText(
                    context, "Erro na API: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
                t.printStackTrace()
            }
        })
    }
}
