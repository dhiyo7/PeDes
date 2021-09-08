package com.plugin.pengaduandesa

import android.os.Bundle
import android.view.View
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.plugin.pengaduandesa.adapter.ViewPagerAdapter
import com.plugin.pengaduandesa.databinding.ActivityHomeBinding
import kotlinx.android.synthetic.main.activity_home.view.*
import kotlinx.android.synthetic.main.content_scrolling.view.*

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val includeView: View = binding.root.contentLayout
        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = title
        val loginViewPager = ViewPagerAdapter(supportFragmentManager)
        includeView.ViewPager.adapter = loginViewPager
        includeView.TabLayout.setupWithViewPager(includeView.ViewPager)
    }
}