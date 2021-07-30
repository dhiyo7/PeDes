package com.plugin.pengaduandesa.utils

import android.content.Context

class PengaduanUtils {
    companion object {
        var API_ENDPOINT = "https://complaint-apps.herokuapp.com/"

        fun getToken(context: Context): String? {
            val token = context.getSharedPreferences("USER", Context.MODE_PRIVATE)
            return token?.getString("TOKEN", null)
        }

        fun setToken(context: Context, token: String) {
            val pref = context.getSharedPreferences("USER", Context.MODE_PRIVATE)
            pref.edit().apply {
                putString("TOKEN", token)
                apply()
            }
        }

        fun getDeviceToken(context: Context): String? {
            val token = context.getSharedPreferences("DEVICE_TOKEN", Context.MODE_PRIVATE)
            return token?.getString("DEVICE_TOKEN", null)
        }

        fun setDeviceToken(context: Context, token: String) {
            val pref = context.getSharedPreferences("DEVICE_TOKEN", Context.MODE_PRIVATE)
            pref.edit().apply {
                putString("DEVICE_TOKEN", token)
                apply()
            }
        }

        fun setList(context: Context, value : String){
            val pref = context.getSharedPreferences("USERDATA", Context.MODE_PRIVATE)
            pref.edit().apply { 
                putString("USERDATA", value)
                apply()
            }
        }
        
        fun getList(context: Context) : String?{
            val list = context.getSharedPreferences("USERDATA", Context.MODE_PRIVATE)
            return list?.getString("USERDATA", null)
        }

        fun clearToken(context: Context) {
            val pref = context.getSharedPreferences("USER", Context.MODE_PRIVATE)
            pref.edit().clear().apply()
        }

        fun isValidPassword(password: String) = password.length > 6
    }
}