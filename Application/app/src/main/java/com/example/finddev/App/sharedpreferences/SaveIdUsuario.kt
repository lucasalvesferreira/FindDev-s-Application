package com.example.finddev.App.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import java.util.UUID

fun saveId(context: Context, id: String) {
    val sharedPreferences = context.getSharedPreferences("IdUsuario", Context.MODE_PRIVATE)
    val editor: SharedPreferences.Editor = sharedPreferences.edit()
    editor.putString("id", id)
    editor.apply()
}

fun getId(context: Context): String {
    val sharedPreferences = context.getSharedPreferences("IdUsuario", Context.MODE_PRIVATE)
    return sharedPreferences.getString("id", null)!!
}