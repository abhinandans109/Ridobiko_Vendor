package com.ridobiko.ridobikoPartner.activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.api.API
import com.ridobiko.ridobikoPartner.constants.Constants
import com.ridobiko.ridobikoPartner.databinding.ActivityAddBikeBinding
import com.ridobiko.ridobikoPartner.models.ChangeStatusResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
       binding.weekend.onFocusChangeListener =
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
        binding.addBikeButton.setOnClickListener{
            API.get().addBike(binding.brand.selectedItem.toString(),binding.bike.selectedItem.toString(),binding.plateType.selectedItem.toString(),binding.modelYear.selectedItem.toString(),binding.plateNumber.text.toString(),binding.rentWeek.text.toString(),binding.weekend.text.toString(),binding.hour.text.toString(),binding.speedLimit.text.toString(),binding.kmLimit.text.toString(),binding.kmLimitMonth.text.toString(),binding.kmCharge.text
                .toString(),binding.Deposit.text.toString(),getSharedPreferences(Constants.PREFS_LOGIN_DETAILS,
                MODE_PRIVATE).getString(Constants.EMAIL,"null")).enqueue(object:Callback<ChangeStatusResponseModel>{
                override fun onResponse(
                    call: Call<ChangeStatusResponseModel>,
                    response: Response<ChangeStatusResponseModel>
                ) {
                    if(response.isSuccessful){
                        if(response.body()?.success==Constants.SUCCESS){
                            Toast.makeText(applicationContext,"Bike added successfully.",Toast.LENGTH_SHORT).show()
                            startActivity(Intent(applicationContext,MyBikeActivity::class.java))
                            finish()
                        }
                    }
                }

                override fun onFailure(call: Call<ChangeStatusResponseModel>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }




    }
}