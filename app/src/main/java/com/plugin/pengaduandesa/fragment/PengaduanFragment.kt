package com.plugin.pengaduandesa.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.plugin.pengaduandesa.CreatePengaduanActivity
import com.plugin.pengaduandesa.R
import com.plugin.pengaduandesa.adapter.ViewPagerAdapter
import com.plugin.pengaduandesa.databinding.FragmentPengaduanBinding

class PengaduanFragment : Fragment() {

    private var _binding : FragmentPengaduanBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPengaduanBinding.inflate(inflater, container, false)

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarPengaduan)
        binding.toolbarLayoutPengaduan.title = "Daftar Aduan"

        val view = binding.root
        setupViewPage()
        intent()
        return view
    }

    private fun setupViewPage(){
        binding.viewPager.adapter = ViewPagerAdapter(getChildFragmentManager())
        binding.TabLayout.setupWithViewPager(binding.viewPager)
    }

    private fun intent(){
        binding.fab.setOnClickListener {
            val intentCreate = Intent(activity, CreatePengaduanActivity::class.java)
            startActivity(intentCreate)
        }
    }
}