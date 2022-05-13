package com.ridobiko.ridobikoPartner.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import com.ridobiko.ridobikoPartner.adapters.MainBooking_FragmentAdapter

import com.ridobiko.ridobikoPartner.databinding.ActivityMainBookingBinding

class MainBooking : AppCompatActivity() {
    private lateinit var binding: ActivityMainBookingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBookingBinding.inflate(layoutInflater)
        setContentView(binding.root)
supportActionBar?.title="Bookings"

binding.tabs.addTab(binding.tabs!!.newTab().setText("Today's Pickup"))
binding.tabs.addTab(binding.tabs!!.newTab().setText("Today's Drop"))
binding.tabs.addTab(binding.tabs!!.newTab().setText("Upcoming Booking"))
binding.tabs.addTab(binding.tabs!!.newTab().setText("All") )

binding.tabs.tabGravity=TabLayout.GRAVITY_FILL
        val adapter=MainBooking_FragmentAdapter(this,supportFragmentManager,binding.tabs.tabCount)
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

//       binding.viewPager.adapter=MainBookingAdapter(this)
//        TabLayoutMediator(binding.tabs,binding.viewPager){tab,index->
//            tab.text=when(index){
//                0->{"today pickup"}
//                0->{"today drop"}
//                0->{"upcoming"}
//                0->{"All"}
//                else-> throw  Resources.NotFoundException("Position Not Found")
//
//            }
//        }.attach()



    }
}