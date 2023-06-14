package com.example.finddev.empresa

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.finddev.R

class PerfilEmpresaFragment : Fragment() {

    private lateinit var botaoEditarPerfil: View
    private lateinit var botaoVagasAbertas: Button
    private lateinit var botaoColaboradores: Button
    private lateinit var botaoContratar: Button

    private lateinit var tvNomeEmpresa: TextView
    private lateinit var tvEstado: TextView
    private lateinit var tvCidade: TextView
    private lateinit var tvBairro: TextView
    private lateinit var tvEndereco: TextView
    private lateinit var tvTitulo: TextView
    private lateinit var tvBiografia: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_perfil_empresa, container, false)

        botaoEditarPerfil = rootView.findViewById(R.id.botaoEditarPerfilEmpresa)
        botaoEditarPerfil.setOnClickListener {
            val intent = Intent(requireContext(), EdicaoPerfilEmpresa::class.java)
            startActivity(intent)
        }
        botaoVagasAbertas = rootView.findViewById(R.id.botaoVagasAbertas)
        botaoVagasAbertas.setOnClickListener {
            val intent = Intent(requireContext(), VagasPublicadasEmpresa::class.java)
            startActivity(intent)
        }
        botaoColaboradores = rootView.findViewById(R.id.botaoColaboradores)
        botaoColaboradores.setOnClickListener {
            val intent = Intent(requireContext(), Colaboradores::class.java)
            startActivity(intent)
        }
        botaoContratar = rootView.findViewById(R.id.botaoContratar)
        botaoContratar.setOnClickListener {
            val intent = Intent(requireContext(), Candidatos::class.java)
            startActivity(intent)
        }

        tvNomeEmpresa = rootView.findViewById(R.id.tvNomeEmpresa)
        tvEstado = rootView.findViewById(R.id.tvEstado)
        tvCidade = rootView.findViewById(R.id.tvCidade)
        tvBairro = rootView.findViewById(R.id.tvBairro)
        tvEndereco = rootView.findViewById(R.id.tvEndereco)
        tvTitulo = rootView.findViewById(R.id.tvTitulo)
        tvBiografia = rootView.findViewById(R.id.tvBiografia)

        if (estado == null || cidade == null || bairro == null || endereco == null || nomeEmpresa == null || titulo == null || descricao == null) {
            nomeEmpresa = arguments?.getString("nomeEmpresa")
            estado = arguments?.getString("estado")
            cidade = arguments?.getString("cidade")
            bairro = arguments?.getString("bairro")
            endereco = arguments?.getString("endereco")
            titulo = arguments?.getString("titulo")
            descricao = arguments?.getString("descricao")
        }

        // Definir as informações nos elementos da tela
        tvNomeEmpresa.text = nomeEmpresa
        tvEstado.text = estado
        tvCidade.text = cidade
        tvBairro.text = bairro
        tvEndereco.text = endereco
        tvTitulo.text = titulo
        tvBiografia.text = descricao

        return rootView
    }

    companion object {
        var nomeEmpresa: String? = null
        var estado: String? = null
        var cidade: String? = null
        var bairro: String? = null
        var endereco: String? = null
        var titulo: String? = null
        var descricao: String? = null
    }
}
