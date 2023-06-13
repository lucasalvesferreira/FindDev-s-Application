package com.example.finddev.dev

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.finddev.R

class PosLoginDevFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pos_login_dev, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.btnEncontre).setOnClickListener {
            openBuscaVagaFragment()
        }

        view.findViewById<View>(R.id.btnPerfil).setOnClickListener {
            openPerfilDevFragment()
        }
    }

    private fun openBuscaVagaFragment() {
        val buscaVagaFragment = BuscaVagaDevFragment()
        parentFragmentManager.beginTransaction()
            .replace(android.R.id.content, buscaVagaFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun openPerfilDevFragment() {
        val perfilDevFragment = PerfilDevFragment()

        val args = Bundle()
        args.putString("nomeDev", arguments?.getString("nomeDev"))
        args.putString("estado", arguments?.getString("estado"))
        args.putString("cidade", arguments?.getString("cidade"))
        perfilDevFragment.arguments = args

        parentFragmentManager.beginTransaction()
            .replace(android.R.id.content, perfilDevFragment)
            .addToBackStack(null)
            .commit()
    }
}
