package com.example.finddev.dev
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.finddev.R

class PerfilDevFragment : Fragment() {

    private lateinit var botaoContratantes: Button
    private lateinit var botaoEditar: ImageView
    private lateinit var tvEstado: TextView
    private lateinit var tvCidade: TextView
    private lateinit var tvNomeCompleto: TextView
    private lateinit var tvTitulo: TextView
    private lateinit var tvBiografia: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_perfil_dev, container, false)

        botaoContratantes = view.findViewById(R.id.botaoContratantes)
        botaoEditar = view.findViewById(R.id.btnEditarPerfilDev)
        tvEstado = view.findViewById(R.id.tvEstado)
        tvCidade = view.findViewById(R.id.tvCidade)
        tvNomeCompleto = view.findViewById(R.id.tvNomeCompleto)
        tvTitulo = view.findViewById(R.id.tituloPerfilDev)
        tvBiografia = view.findViewById(R.id.biografia)

        botaoContratantes.setOnClickListener {
            val fragment = JobsEncerradosDevFragment()
            val fragmentManager = requireActivity().supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(android.R.id.content, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        botaoEditar.setOnClickListener {
            val telaEditar = Intent(requireContext(), EdicaoPerfilDev::class.java)
            startActivity(telaEditar)
        }

        if (estado == null || cidade == null || nomeCompleto == null || titulo == null || biografia == null) {
            estado = arguments?.getString("estado")
            cidade = arguments?.getString("cidade")
            nomeCompleto = arguments?.getString("nomeDev")
            titulo = arguments?.getString("titulo")
            biografia = arguments?.getString("descricao")
        }

        tvEstado.text = estado
        tvCidade.text = cidade
        tvNomeCompleto.text = nomeCompleto
        tvTitulo.text = titulo
        tvBiografia.text = biografia

        return view
    }

    companion object {
        var estado: String? = null
        var cidade: String? = null
        var nomeCompleto: String? = null
        var titulo: String? = null
        var biografia: String? = null
    }
}
