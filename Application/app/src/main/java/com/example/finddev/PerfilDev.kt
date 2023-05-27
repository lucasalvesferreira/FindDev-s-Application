package com.example.finddev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class PerfilDev : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_dev)

        val headerSetaVoltar = HeaderSetaVoltar()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, headerSetaVoltar)
            .commit()

    }
}