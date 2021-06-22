package com.plugin.pengaduandesa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.plugin.pengaduandesa.adapter.ViewPagerAdapter
import com.plugin.pengaduandesa.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewPager()
    }

    private fun setupViewPager() {
        val loginViewPager = ViewPagerAdapter(supportFragmentManager)
        binding.ViewPager.adapter = loginViewPager
        binding.TabLayout.setupWithViewPager(binding.ViewPager)
    }
}