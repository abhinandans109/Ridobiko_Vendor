package com.ridobiko.ridobikoPartner.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.databinding.ActivityAddBikeBinding

class AddBikeActivity : AppCompatActivity() {
    lateinit var binding:ActivityAddBikeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddBikeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title="Add Bike"
        //for edittext
        binding.plateNumber.onFocusChangeListener =
            View.OnFocusChangeListener { v, hasFocus ->
                if(hasFocus) binding.tvBikePlate.setTextColor(Color.parseColor("#F44336"))
                else binding.tvBikePlate.setTextColor(Color.parseColor("#666666"))
            }
        binding.brand.adapter=ArrayAdapter(applicationContext,R.layout.spinner_item,
            mutableListOf("TVS","Honda","Hero","KTM","BMW"))

        // for spinner
        binding.brand.isFocusableInTouchMode = true;
        binding.brand.onFocusChangeListener=View.OnFocusChangeListener{ v, hasfocus ->
            if(hasfocus) binding.tvSelectBrand.setTextColor(Color.parseColor("#F44336"))
            else binding.tvSelectBrand.setTextColor(Color.parseColor("#666666"))
        }

    }
}