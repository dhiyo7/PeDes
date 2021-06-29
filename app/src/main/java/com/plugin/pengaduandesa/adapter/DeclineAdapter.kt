package com.plugin.pengaduandesa.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.plugin.pengaduandesa.R
import com.plugin.pengaduandesa.models.Pengaduan
import kotlinx.android.synthetic.main.list_item_waiting.view.*

class DeclineAdapter(private var data: List<Pengaduan>, private var context: Context) :
    RecyclerView.Adapter<DeclineAdapter.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return DeclineAdapter.MyHolder(
            LayoutInflater.from(context).inflate(R.layout.list_item_waiting, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) =
        holder.bind(data[position], context)

    override fun getItemCount() = data.size

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pengaduan: Pengaduan, context: Context) {
            itemView.tvNamaAduan.text = pengaduan.complaint_content
            itemView.tvLokasi.text = pengaduan.complaint_category
            itemView.tvStatus.text = pengaduan.status
        }
    }
}
