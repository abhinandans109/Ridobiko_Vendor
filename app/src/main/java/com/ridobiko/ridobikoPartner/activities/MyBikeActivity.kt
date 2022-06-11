package com.ridobiko.ridobikoPartner.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ridobiko.ridobikoPartner.AppVendor
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.adapters.MyBikeAdapter
import com.ridobiko.ridobikoPartner.api.API
import com.ridobiko.ridobikoPartner.constants.Constants
import com.ridobiko.ridobikoPartner.databinding.ActivityMyBikeBinding
import com.ridobiko.ridobikoPartner.models.ApiResponseModel
import com.ridobiko.ridobikoPartner.models.MyBikesResponseModel
import com.ridobiko.ridobikoPartner.models.MybikeModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyBikeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMyBikeBinding
//    private  lateinit var recyclerView: RecyclerView
    private  lateinit var bikeList: ArrayList<MyBikesResponseModel>
    private  lateinit var myBikeAdapter: MyBikeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityMyBikeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.title="My Bike"
        bikeList= ArrayList()

        val sharedPreferences=getSharedPreferences(Constants.PREFS_LOGIN_DETAILS, MODE_PRIVATE)
        val email=sharedPreferences.getString(Constants.EMAIL,"none");
        API.get().getMyBikes(email).enqueue(object : Callback<ApiResponseModel<ArrayList<MyBikesResponseModel>>>{
            override fun onResponse(
                call: Call<ApiResponseModel<ArrayList<MyBikesResponseModel>>>,
                response: Response<ApiResponseModel<ArrayList<MyBikesResponseModel>>>
            ) {
                if(response.isSuccessful){
                    if(response.body()?.success==Constants.SUCCESS){
                        bikeList=response.body()?.data!!
                        binding.rvMyBike.layoutManager=LinearLayoutManager(applicationContext)
                        myBikeAdapter= MyBikeAdapter(applicationContext,bikeList)
                        binding.rvMyBike.adapter=myBikeAdapter
                        myBikeAdapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(
                call: Call<ApiResponseModel<ArrayList<MyBikesResponseModel>>>,
                t: Throwable
            ) {
                TODO("Not yet implemented")
            }

        });

    }

}