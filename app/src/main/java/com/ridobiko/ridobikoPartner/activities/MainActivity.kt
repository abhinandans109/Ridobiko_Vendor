package com.ridobiko.ridobikoPartner.activities

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.ridobiko.ridobikoPartner.AppVendor
import com.ridobiko.ridobikoPartner.R
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
        supportActionBar?.hide()
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
                       binding.bikeDatabase.visibility= View.VISIBLE


                   }
               }
                binding.pb1.visibility= View.GONE
                binding.pb2.visibility= View.GONE
                binding.pb3.visibility= View.GONE
                binding.pb4.visibility= View.GONE
                binding.pb5.visibility= View.GONE
            }

            override fun onFailure(
                call: Call<ApiResponseModel<FuelPriceAndNumbers>>,
                t: Throwable
            ) {
                binding.pb1.visibility= View.GONE
                binding.pb2.visibility= View.GONE
                binding.pb3.visibility= View.GONE
                binding.pb4.visibility= View.GONE
                binding.pb5.visibility= View.GONE


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
        binding.underDev1.setOnClickListener {
            val inflater = layoutInflater
            val popupView: View = LayoutInflater.from(applicationContext).inflate(R.layout.popup, null)
            val width = LinearLayout.LayoutParams.WRAP_CONTENT
            val height = LinearLayout.LayoutParams.WRAP_CONTENT
            val focusable = true
            val popupWindow = PopupWindow(popupView, width, height, focusable)
            popupWindow.showAtLocation(it, Gravity.CENTER, 0, 0)
            popupView.setOnTouchListener { v, event ->
                popupWindow.dismiss()
                true
            }
        }
        binding.underDev1.setOnClickListener {
            val inflater = layoutInflater
            val popupView: View = LayoutInflater.from(applicationContext).inflate(R.layout.popup, null)
            val width = LinearLayout.LayoutParams.WRAP_CONTENT
            val height = LinearLayout.LayoutParams.WRAP_CONTENT
            val focusable = true
            val popupWindow = PopupWindow(popupView, width, height, focusable)
            popupWindow.showAtLocation(it, Gravity.CENTER, 0, 0)
            popupView.setOnTouchListener { v, event ->
                popupWindow.dismiss()
                true
            }
        }

        binding.underDev3.setOnClickListener {
            val inflater = layoutInflater
            val popupView: View = LayoutInflater.from(applicationContext).inflate(R.layout.popup, null)
            val width = LinearLayout.LayoutParams.WRAP_CONTENT
            val height = LinearLayout.LayoutParams.WRAP_CONTENT
            val focusable = true
            val popupWindow = PopupWindow(popupView, width, height, focusable)
            popupWindow.showAtLocation(it, Gravity.CENTER, 0, 0)
            popupView.setOnTouchListener { v, event ->
                popupWindow.dismiss()
                true
            }
        }
        binding.underDev4.setOnClickListener {
            val inflater = layoutInflater
            val popupView: View = LayoutInflater.from(applicationContext).inflate(R.layout.popup, null)
            val width = LinearLayout.LayoutParams.WRAP_CONTENT
            val height = LinearLayout.LayoutParams.WRAP_CONTENT
            val focusable = true
            val popupWindow = PopupWindow(popupView, width, height, focusable)
            popupWindow.showAtLocation(it, Gravity.CENTER, 0, 0)
            popupView.setOnTouchListener { v, event ->
                popupWindow.dismiss()
                true
            }
        }
        binding.settings.setOnClickListener{
            startActivity(Intent(applicationContext, SettingActivity::class.java))
        }
        binding.customerDetails.setOnClickListener{
            startActivity(Intent(applicationContext, CustomerDetails::class.java))
        }
        binding.bikeDatabaseCard.setOnClickListener{
            startActivity(Intent(applicationContext, BikeDatabase_Activity::class.java))
        }
        binding.underDev2.setOnClickListener {
            startActivity(Intent(applicationContext, AccountActivity::class.java))

        }



        //logout
        binding.logout.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setMessage("Are you sure you want to Logout?")

                .setCancelable(false)

                .setPositiveButton("YES", DialogInterface.OnClickListener {

                        dialog, id -> getSharedPreferences(Constants.PREFS_LOGIN_DETAILS,
                    MODE_PRIVATE).edit().clear().commit()
                    startActivity(Intent(applicationContext,LoginActivity::class.java))
                })

                .setNegativeButton("NO", DialogInterface.OnClickListener {
                        dialog, id -> dialog.cancel()
                })
            val alert = dialogBuilder.create()

            alert.setTitle("Logout")

            alert.show()

        }

    }
}