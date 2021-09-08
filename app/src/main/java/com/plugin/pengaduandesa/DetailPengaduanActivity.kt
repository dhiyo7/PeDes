package com.plugin.pengaduandesa

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import coil.api.load
import com.google.android.gms.maps.model.LatLng
import com.plugin.pengaduandesa.databinding.ActivityDetailPengaduanBinding
import com.plugin.pengaduandesa.models.Pengaduan

class DetailPengaduanActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailPengaduanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPengaduanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        parseToView()
        toolbarAction()
        clickCheckLocation()
    }

    private fun toolbarAction(){
        val action = supportActionBar
        action!!.title = "Complaint Detail"
        action!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onSupportNavigateUp()
        onBackPressed()
        return true
    }

    private fun getDetail() : Pengaduan? = intent.getParcelableExtra("COMPLAINT")

    private fun parseToView(){
        println("PENGADUAN DETAIL " + getDetail())
        binding.tvPelapor.text = getDetail()?.user
        binding.tvStatus.text = getDetail()?.status
        binding.tvTanggal.text = getDetail()?.created_at
        binding.tvCategory.text = getDetail()?.complaint_category
        binding.tvComplaintContent.text = getDetail()?.complaint_content
        binding.ivComplaintImage.load(getDetail()?.complaint_image)
    }

    private fun clickCheckLocation(){
        binding.btnCheckLocation.setOnClickListener {
            val pcc = LatLng(getDetail()?.latitude?.toDouble()!!, getDetail()?.longitude?.toDouble()!!)
            val map = "http://maps.google.com/maps?q=loc:" + getDetail()?.latitude
                .toString() + "," + getDetail()?.longitude.toString() + " (" + getDetail()?.user
                .toString() + ")"

            val gmmIntentUri: Uri = Uri.parse(map) as Uri
            System.err.println(pcc);
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }
    }
}