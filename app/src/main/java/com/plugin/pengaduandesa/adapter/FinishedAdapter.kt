package com.plugin.pengaduandesa.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.plugin.pengaduandesa.DetailPengaduanActivity
import com.plugin.pengaduandesa.R
import com.plugin.pengaduandesa.models.Pengaduan
import kotlinx.android.synthetic.main.list_item_waiting.view.*

class FinishedAdapter(private var data: List<Pengaduan>, private var context: Context) :
    RecyclerView.Adapter<FinishedAdapter.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            LayoutInflater.from(context).inflate(R.layout.list_item_waiting, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) = holder.bind(data[position], context)

    override fun getItemCount() = data.size

    class MyHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pengaduan: Pengaduan, context: Context) {
            itemView.tvNamaAduan.text = pengaduan.complaint_content
            itemView.tvStatus.text = pengaduan.complaint_category
            itemView.tvLokasi.text = pengaduan.created_at
            itemView.ivImage.load(pengaduan.complaint_image)

            itemView.setOnClickListener {
                val intent = Intent(context, DetailPengaduanActivity::class.java).apply {
                    putExtra("COMPLAINT", pengaduan)
                }

                context.startActivity(intent)
            }
        }
    }
}