package com.ridobiko.ridobikoPartner.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.api.API
import com.ridobiko.ridobikoPartner.constants.Constants
import com.ridobiko.ridobikoPartner.databinding.ActivityAddEmployeeBinding
import com.ridobiko.ridobikoPartner.databinding.ActivitySettingBinding
import com.ridobiko.ridobikoPartner.models.ChangeStatusResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SettingActivity : AppCompatActivity() {
    private  lateinit var binding: ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivitySettingBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        supportActionBar?.title="Settings"
       binding.btShowEmp.setOnClickListener{
           startActivity(Intent(applicationContext,AddEmployeeActivity::class.java))
           
           
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
            binding.onum.text.toString(),binding.ename.text.toString(),binding.empNum.text.toString(),binding.shopName.text.toString()).enqueue(
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
           binding.aadharCard.text.toString(),binding.panNum.text.toString()).enqueue(object :

               Callback<ChangeStatusResponseModel> {
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
        
    }
}