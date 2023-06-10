package com.example.finddev.App.sharedpreferences

import android.content.Context
import android.content.SharedPreferences

fun saveIdUser(context: Context, id: String) {
    val sharedPreferences = context.getSharedPreferences("IdUsuario", Context.MODE_PRIVATE)
    val editor: SharedPreferences.Editor = sharedPreferences.edit()
    editor.putString("id", id)
    editor.apply()
}

fun getIdUser(context: Context): String {
    val sharedPreferences = context.getSharedPreferences("IdUsuario", Context.MODE_PRIVATE)
    return sharedPreferences.getString("id", null)!!
}

fun saveIdVaga(context: Context, id: Int) {
    val sharedPreferences = context.getSharedPreferences("IdVaga", Context.MODE_PRIVATE)
    val editor: SharedPreferences.Editor = sharedPreferences.edit()
    editor.putInt("idVaga", id)
    editor.apply()
}

fun getIdVaga(context: Context): Int {
    val sharedPreferences = context.getSharedPreferences("IdVaga", Context.MODE_PRIVATE)
    return sharedPreferences.getInt("idVaga", -1)
}
