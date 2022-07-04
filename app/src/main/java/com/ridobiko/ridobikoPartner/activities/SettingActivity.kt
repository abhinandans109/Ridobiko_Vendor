package com.ridobiko.ridobikoPartner.activities

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.github.dhaval2404.imagepicker.ImagePicker
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.api.API
import com.ridobiko.ridobikoPartner.constants.Constants
import com.ridobiko.ridobikoPartner.databinding.ActivityAddEmployeeBinding
import com.ridobiko.ridobikoPartner.databinding.ActivitySettingBinding
import com.ridobiko.ridobikoPartner.models.ApiResponseModel
import com.ridobiko.ridobikoPartner.models.ChangeStatusResponseModel
import com.ridobiko.ridobikoPartner.models.SettingsResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.InputStream

class SettingActivity : AppCompatActivity() {
    private  lateinit var binding: ActivitySettingBinding
    var dlImage:String?=null
    var panImage:String?=null
    var adhaarAImage:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivitySettingBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        supportActionBar?.title="Settings"
       binding.btShowEmp.setOnClickListener {
           startActivity(Intent(applicationContext, AddEmployeeActivity::class.java))
       }
        binding.rentType.adapter= ArrayAdapter<String>(applicationContext,R.layout.spinner_item,
            mutableListOf(
                "Based on store opening and closing time",
                "24 hours rental",
                "Count 1 day till 10 AM"
            ))

        API.get().getSettings(getSharedPreferences(Constants.PREFS_LOGIN_DETAILS, MODE_PRIVATE).getString(Constants.EMAIL,"null")).enqueue(object:Callback<ApiResponseModel<SettingsResponseModel>>{
            override fun onResponse(
                call: Call<ApiResponseModel<SettingsResponseModel>>,
                response: Response<ApiResponseModel<SettingsResponseModel>>
            ) {
               var data=response.body()?.data!!
                if(data.Home_Delivery.home_delivery=="1") binding.hdChecked.isChecked=true;
                else binding.hdUnchecked.isChecked=true;
                binding.less3km.setText(data.Home_Delivery.less_than_three_km)
                binding.threeto5km.setText(data.Home_Delivery.three_to_five_km)
                binding.fiveto8km.setText(data.Home_Delivery.five_to_eight_km)
                binding.eightto10km.setText(data.Home_Delivery.eight_to_ten_km)
                binding.morethan10km.setText(data.Home_Delivery.more_than_ten_km)

                binding.landmark.setText(data.Address.form_landmark)
                binding.area.setText(data.Address.form_area)
                binding.city.setText(data.Address.form_city)
                binding.pincode.setText(data.Address.form_pin)

                if(data.Pillion.weekly_rental=="1")binding.yesWeekRental.isChecked=true
                else binding.noWeekRental.isChecked=true
                if(data.Pillion.remaining_payment_flag=="1")binding.yesRemaining.isChecked=true
                else binding.noRemaining.isChecked=true
                binding.helmetCharge.setText("Rs "+data.Pillion.Helmet_charge)
                if(data.Pillion.rent_type=="1") binding.rentType.setSelection(1)
                else if(data.Pillion.rent_type=="2") binding.rentType.setSelection(0)
                else binding.rentType.setSelection(2)

                binding.acccNo.setText(data.Bank.account_no)
                binding.accHolder.setText(data.Bank.account_holder)
                binding.ifscCode.setText(data.Bank.ifsc)

                var days=data.Store_opening_closing.courses
                if(days.contains("Mon")) binding.mon.isChecked=true
                if(days.contains("Tue")) binding.tue.isChecked=true
                if(days.contains("Wed")) binding.wed.isChecked=true
                if(days.contains("Thurs")) binding.thus.isChecked=true
                if(days.contains("Fri")) binding.fri.isChecked=true
                if(days.contains("Sat")) binding.sat.isChecked=true
                if(days.contains("Sun")) binding.sun.isChecked=true

                binding.opening.setText(data.Store_opening_closing.form_opening+" "+data.Store_opening_closing.form_day)
                binding.closing.setText(data.Store_opening_closing.form_closing+" "+data.Store_opening_closing.closeing_day)

                binding.onum.setText(data.store_details.company_phone)
                binding.ename.setText(data.store_details.employee_name)
                binding.empNum.setText(data.store_details.employee_number)
                binding.shopName.setText(data.store_details.company_name)
                binding.location.setText(data.store_details.map_address)


            }

            override fun onFailure(
                call: Call<ApiResponseModel<SettingsResponseModel>>,
                t: Throwable
            ) {
                TODO("Not yet implemented")
            }

        })

           
           binding.btnAddress.setOnClickListener {

               API.get().setAddress(getSharedPreferences(Constants.PREFS_LOGIN_DETAILS, MODE_PRIVATE).getString(Constants.EMAIL,"null"),
                   binding.landmark.text.toString(),binding.area.text.toString(),binding.city.text.toString(),binding.pincode.text.toString(),null).enqueue(
                   object : Callback<ChangeStatusResponseModel>{
                       override fun onResponse(
                           call: Call<ChangeStatusResponseModel>,
                           response: Response<ChangeStatusResponseModel>
                       ) {
                           if(response.isSuccessful){
                               if(response.body()?.success==Constants.SUCCESS){
                                   Toast.makeText(applicationContext,"Address Updated", Toast.LENGTH_SHORT).show()
                               }else {
                                   Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_SHORT)
                                       .show()
                               }
                           }
                       }

                       override fun onFailure(call: Call<ChangeStatusResponseModel>, t: Throwable) {
                           Toast.makeText(applicationContext,Constants.WENT_WRONG,Toast.LENGTH_SHORT).show()

                       }

                   })
           }
            binding.hdChecked.setOnClickListener{
                if(binding.hdUnchecked.isChecked) binding.hdUnchecked.toggle()
            }

            binding.hdUnchecked.setOnClickListener{
                if(binding.hdChecked.isChecked) binding.hdChecked.toggle()
            }

           binding.btnHome.setOnClickListener {

               API.get().homeDelivery(getSharedPreferences(Constants.PREFS_LOGIN_DETAILS, MODE_PRIVATE).getString(Constants.EMAIL,"null"),
                   if(binding.hdChecked.isChecked)"1" else "0",binding.less3km.text.toString(),binding.threeto5km.text.toString(),binding.fiveto8km.text.toString(),binding.eightto10km.text.toString(),binding.morethan10km.text.toString()).enqueue(
                   object : Callback<ChangeStatusResponseModel>{
                       override fun onResponse(
                           call: Call<ChangeStatusResponseModel>,
                           response: Response<ChangeStatusResponseModel>
                       ) {
                           if(response.isSuccessful){
                               if(response.body()?.success==Constants.SUCCESS){
                                   Toast.makeText(applicationContext,"Data Updated", Toast.LENGTH_SHORT).show()
                               }else {
                                   Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_SHORT)
                                       .show()
                               }
                           }
                       }

                       override fun onFailure(call: Call<ChangeStatusResponseModel>, t: Throwable) {
                           Toast.makeText(applicationContext,Constants.WENT_WRONG,Toast.LENGTH_SHORT).show()

                       }

                   })
           }

        binding.btnBank.setOnClickListener { 
            API.get().setBank(getSharedPreferences(Constants.PREFS_LOGIN_DETAILS, MODE_PRIVATE).getString(Constants.EMAIL,"null"),
                binding.acccNo.text.toString(),binding.ifscCode.text.toString(),binding.accHolder.text.toString()).enqueue(
                object : Callback<ChangeStatusResponseModel> {
                    override fun onResponse(
                        call: Call<ChangeStatusResponseModel>,
                        response: Response<ChangeStatusResponseModel>
                    ) {
                        if(response.isSuccessful){
                            if(response.body()?.success==Constants.SUCCESS){
                                Toast.makeText(applicationContext,"Details Updated", Toast.LENGTH_SHORT).show()
                            }else {
                                Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    }

                    override fun onFailure(call: Call<ChangeStatusResponseModel>, t: Throwable) {
                        Toast.makeText(applicationContext,Constants.WENT_WRONG,Toast.LENGTH_SHORT).show()

                    }


                })
        }
        binding.btnstore.setOnClickListener { 
            API.get().setStore(getSharedPreferences(Constants.PREFS_LOGIN_DETAILS, MODE_PRIVATE).getString(Constants.EMAIL,"null"),binding.oname.text.toString(),
            binding.onum.text.toString(),binding.ename.text.toString(),binding.empNum.text.toString(),binding.shopName.text.toString(),binding.location.text.toString()).enqueue(
                object : Callback<ChangeStatusResponseModel> {
                    override fun onResponse(
                        call: Call<ChangeStatusResponseModel>,
                        response: Response<ChangeStatusResponseModel>
                    ) {
                        if(response.isSuccessful){
                            if(response.body()?.success==Constants.SUCCESS){
                                Toast.makeText(applicationContext,"Details Updated", Toast.LENGTH_SHORT).show()
                            }else {
                                Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    }

                    override fun onFailure(call: Call<ChangeStatusResponseModel>, t: Throwable) {
                        Toast.makeText(applicationContext,Constants.WENT_WRONG,Toast.LENGTH_SHORT).show()

                    }


                })
        }
       binding.btnDocument.setOnClickListener {
           API.get().documents(getSharedPreferences(Constants.PREFS_LOGIN_DETAILS, MODE_PRIVATE).getString(Constants.EMAIL,"null"),binding.license.text.toString(),
           binding.aadharCard.text.toString(),binding.panNum.text.toString(),adhaarAImage,panImage,dlImage).enqueue(object :

               Callback<ChangeStatusResponseModel> {
               override fun onResponse(
                   call: Call<ChangeStatusResponseModel>,
                   response: Response<ChangeStatusResponseModel>
               ) {
                   if(response.isSuccessful){
                       if(response.body()?.success==Constants.SUCCESS){
                           Toast.makeText(applicationContext,"Documents Updated", Toast.LENGTH_SHORT).show()
                           startActivity(Intent(applicationContext,MainActivity::class.java))
                           finish()
                       }else {
                           Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_SHORT)
                               .show()
                       }
                   }
               }

               override fun onFailure(call: Call<ChangeStatusResponseModel>, t: Throwable) {
                   Toast.makeText(applicationContext,Constants.WENT_WRONG,Toast.LENGTH_SHORT).show()

               }

           })
       }

        binding.dlUpload.setOnClickListener {

            ImagePicker.with(this).start(1)

        }
        binding.adhaarUpload.setOnClickListener {

            ImagePicker.with(this).start(2)

        }
        binding.panUpload.setOnClickListener {

            ImagePicker.with(this).start(3)

        }
        
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== Activity.RESULT_OK){
            when (requestCode) {
                1 -> {
                    binding.dlImage.setImageURI(data!!.data)
                    dlImage=photoConvert(data.data)
                }
                2 -> {
                    binding.adhaarImage.setImageURI(data!!.data)
                    adhaarAImage=photoConvert(data.data)
                }
                3 -> {
                    binding.panImage.setImageURI(data!!.data)
                    panImage=photoConvert(data.data)
                }
             }
        }
    }
    fun photoConvert(uri: Uri?): String? {
        try {
            return if (uri != null) {
//                val applicationContext: Context = BookingActivity.getContextOfApplication()
//                applicationContext.getContentResolver()
                val inputStream: InputStream = contentResolver.openInputStream(uri)!!
                val bitmap = BitmapFactory.decodeStream(inputStream)
                val outputStream = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 70, outputStream)
                val bytes = outputStream.toByteArray()
                val encode = Base64.encodeToString(bytes, Base64.DEFAULT)
                Log.e("ENCODE", encode)

                encode
            } else {

                null
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        return null
    }
}