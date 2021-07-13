package com.plugin.pengaduandesa.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.plugin.pengaduandesa.LoginActivity
import com.plugin.pengaduandesa.R
import com.plugin.pengaduandesa.databinding.FragmentProfileBinding
import com.plugin.pengaduandesa.models.User
import com.plugin.pengaduandesa.utils.PengaduanUtils
import java.lang.reflect.Type

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
        return binding.root
    }

    private fun getUserData(){
        var listUser : User;
        val list = PengaduanUtils.getList(requireActivity())
        var gson = Gson()
//        var type : Type = TypeToken<User>().type
        listUser = gson.fromJson(list, User::class.java)

        binding.tvName.text = listUser.name
        binding.tvEmail.text = listUser.email

    }

    private fun funLogout(){
        binding.btnLogout.setOnClickListener {
            PengaduanUtils.clearToken(requireActivity())
            startActivity(Intent(activity, LoginActivity::class.java).also{ activity?.finish() })
        }
    }
}