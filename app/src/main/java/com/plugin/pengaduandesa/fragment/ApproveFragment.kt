package com.plugin.pengaduandesa.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.plugin.pengaduandesa.R
import com.plugin.pengaduandesa.adapter.ApprovedAdapter
import com.plugin.pengaduandesa.adapter.WaitingAdapter
import com.plugin.pengaduandesa.contracts.PengaduanActivityContract
import com.plugin.pengaduandesa.models.Pengaduan
import com.plugin.pengaduandesa.presenters.PengaduanActivityPresenter
import com.plugin.pengaduandesa.utils.PengaduanUtils
import kotlinx.android.synthetic.main.fragment_approve.view.*
import kotlinx.android.synthetic.main.fragment_waiting.view.*


class ApproveFragment : Fragment(), PengaduanActivityContract.View {

    var presenter = PengaduanActivityPresenter(this)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_approve, container, false)
    }

    private fun getData() {
        PengaduanUtils.getToken(requireActivity())?.let {
            presenter.allDataApproved(it)
        }
    }

    override fun attachToRecycler(aduan: List<Pengaduan>) {
        view?.rvApprove?.apply {
            val mlayoutManager = LinearLayoutManager(activity)
            layoutManager = mlayoutManager
            adapter = ApprovedAdapter(aduan, requireActivity())
        }
    }

    override fun isLoading(state: Boolean) {
        view?.loadingApprove?.visibility = if(state) View.VISIBLE else View.INVISIBLE
    }

    override fun toast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    override fun emptyData() {
        view?.emptyDataApproved?.visibility = View.VISIBLE
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