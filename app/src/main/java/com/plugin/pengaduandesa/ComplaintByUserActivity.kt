package com.plugin.pengaduandesa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.plugin.pengaduandesa.adapter.ComplaintByUserAdapter
import com.plugin.pengaduandesa.contracts.PengaduanActivityContract
import com.plugin.pengaduandesa.databinding.ActivityComplaintByUserBinding
import com.plugin.pengaduandesa.models.Pengaduan
import com.plugin.pengaduandesa.presenters.PengaduanActivityPresenter
import com.plugin.pengaduandesa.utils.PengaduanUtils

class ComplaintByUserActivity : AppCompatActivity(), PengaduanActivityContract.View {
    private lateinit var binding : ActivityComplaintByUserBinding
    private var presenter = PengaduanActivityPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = PengaduanActivityPresenter(this)
        binding = ActivityComplaintByUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        toolbarAction()
    }

    private fun toolbarAction(){
        val action = supportActionBar
        action!!.title = "List of your complaints"
        action!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onSupportNavigateUp()
        onBackPressed()
        return true
    }

    override fun attachToRecycler(aduan: List<Pengaduan>) {
        binding.rvByUser.apply {
            adapter = ComplaintByUserAdapter(aduan, this@ComplaintByUserActivity)
            layoutManager = LinearLayoutManager(this@ComplaintByUserActivity)
        }
    }

    override fun isLoading(state: Boolean) {
        binding.loadingByUser.visibility = if(state) View.VISIBLE else View.INVISIBLE
    }

    override fun toast(message: String) {
        Toast.makeText(this@ComplaintByUserActivity, message, Toast.LENGTH_LONG).show()
    }

    override fun emptyData() {
        binding.emptyByUser.visibility = View.VISIBLE
    }

    private fun getData(){
        val token = PengaduanUtils.getToken(this@ComplaintByUserActivity)
        presenter?.complaintByUser(token!!)
    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.destroy()
    }
}