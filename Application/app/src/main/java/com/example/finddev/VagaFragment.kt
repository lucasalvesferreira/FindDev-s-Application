package com.example.finddev

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.finddev.App.model.dtos.VagaModel


/**
 * A simple [Fragment] subclass.
 * Use the [VagaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class VagaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vagas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val vaga = arguments?.getSerializable("vaga") as VagaModel?

        val tvTitulo = view.findViewById<TextView>(R.id.tv_titulo)
        val tvDescricao = view.findViewById<TextView>(R.id.tv_descricao)

        tvTitulo.text = vaga!!.titulo
        tvDescricao.text = vaga!!.descricao
    }
}