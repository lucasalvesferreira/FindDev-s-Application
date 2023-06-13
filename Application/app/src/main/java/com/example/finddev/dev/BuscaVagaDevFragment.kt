package com.example.finddev.dev

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.finddev.R

class BuscaVagaDevFragment : Fragment() {

    private lateinit var spinnerFrenteDesenvolvimento: Spinner
    private lateinit var spinnerSenioridade: Spinner
    private lateinit var btnProcurar: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_busca_vaga_dev, container, false)

        spinnerFrenteDesenvolvimento = view.findViewById(R.id.spinnerFrenteDesenvolvimento)
        spinnerSenioridade = view.findViewById(R.id.spinnerSenioridade)
        btnProcurar = view.findViewById(R.id.btnProcurar)

        val backButton = view.findViewById<ImageView>(R.id.backButton)
        backButton.setOnClickListener {
            requireActivity().onBackPressed() // Volta para a tela anterior
        }

        // Configurar os adapters para os spinners
        val frenteDesenvolvimentoAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.frentes_desenvolvimento,
            R.layout.spinner_item
        )
        frenteDesenvolvimentoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFrenteDesenvolvimento.adapter = frenteDesenvolvimentoAdapter

        val senioridadeAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.senioridades,
            R.layout.spinner_item
        )
        senioridadeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSenioridade.adapter = senioridadeAdapter

        // Definir o listener para o botão "Procurar"
        btnProcurar.setOnClickListener {
            // Obter os valores selecionados nos spinners
            val frenteDesenvolvimento = spinnerFrenteDesenvolvimento.selectedItem.toString()
            val senioridade = spinnerSenioridade.selectedItem.toString()

            // Criar um Intent para iniciar a ListaVagasActivity
            val intent = Intent(requireContext(), ListaBuscaVagasDev::class.java)
            // Passar os valores selecionados como extras para a próxima Activity
            intent.putExtra("frenteDesenvolvimento", frenteDesenvolvimento)
            intent.putExtra("senioridade", senioridade)
            // Iniciar a próxima Activity
            startActivity(intent)
        }

        return view
    }
}
