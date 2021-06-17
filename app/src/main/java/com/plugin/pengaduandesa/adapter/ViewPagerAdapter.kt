package com.plugin.pengaduandesa.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.plugin.pengaduandesa.fragment.ApproveFragment
import com.plugin.pengaduandesa.fragment.DeclineFragment
import com.plugin.pengaduandesa.fragment.WaitingFragment

class ViewPagerAdapter(supportFragmentManager: FragmentManager) :
    FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                WaitingFragment()
            }
            1 -> {
                ApproveFragment()
            }
            else -> {
                DeclineFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Waiting"
            1 -> "Approve"
            else -> "Decline"

        }
    }
}