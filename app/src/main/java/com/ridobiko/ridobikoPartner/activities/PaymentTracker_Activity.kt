package com.ridobiko.ridobikoPartner.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.databinding.ActivityPaymentTrackerBinding

class PaymentTracker_Activity : AppCompatActivity() {
    private  lateinit var binding:ActivityPaymentTrackerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityPaymentTrackerBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.title="Payment Tracker"
    }
}