package com.plugin.pengaduandesa.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.gson.Gson
import com.plugin.pengaduandesa.ComplaintByUserActivity
import com.plugin.pengaduandesa.LoginActivity
import com.plugin.pengaduandesa.databinding.FragmentProfileBinding
import com.plugin.pengaduandesa.models.User
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
        getUserData()
        getComplaintByUser()

        binding.fab.setOnClickListener {
            openWa()
        }

        return binding.root
    }

    private fun getUserData(){
        var listUser : User;
        val list = PengaduanUtils.getList(requireActivity())
        var gson = Gson()
        listUser = gson.fromJson(list, User::class.java)

        binding.tvName.text = listUser.name
        binding.tvNIK.text = listUser.nik
        binding.tvEmail.text = listUser.email

    }

    private fun funLogout(){
        binding.btnLogout.setOnClickListener {
            PengaduanUtils.clearToken(requireActivity())
            startActivity(Intent(activity, LoginActivity::class.java).also{ activity?.finishAffinity() })
        }
    }

    private fun getComplaintByUser(){
        binding.complaintList.setOnClickListener {
            val intent = Intent(requireActivity(), ComplaintByUserActivity::class.java)
            startActivity(intent)
        }
    }

    private fun openWa(){
        var number : String = "6285602518653"
        var url : String = "https://api.whatsapp.com/send?phone="+number;

        val intent = Intent(Intent.ACTION_VIEW);
        intent.setPackage("com.whatsapp")
        intent.setData(Uri.parse(url))
        startActivity(intent)
    }
}