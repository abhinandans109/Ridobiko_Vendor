package com.ridobiko.ridobikoPartner.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.ridobiko.ridobikoPartner.adapters.BookingsAdapter
import com.ridobiko.ridobikoPartner.api.API
import com.ridobiko.ridobikoPartner.constants.Constants
import com.ridobiko.ridobikoPartner.databinding.ActivityTodaysPickupsBinding
import com.ridobiko.ridobikoPartner.models.ApiResponseModel
import com.ridobiko.ridobikoPartner.models.BookingResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodaysDrops : AppCompatActivity() {
    private lateinit var binding : ActivityTodaysPickupsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityTodaysPickupsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title="Today's Drops"


        API.get()
            .getTodaysDrops(getSharedPreferences(Constants.PREFS_LOGIN_DETAILS, MODE_PRIVATE).getString(Constants.EMAIL,"")).enqueue(object:Callback<ApiResponseModel<ArrayList<BookingResponseModel>>>{
                override fun onResponse(
                    call: Call<ApiResponseModel<ArrayList<BookingResponseModel>>>,
                    response: Response<ApiResponseModel<ArrayList<BookingResponseModel>>>
                ) {
                    binding.rvTodaysPickups.layoutManager=LinearLayoutManager(applicationContext)
                    binding.rvTodaysPickups.adapter=BookingsAdapter(applicationContext,response.body()!!.data)
                    binding.pb.visibility= View.GONE
                }

                override fun onFailure(
                    call: Call<ApiResponseModel<ArrayList<BookingResponseModel>>>,
                    t: Throwable
                ) {
                    Toast.makeText(applicationContext,Constants.WENT_WRONG,Toast.LENGTH_SHORT).show()
                    binding.pb.visibility= View.GONE
                }

            })

    }
}