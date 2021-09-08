package com.plugin.pengaduandesa

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.github.florent37.runtimepermission.kotlin.askPermission
import com.plugin.pengaduandesa.adapter.ViewPagerAdapter
import com.plugin.pengaduandesa.databinding.ActivityMainBinding
import com.plugin.pengaduandesa.fragment.HomeFragment
import com.plugin.pengaduandesa.fragment.PengaduanFragment
import com.plugin.pengaduandesa.fragment.ProfileFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setupViewPager()
        bottomNavigation()
        askPermission()
    }

    private fun askPermission() {
        askPermission(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION) {
        }.onDeclined { e ->
            if(e.hasDenied()){
                e.denied.forEach(){

                }

                AlertDialog.Builder(this)
                    .setMessage("Please Accept Our Permission")
                    .setPositiveButton("Yes"){_ , _ ->
                        e.askAgain()
                    }
                    .setNegativeButton("NO"){dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }

            if(e.hasForeverDenied()){
                e.foreverDenied.forEach(){}
                    e.goToSettings()
            }
        }
    }

    private fun bottomNavigation(){
        setFragment(HomeFragment())
        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_home -> {
                    setFragment(HomeFragment())
                }
                R.id.menu_informasi -> {
                    setFragment(PengaduanFragment())
                }
                R.id.menu_profil -> {
                    setFragment(ProfileFragment())
                }
            }
            true
        }
    }

    private fun setFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayoutMain, fragment)
            addToBackStack(null)
            commit()
        }
    }
}