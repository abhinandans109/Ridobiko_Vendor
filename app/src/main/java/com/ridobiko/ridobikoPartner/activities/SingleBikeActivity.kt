package com.ridobiko.ridobikoPartner.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.adapters.MainBooking_FragmentAdapter
import com.ridobiko.ridobikoPartner.adapters.SingleBike_FragmentAdapter
import com.ridobiko.ridobikoPartner.databinding.ActivityMainBookingBinding
import com.ridobiko.ridobikoPartner.databinding.ActivitySingleBikeBinding

class SingleBikeActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySingleBikeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySingleBikeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title="Bike Details"

        binding.tabs.addTab(binding.tabs.newTab().setText("Dashboard"))
        binding.tabs.addTab(binding.tabs.newTab().setText("Documents"))
        binding.tabs.addTab(binding.tabs.newTab().setText("Service History"))
        binding.tabs.addTab(binding.tabs.newTab().setText("Trip History") )
        binding.tabs.addTab(binding.tabs.newTab().setText("Setting") )

        binding.tabs.tabGravity= TabLayout.GRAVITY_FILL
        val adapter= SingleBike_FragmentAdapter(this,supportFragmentManager,binding.tabs.tabCount)
        binding.viewPager.adapter=adapter

        binding.viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabs))

        binding.tabs!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.viewPager!!.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })




    }
}