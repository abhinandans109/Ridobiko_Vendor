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

        binding.rentWeek.onFocusChangeListener =
            View.OnFocusChangeListener { v, hasFocus ->
                if(hasFocus) binding.tvRentWeek.setTextColor(Color.parseColor("#F44336"))
                else binding.tvRentWeek.setTextColor(Color.parseColor("#666666"))
            }
        binding.tvRentWeekend.onFocusChangeListener =
            View.OnFocusChangeListener { v, hasFocus ->
                if(hasFocus) binding.tvRentWeekend.setTextColor(Color.parseColor("#F44336"))
                else binding.tvRentWeekend.setTextColor(Color.parseColor("#666666"))
            }
        binding.hour.onFocusChangeListener =
            View.OnFocusChangeListener { v, hasFocus ->
                if(hasFocus) binding.tvRentHour.setTextColor(Color.parseColor("#F44336"))
                else binding.tvRentHour.setTextColor(Color.parseColor("#666666"))
            }
        binding.speedLimit.onFocusChangeListener =
            View.OnFocusChangeListener { v, hasFocus ->
                if(hasFocus) binding.tvSpeedLimit.setTextColor(Color.parseColor("#F44336"))
                else binding.tvSpeedLimit.setTextColor(Color.parseColor("#666666"))
            }
        binding.kmLimit.onFocusChangeListener =
            View.OnFocusChangeListener { v, hasFocus ->
                if(hasFocus) binding.tvKmDay.setTextColor(Color.parseColor("#F44336"))
                else binding.tvKmDay.setTextColor(Color.parseColor("#666666"))
            }
        binding.kmLimitMonth.onFocusChangeListener =
            View.OnFocusChangeListener { v, hasFocus ->
                if(hasFocus) binding.tvKmMonth.setTextColor(Color.parseColor("#F44336"))
                else binding.tvKmMonth.setTextColor(Color.parseColor("#666666"))
            }
        binding.kmCharge.onFocusChangeListener =
            View.OnFocusChangeListener { v, hasFocus ->
                if(hasFocus) binding.tvAdditional.setTextColor(Color.parseColor("#F44336"))
                else binding.tvAdditional.setTextColor(Color.parseColor("#666666"))
            }
        binding.Deposit.onFocusChangeListener =
            View.OnFocusChangeListener { v, hasFocus ->
                if(hasFocus) binding.tvDeposit.setTextColor(Color.parseColor("#F44336"))
                else binding.tvDeposit.setTextColor(Color.parseColor("#666666"))
            }




        // for spinner
        binding.brand.isFocusableInTouchMode = true;
        binding.brand.onFocusChangeListener=View.OnFocusChangeListener{ v, hasfocus ->
            if(hasfocus) binding.tvSelectBrand.setTextColor(Color.parseColor("#F44336"))
            else binding.tvSelectBrand.setTextColor(Color.parseColor("#666666"))
        }

        binding.bike.isFocusableInTouchMode = true;
        binding.bike.onFocusChangeListener=View.OnFocusChangeListener{ v, hasfocus ->
            if(hasfocus) binding.tvselectBike.setTextColor(Color.parseColor("#F44336"))
            else binding.tvselectBike.setTextColor(Color.parseColor("#666666"))
        }
        binding.plateType.isFocusableInTouchMode = true;
        binding.plateType.onFocusChangeListener=View.OnFocusChangeListener{ v, hasfocus ->
            if(hasfocus) binding.tvPlatetype.setTextColor(Color.parseColor("#F44336"))
            else binding.tvPlatetype.setTextColor(Color.parseColor("#666666"))
        }
        binding.modelYear.isFocusableInTouchMode = true;
        binding.modelYear.onFocusChangeListener=View.OnFocusChangeListener{ v, hasfocus ->
            if(hasfocus) binding.tvModelYear.setTextColor(Color.parseColor("#F44336"))
            else binding.tvModelYear.setTextColor(Color.parseColor("#666666"))
        }



    }
}