package com.plugin.pengaduandesa.contracts

import com.plugin.pengaduandesa.models.User

interface LoginActivityContract {

    /* Main funtion untuk terhubung ke View */
    interface View {
        fun toast(message: String)
        fun success(token: String, list: User?)
        fun isLoading(state: Boolean)
        fun idError(err: String?)
        fun passwordError(err: String?)
        fun notConect()
    }

    /* Main function untuk Logic (Connection to API , etc) dan nantinya akan
   * di teruskan di Presenter sebagai Base Logic funtion di setiap Activity Viewnya */
    interface Interaction {
        fun validate(id: String, password: String): Boolean
        fun login(email: String, password: String)
        fun register(nik: String, email: String, password: String, confirm_password: String)
        fun saveDeviceToken(token : String, device_token : String)
        fun destroy()
    }
}