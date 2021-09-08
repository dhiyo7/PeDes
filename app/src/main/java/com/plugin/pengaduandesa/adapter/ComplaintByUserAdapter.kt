package com.plugin.pengaduandesa.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.plugin.pengaduandesa.DetailPengaduanActivity
import com.plugin.pengaduandesa.R
import com.plugin.pengaduandesa.models.Pengaduan
import kotlinx.android.synthetic.main.item_complaint_by_user.view.*

class ComplaintByUserAdapter(private var aduan : List<Pengaduan>, private var context : Context) : RecyclerView.Adapter<ComplaintByUserAdapter.MyHolder>() {
    inner class MyHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(aduan : Pengaduan, context: Context){
            itemView.tvComplaintContent.text = aduan.complaint_content
            itemView.tvTanggal.text = aduan.created_at
            itemView.setOnClickListener {
                val intent = Intent(context, DetailPengaduanActivity::class.java).apply {
                    putExtra("COMPLAINT", aduan)
                }

                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(LayoutInflater.from(context).inflate(R.layout.item_complaint_by_user, parent, false))
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(aduan[position], context)
    }

    override fun getItemCount() = aduan.size
}