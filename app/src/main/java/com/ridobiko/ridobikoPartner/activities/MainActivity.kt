package com.ridobiko.ridobikoPartner.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ridobiko.ridobikoPartner.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
   private lateinit var binding :ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.todaysPickupsCard.setOnClickListener {
            startActivity(Intent(applicationContext, TodaysPickups::class.java))
        }
        binding.addBike.setOnClickListener {
            startActivity(Intent(applicationContext,AddBikeActivity::class.java))
        }
        binding.bookings.setOnClickListener {
            startActivity(Intent(applicationContext,MainBooking::class.java))
        }
        binding.myBikes.setOnClickListener {
            startActivity(Intent(applicationContext,MyBikeActivity::class.java))
        }
        binding.todaysDropsCard.setOnClickListener {
            startActivity(Intent(applicationContext,TodaysDrops::class.java))
        }
        binding.upcommingBookings.setOnClickListener {
            startActivity(Intent(applicationContext,UpcommingBookings::class.java))
        }
        binding.allBookings.setOnClickListener {
            startActivity(Intent(applicationContext,AllBookings::class.java))
        }
    }
}