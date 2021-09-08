package com.plugin.pengaduandesa.presenters

import android.util.Log
import android.widget.Toast
import com.plugin.pengaduandesa.contracts.LoginActivityContract
import com.plugin.pengaduandesa.models.User
import com.plugin.pengaduandesa.utils.PengaduanUtils
import com.plugin.pengaduandesa.webservices.PengaduanAPI
import com.plugin.pengaduandesa.webservices.WrappedResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.security.AccessController.getContext


class LoginActivityPresenter(v: LoginActivityContract.View?) : LoginActivityContract.Interaction {
    private var view: LoginActivityContract.View? = v
    private var api = PengaduanAPI.instance()

    override fun validate(id: String, password: String): Boolean {
        view?.passwordError(null)
        if (!PengaduanUtils.isValidPassword(password)) {
            view?.passwordError("Password tidak valid")
            return false
        }
        return true
    }

    override fun login(email: String, password: String) {
        view?.isLoading(true)
        api.login(email, password).enqueue(object : Callback<WrappedResponse<User>> {
            override fun onResponse(
                call: Call<WrappedResponse<User>>,
                response: Response<WrappedResponse<User>>
            ) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null && body.status == 200) {
                        view?.toast("Selamat datang ${body.data!!.name}")
                        view?.success(body.data?.token!!, body.data)
                    } else {
                        view?.toast("Login gagal, cek email dan password")
                    }
                } else {
                    var jsonError = JSONObject(response.errorBody()?.string())
                    view?.toast(jsonError.getString("error"))
                }
                view?.isLoading(false)
            }

            override fun onFailure(call: Call<WrappedResponse<User>>, t: Throwable) {
                view?.toast("Koneksi ke server tidak bisa")
                println("error " + t.message.toString())
                view?.notConect()
            }

        })
    }

    override fun saveDeviceToken(token: String, device_token: String) {
        val request = api.saveDeviceToken(token , device_token)
        request.enqueue(object : Callback<WrappedResponse<String>>{
            override fun onResponse(
                call: Call<WrappedResponse<String>>,
                response: Response<WrappedResponse<String>>
            ) {
                if(response.isSuccessful){
                    val body = response.body()
                    if(body != null){
                        body.message?.let { view?.toast(it) }
                    }else{
                        view?.toast(body?.message!!)

                    }
                }else{
                    view?.toast(response.message())
                    println("BODY "+ response.message())
                    println("RESPONSE "+ response)
                }
            }

            override fun onFailure(call: Call<WrappedResponse<String>>, t: Throwable) {
                view?.toast("Tidak bisa koneksi ke server")
                println(t.message)
            }

        })
    }


    override fun register(nik: String, email: String, password: String, confirm_password: String) {
        view?.isLoading(true)
        api.register(nik, email, password, confirm_password).enqueue(object : Callback<WrappedResponse<User>>{
            override fun onResponse(
                call: Call<WrappedResponse<User>>,
                response: Response<WrappedResponse<User>>
            ) {
                if (response.isSuccessful){
                    val body = response.body()
                    if (body != null && body.status == 200) {
                        view?.toast("Selamat Datang ${body.data!!.name}")
                        view?.success(body.data?.token!!, body.data)
                    } else {
                        view?.toast("Register Gagal, cek semua form")
                    }
                } else {
                    var jsonError = JSONObject(response.errorBody()?.string())
                    view?.toast(jsonError.getString("error"))
                }
                view?.isLoading(false)
            }

            override fun onFailure(call: Call<WrappedResponse<User>>, t: Throwable) {
                view?.toast("Koneksi ke server tidak bisa")
                println("error " + t.message)
                view?.notConect()
            }

        })
    }

    override fun destroy() {
        view = null
    }
}