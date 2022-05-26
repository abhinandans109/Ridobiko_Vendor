package com.ridobiko.ridobikoPartner.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ridobiko.ridobikoPartner.AppVendor
import com.ridobiko.ridobikoPartner.api.API
import com.ridobiko.ridobikoPartner.constants.Constants
import com.ridobiko.ridobikoPartner.databinding.ActivityMainBinding
import com.ridobiko.ridobikoPartner.models.ApiResponseModel
import com.ridobiko.ridobikoPartner.models.FuelPriceAndNumbers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
   private lateinit var binding :ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        API.get().getNumberAndFuel(getSharedPreferences(Constants.PREFS_LOGIN_DETAILS, MODE_PRIVATE).
        getString(Constants.EMAIL,"email")).enqueue(object :
            Callback<ApiResponseModel<FuelPriceAndNumbers>> {
            override fun onResponse(
                call: Call<ApiResponseModel<FuelPriceAndNumbers>>,
                response: Response<ApiResponseModel<FuelPriceAndNumbers>>
            ) {
               if(response.isSuccessful){
                   if(response.body()!!.success.equals(Constants.SUCCESS)){
                       binding.todaysPickups.text=response.body()!!.data.pickups.toString()
                       binding.todaysDrops.text=response.body()!!.data.drops.toString()
                       binding.upcommingBookingNumber.text=response.body()!!.data.upcommings.toString()
                       binding.allNo.text=response.body()!!.data.all.toString()
                       AppVendor.fuel_price=response.body()!!.data.fuel_price
                       binding.todaysPickups.visibility= View.VISIBLE
                       binding.todaysDrops.visibility= View.VISIBLE
                       binding.upcommingBookingNumber.visibility= View.VISIBLE
                       binding.allNo.visibility= View.VISIBLE
                   }
               }
                binding.pb1.visibility= View.GONE
                binding.pb2.visibility= View.GONE
                binding.pb3.visibility= View.GONE
                binding.pb4.visibility= View.GONE
            }

            override fun onFailure(
                call: Call<ApiResponseModel<FuelPriceAndNumbers>>,
                t: Throwable
            ) {
                binding.pb1.visibility= View.GONE
                binding.pb2.visibility= View.GONE
                binding.pb3.visibility= View.GONE
                binding.pb4.visibility= View.GONE

            }



        })

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