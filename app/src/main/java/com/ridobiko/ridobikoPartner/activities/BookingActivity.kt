package com.ridobiko.ridobikoPartner.activities

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import com.ridobiko.ridobikoPartner.AppVendor
import com.ridobiko.ridobikoPartner.activities.ui.main.SectionsPagerAdapter
import com.ridobiko.ridobikoPartner.databinding.ActivityBookingBinding
import com.ridobiko.ridobikoPartner.models.BookingResponseModel

class BookingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookingBinding
    private lateinit var booking :BookingResponseModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBookingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        booking=AppVendor.selectedBooking
       supportActionBar?.title=booking.trans_id
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)

    }

}