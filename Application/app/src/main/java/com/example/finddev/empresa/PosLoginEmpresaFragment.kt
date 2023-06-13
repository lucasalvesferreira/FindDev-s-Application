package com.example.finddev.empresa

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.finddev.R
import com.example.finddev.empresa.PerfilEmpresaFragment

class PosLoginEmpresaFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pos_login_empresa, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.btnCriarVagas).setOnClickListener {
            openCriacaoVagasFragment()
        }

        view.findViewById<View>(R.id.perfilEmpresa).setOnClickListener {
            openPerfilEmpresaFragment()
        }
    }

    private fun openCriacaoVagasFragment() {
        val criacaoDeVagas = Intent(requireContext(), PublicacaoDeVagas::class.java)
        startActivity(criacaoDeVagas)
    }

    private fun openPerfilEmpresaFragment() {
        // Obter as informações enviadas pela tela de login
        val nomeEmpresa = arguments?.getString("nomeEmpresa")
        val estado = arguments?.getString("estado")
        val cidade = arguments?.getString("cidade")
        val bairro = arguments?.getString("bairro")
        val endereco = arguments?.getString("endereco")

        // Criar uma instância do fragmento de perfil da empresa
        val perfilEmpresaFragment = PerfilEmpresaFragment()

        // Passar os argumentos para o fragmento de perfil da empresa
        val args = Bundle().apply {
            putString("nomeEmpresa", nomeEmpresa)
            putString("estado", estado)
            putString("cidade", cidade)
            putString("bairro", bairro)
            putString("endereco", endereco)
        }
        perfilEmpresaFragment.arguments = args

        // Navegar para o fragmento de perfil da empresa
        parentFragmentManager.beginTransaction()
            .replace(android.R.id.content, perfilEmpresaFragment)
            .addToBackStack(null)
            .commit()
    }
}
