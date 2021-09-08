package com.plugin.pengaduandesa

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.plugin.pengaduandesa.contracts.LoginActivityContract
import com.plugin.pengaduandesa.models.User
import com.plugin.pengaduandesa.presenters.LoginActivityPresenter
import com.plugin.pengaduandesa.utils.PengaduanUtils
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.etPass

class RegisterActivity : AppCompatActivity(), LoginActivityContract.View {
    private var presenter = LoginActivityPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        isLoggedIn()
        doRegister()

        textLogin.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java)).also {
                finish()
            }
        }
    }

    private fun doRegister() {
        btnRegister.setOnClickListener {
            val nik = etNIK.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val pass = etPass.text.toString().trim()
            val repass = etRePass.text.toString().trim()
            if (nik.isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty() && repass.isNotEmpty()) {
                if (pass.length > 5 && repass.length > 5 && pass == repass) {
                    presenter.register(nik, email, pass, repass)
                } else {
                    toast("cek password anda")
                }
            } else {
                toast("Isi semua form")
            }
        }
    }

    override fun toast(message: String) {
        Toast.makeText(this@RegisterActivity, message, Toast.LENGTH_LONG).show()
    }

    override fun success(token: String, list: User?) {
        startActivity(Intent(this, LoginActivity::class.java)).also { finish() }
    }

    override fun isLoading(state: Boolean) {
        progresss.visibility = if(state) View.VISIBLE else View.INVISIBLE
        btnRegister.isEnabled = !state
    }

    override fun idError(err: String?) {
        inNIK.error = err
        inEmail.error = err
        progresss.visibility = View.INVISIBLE
    }

    override fun passwordError(err: String?) {
        inPasss.error = err
        inRePass.error = err
        progresss.visibility = View.INVISIBLE
    }

    override fun notConect() {
        btnRegister.isEnabled = true
        progresss.visibility = View.INVISIBLE
    }

    private fun isLoggedIn() {
        val token = PengaduanUtils.getToken(this)
        if (token != null) {
            startActivity(Intent(this, MainActivity::class.java)).also { finish() }
        }
    }
}
