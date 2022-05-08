package com.ridobiko.ridobikoPartner.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ridobiko.ridobikoPartner.api.API
import com.ridobiko.ridobikoPartner.constants.Constants
import com.ridobiko.ridobikoPartner.databinding.ActivityLoginBinding
import com.ridobiko.ridobikoPartner.models.ApiResponseModel
import com.ridobiko.ridobikoPartner.models.LoginUserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.login.setOnClickListener{
            val email =binding.email.text.toString()
            val password =binding.email.text.toString()
            if(email=="")binding.email.error="Enter valid email"
            else if(password=="")binding.password.error="Enter valid password"
            else{
                API.get().login_vendor(email,password).enqueue(object:
                    Callback<ApiResponseModel<LoginUserResponse>> {
                    override fun onResponse(
                        call: Call<ApiResponseModel<LoginUserResponse>>,
                        response: Response<ApiResponseModel<LoginUserResponse>>
                    ) {
                        if(response.isSuccessful){
                            if(response.body()!!.success==Constants.SUCCESS){
                                val loinResponse=response.body()!!.data
                                val sharedPreferences=getSharedPreferences(Constants.PREFS_LOGIN_DETAILS, MODE_PRIVATE).edit()
                                sharedPreferences.putString(Constants.EMAIL,loinResponse.email)
                                sharedPreferences.putString(Constants.IS_VENDOR,loinResponse.is_vendor)
                                sharedPreferences.putString(Constants.VENDOR_ID,loinResponse.vendor_id)
                                sharedPreferences.putString(Constants.VENDOR_NAME,loinResponse.vendor_name)
                                sharedPreferences.apply()
                                startActivity(Intent(applicationContext,MainActivity::class.java))
                                finish()

                            }
                            Toast.makeText(applicationContext,response.body()!!.message,Toast.LENGTH_SHORT).show()

                        }else{
                            Toast.makeText(applicationContext,Constants.WENT_WRONG,Toast.LENGTH_SHORT).show()

                        }
                    }

                    override fun onFailure(call: Call<ApiResponseModel<LoginUserResponse>>, t: Throwable) {

                        Toast.makeText(applicationContext,Constants.WENT_WRONG,Toast.LENGTH_SHORT).show()

                    }

                })
            }

        }

    }
}