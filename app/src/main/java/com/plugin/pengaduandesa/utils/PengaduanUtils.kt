package com.plugin.pengaduandesa.utils

import android.content.Context
import io.github.cdimascio.dotenv.Dotenv

class PengaduanUtils {

    companion object {
        val dotenv = Dotenv.configure().load()
        var API_ENDPOINT = dotenv["MY_ENV_VAR"]

        fun getToken(context: Context) : String? {
            val token = context.getSharedPreferences("USER", Context.MODE_PRIVATE)
            return token?.getString("TOKEN", null)
        }

        fun setToken(context : Context, token: String) {
            val pref = context.getSharedPreferences("USER", Context.MODE_PRIVATE)
            pref.edit().apply(){
                putString("TOKEN", token)
                apply()
            }
        }

        fun clearToken(context: Context){
            val pref = context.getSharedPreferences("USER", Context.MODE_PRIVATE)
            pref.edit().clear().apply()
        }

        fun isValidPassword(password : String) = password.length > 6
    }
}