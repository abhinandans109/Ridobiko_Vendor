package com.ridobiko.ridobikoPartner.activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.api.API
import com.ridobiko.ridobikoPartner.constants.Constants
import com.ridobiko.ridobikoPartner.databinding.ActivityAddBikeBinding
import com.ridobiko.ridobikoPartner.models.ApiResponseModel
import com.ridobiko.ridobikoPartner.models.BikeBrandNameResponseModel
import com.ridobiko.ridobikoPartner.models.ChangeStatusResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddBikeActivity : AppCompatActivity() {
    lateinit var binding:ActivityAddBikeBinding
    lateinit var bikeBranName:ArrayList<BikeBrandNameResponseModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddBikeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title="Add Bike"
        //for edittext

        API.get().getAddBikes(getSharedPreferences(Constants.PREFS_LOGIN_DETAILS, MODE_PRIVATE)
            .getString(Constants.EMAIL,"null")).enqueue(object:Callback<ApiResponseModel<ArrayList<BikeBrandNameResponseModel>>>{
            override fun onResponse(
                call: Call<ApiResponseModel<ArrayList<BikeBrandNameResponseModel>>>,
                response: Response<ApiResponseModel<ArrayList<BikeBrandNameResponseModel>>>
            ){
                if(response.isSuccessful){
                    if(response.body()?.success==Constants.SUCCESS){
                        bikeBranName= response.body()!!.data
                        var brands= mutableListOf<String>()
                        for (i in bikeBranName){
                            brands.add(i.bike_brand)
                        }
                        binding.brand.adapter=ArrayAdapter(applicationContext,R.layout.spinner_item,brands)
                    }
                }
            }

            override fun onFailure(
                call: Call<ApiResponseModel<ArrayList<BikeBrandNameResponseModel>>>,
                t: Throwable
            ) {

//                TODO("Not yet implemented")
            }

        })

        binding.plateType.adapter=ArrayAdapter(applicationContext,R.layout.spinner_item,
            mutableListOf("White Plate","Yellow Plate",))
        binding.modelYear.adapter=ArrayAdapter(applicationContext,R.layout.spinner_item,
            mutableListOf("2022","2021","2020","2019","2018","2017","2016","2015","2014","2013","2012"))
        binding.brand.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                var bikeNames= mutableListOf<String>()
                for(i in bikeBranName[position].bikes){
                    bikeNames.add(i.bike_name)
                }
                binding.bike.adapter=ArrayAdapter(applicationContext,R.layout.spinner_item,bikeNames)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
//                TODO("Not yet implemented")
            }

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
//                    TODO("Not yet implemented")
                }

            })
        }




    }
}