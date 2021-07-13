package com.plugin.pengaduandesa.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.plugin.pengaduandesa.LoginActivity
import com.plugin.pengaduandesa.R
import com.plugin.pengaduandesa.databinding.FragmentProfileBinding
import com.plugin.pengaduandesa.utils.PengaduanUtils

class ProfileFragment : Fragment() {
    private var _binding : FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater,container, false)
        // Inflate the layout for this fragment
        funLogout()
        return binding.root
    }

    private fun funLogout(){
        binding.btnLogout.setOnClickListener {
            PengaduanUtils.clearToken(requireActivity())
            startActivity(Intent(activity, LoginActivity::class.java).also{ activity?.finish() })
        }
    }
}