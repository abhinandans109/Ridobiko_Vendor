package com.ridobiko.ridobikoPartner.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.adapters.MainBooking_FragmentAdapter
import com.ridobiko.ridobikoPartner.adapters.SingleBike_FragmentAdapter
import com.ridobiko.ridobikoPartner.adapters.SingleCutomer_Fragment
import com.ridobiko.ridobikoPartner.databinding.ActivityCustomerDetailsBinding
import com.ridobiko.ridobikoPartner.databinding.ActivitySingleCustomerDetailsBinding

class SingleCustDetailsActivity : AppCompatActivity() {
    private  lateinit var binding:ActivitySingleCustomerDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivitySingleCustomerDetailsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.title="Customer Details"

        binding.tabs.addTab(binding.tabs!!.newTab().setText("Custom Profile"))
        binding.tabs.addTab(binding.tabs!!.newTab().setText("Booking History"))

        binding.tabs.tabGravity=TabLayout.GRAVITY_FILL
        val adapter= SingleCutomer_Fragment(this,supportFragmentManager,binding.tabs.tabCount)
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