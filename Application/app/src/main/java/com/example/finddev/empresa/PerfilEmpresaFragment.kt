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

    private lateinit var botaoVagasAbertas: Button
    private lateinit var botaoColaboradores: Button
    private lateinit var botaoContratar: Button
    private lateinit var tvNomeEmpresa: TextView
    private lateinit var tvResumo: TextView
    private lateinit var tvBiografia: TextView
    private lateinit var tvEstado: TextView
    private lateinit var tvCidade: TextView
    private lateinit var tvBairro: TextView
    private lateinit var tvEndereço: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_perfil_empresa, container, false)

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
        tvEstado = rootView.findViewById(R.id.tvEstado)
        tvCidade = rootView.findViewById(R.id.tvCidade)
        tvBairro = rootView.findViewById(R.id.tvBairro)
        tvEndereço = rootView.findViewById(R.id.tvEndereco)
        tvNomeEmpresa = rootView.findViewById(R.id.tvNomeEmpresa)
        tvResumo = rootView.findViewById(R.id.tvResumo)
        tvBiografia = rootView.findViewById(R.id.tvBiografia)

        // Obter as informações enviadas pela tela de login
        val estado = arguments?.getString("estado")
        val cidade = arguments?.getString("cidade")
        val bairro = arguments?.getString("bairro")
        val endereco = arguments?.getString("endereco")
        val nomeCompleto = arguments?.getString("nomeEmpresa")
        val descricaoResumo = arguments?.getString("descricaoResumo")
        val descricaoBiografia = arguments?.getString("descricaoBiografia")

        // Definir as informações nos elementos da tela
        tvEstado.text = estado
        tvCidade.text = cidade
        tvBairro.text = bairro
        tvEndereço.text = endereco
        tvNomeEmpresa.text = nomeCompleto
        tvResumo.text = descricaoResumo
        tvBiografia.text = descricaoBiografia

        return rootView
    }

    fun telaEditarEmpresa(componente: View) {
        val telaPerfilEmpresa = Intent(requireContext(), EdicaoPerfilEmpresa::class.java)
        startActivity(telaPerfilEmpresa)
    }
}
