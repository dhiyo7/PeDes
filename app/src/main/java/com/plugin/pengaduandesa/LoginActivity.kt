package com.plugin.pengaduandesa

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.plugin.pengaduandesa.contracts.LoginActivityContract
import com.plugin.pengaduandesa.presenters.LoginActivityPresenter
import com.plugin.pengaduandesa.utils.PengaduanUtils
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.view.*

class LoginActivity : AppCompatActivity(), LoginActivityContract.View {
    private var presenter = LoginActivityPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        isLoggedIn()
        presenter = LoginActivityPresenter(this)
        doLogin()
        moveRegister()
    }

    private fun moveRegister() {
        etRegister.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    RegisterActivity::class.java
                )
            ).also { finish() }
        }
    }

    private fun doLogin() {
        btnLogin.setOnClickListener {
            val email = etId.text.toString().trim()
            val pass = etPass.text.toString().trim()
            if (email.isNotEmpty() && pass.isNotEmpty()) {
                if (pass.length > 5) {
                    presenter.login(email, pass)
                } else {
                    toast("Cek password anda")
                }
            } else {
                toast("Isi semua form")
            }
        }
    }

    override fun toast(message: String) =
        Toast.makeText(this@LoginActivity, message, Toast.LENGTH_LONG).show()

    override fun success(token: String) {
        PengaduanUtils.setToken(this, "Bearer ${token}")
        println(token)
        startActivity(Intent(this, MainActivity::class.java)).also { finish() }
    }

    override fun isLoading(state: Boolean) {
        progress.visibility= View.VISIBLE
        btnLogin.isEnabled = !state
    }

    override fun idError(err: String?) {
        inId.error = err
    }

    override fun passwordError(err: String?) {
        inPass.error = err
    }

    override fun notConect() {
        btnLogin.isEnabled = true
        progress.isEnabled = false
    }

    private fun isLoggedIn() {
        val token = PengaduanUtils.getToken(this)
        if (token != null) {
            startActivity(Intent(this, MainActivity::class.java)).also { finish() }
        }
    }
}