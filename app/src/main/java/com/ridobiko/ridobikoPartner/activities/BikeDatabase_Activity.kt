package com.ridobiko.ridobikoPartner.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.databinding.ActivityBikeDatabaseBinding

class BikeDatabase_Activity : AppCompatActivity() {
    private  lateinit var binding:ActivityBikeDatabaseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityBikeDatabaseBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.title="Bike Database"
    }
}