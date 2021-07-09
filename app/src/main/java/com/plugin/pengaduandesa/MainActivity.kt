package com.plugin.pengaduandesa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
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
    }

//    private fun setupViewPager() {
//        val loginViewPager = ViewPagerAdapter(supportFragmentManager)
//        binding.ViewPager.adapter = loginViewPager
//        binding.TabLayout.setupWithViewPager(binding.ViewPager)
//    }

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