package com.ridobiko.ridobikoPartner.fragments

import android.app.Activity
import android.content.Intent
//import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.dhaval2404.imagepicker.ImagePicker
import com.ridobiko.ridobikoPartner.AppVendor
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.activities.MyBikeActivity
import com.ridobiko.ridobikoPartner.activities.TermsConditonsActivity
import com.ridobiko.ridobikoPartner.api.API
import com.ridobiko.ridobikoPartner.constants.Constants
import com.ridobiko.ridobikoPartner.databinding.FragmentSettingBinding
import com.ridobiko.ridobikoPartner.databinding.FragmentTodaysDropBinding
import com.ridobiko.ridobikoPartner.models.ChangeStatusResponseModel
import com.ridobiko.ridobikoPartner.models.MyBikesResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.InputStream


class Setting : Fragment() {
    lateinit var selectedMyBike:MyBikesResponseModel;
    lateinit var binding: FragmentSettingBinding
    var topImage:String?=null
    var leftImage:String?=null
    var frontImage:String?=null
    var backImage:String?=null
    var feulImage:String?=null
//    var bikeFuel:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        selectedMyBike=AppVendor.selectedMyBike
//        binding.bikePlateNo.setText(selectedMyBike.bike_id)
     binding= FragmentSettingBinding.inflate(inflater, container, false)
        binding.rentType.adapter=ArrayAdapter<String>(requireContext(),R.layout.spinner_item,
            mutableListOf(
                "Based on store opening and closing time",
                "24 hours rental",
                "Count 1 day till 10 AM"
            ))

//        API.get().getMyBikes(requireContext().getSharedPreferences(Constants.PREFS_LOGIN_DETAILS, AppCompatActivity.MODE_PRIVATE).getString(Constants.EMAIL,null))
        binding.bookedFrom.setText(selectedMyBike.pickup.split(" ")[0])
        binding.bookedTill.setText(selectedMyBike.drop.split(" ")[0])
        binding.fuelSize.setText(selectedMyBike.fuel_tank)
        binding.fuelBar.setText(selectedMyBike.max_fuel_bars)
        binding.bikePlateNo.setText(selectedMyBike.bike_id)
        binding.brandName.setText(selectedMyBike.bike_brand)
        binding.bikeName.setText(selectedMyBike.bike_name)
        binding.plateType.setText(selectedMyBike.bike_plate_type)
        binding.weekdays.setText(selectedMyBike.rent_per_day)
        binding.weekends.setText(selectedMyBike.rent_per_day_weekend)
        binding.hour.setText(selectedMyBike.rent_per_hour)
        binding.deposit.setText(selectedMyBike.deposit)
        binding.limitday.setText(selectedMyBike.km_limit)
//        binding.limitMonth.setText(selectedMyBike.km_limit)
        binding.additionalCost.setText(selectedMyBike.additional_km_cost)
        binding.speed.setText(selectedMyBike.speed_limit)
        binding.modelYear.setText(selectedMyBike.modelYear)
        binding.bikeAddOn.setText(selectedMyBike.bike_added_on)
        binding.insurance.setText(selectedMyBike.insurance_expiry_date)
        binding.pollution.setText(selectedMyBike.puc_expiry_date)
        binding.permit.setText(selectedMyBike.permit_expiry_date)
        binding.fitness.setText(selectedMyBike.fitness_renewal_date)
        binding.d0.setText(selectedMyBike.rent_per_day_0_6)
        binding.d7.setText(selectedMyBike.rent_per_day_7_10)
        binding.d11.setText(selectedMyBike.rent_per_day_11_15)
        binding.d15.setText(selectedMyBike.rent_per_day_11_15)
        binding.d21.setText(selectedMyBike.rent_per_day_20_30)
        binding.d30.setText(selectedMyBike.rent_per_day_30_100)
        binding.s1.setText(selectedMyBike.Sub_1month)
        binding.s2.setText(selectedMyBike.Sub_2month)
        binding.s3.setText(selectedMyBike.Sub_3month)
        binding.s6.setText(selectedMyBike.Sub_6month)
        binding.s12.setText(selectedMyBike.Sub_12month)
//        binding.d30.setText(selectedMyBike.rent_per_day_30_100)


        binding.btnBlockVehicle.setOnClickListener {
            binding.pb.visibility=View.VISIBLE
            API.get().setPickupDropdate(binding.bookedFrom.text.toString(),binding.bookedTill.text.toString(),selectedMyBike.bike_id).enqueue(
                object : Callback<ChangeStatusResponseModel> {
                    override fun onResponse(
                        call: Call<ChangeStatusResponseModel>,
                        response: Response<ChangeStatusResponseModel>
                    ) {
                        binding.pb.visibility=View.GONE
                        if (response.isSuccessful) {
                            if (response.body()?.success == Constants.SUCCESS) {
                                Toast.makeText(requireContext(), " updated", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(requireContext(), response.body()?.message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                    override fun onFailure(call: Call<ChangeStatusResponseModel>, t: Throwable) {
                        Toast.makeText(requireContext(),Constants.WENT_WRONG,Toast.LENGTH_SHORT).show()
                        binding.pb.visibility=View.GONE
                    }


                })
        }

        binding.btnFuelMeter.setOnClickListener {
            binding.pb.visibility=View.VISIBLE
            API.get().setFuelMeter(binding.fuelSize.text.toString(),binding.fuelBar.text.toString(),selectedMyBike.bike_id).enqueue(
                object : Callback<ChangeStatusResponseModel> {
                    override fun onResponse(
                        call: Call<ChangeStatusResponseModel>,
                        response: Response<ChangeStatusResponseModel>
                    ) {
                        binding.pb.visibility=View.GONE
                        if (response.isSuccessful) {
                            if (response.body()?.success == Constants.SUCCESS) {
                                Toast.makeText(requireContext(), "Fuel meter updated", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(requireContext(), response.body()?.message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    override fun onFailure(call: Call<ChangeStatusResponseModel>, t: Throwable) {
                        Toast.makeText(requireContext(),Constants.WENT_WRONG,Toast.LENGTH_SHORT).show()
                        binding.pb.visibility=View.GONE
                    }


                })
        }
        binding.btnhsrp.setOnClickListener {
            binding.pb.visibility=View.VISIBLE
            API.get().setStatusHsrp(binding.rentType.selectedItem.toString(),if(binding.hsrpYes.isChecked)"1" else "0",selectedMyBike.bike_id).enqueue(
                object : Callback<ChangeStatusResponseModel> {
                    override fun onResponse(
                        call: Call<ChangeStatusResponseModel>,
                        response: Response<ChangeStatusResponseModel>
                    ) {
                        binding.pb.visibility=View.GONE
                        if (response.isSuccessful) {
                            if (response.body()?.success == Constants.SUCCESS) {
                                Toast.makeText(requireContext(), "Hsrp updated", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(requireContext(), response.body()?.message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                    override fun onFailure(call: Call<ChangeStatusResponseModel>, t: Throwable) {
                        binding.pb.visibility=View.GONE
                        Toast.makeText(requireContext(),Constants.WENT_WRONG,Toast.LENGTH_SHORT).show()

                    }


                })
        }
binding.btnVehicleDetails.setOnClickListener {
    binding.pb.visibility=View.VISIBLE
    API.get().setMyBikesVehicleDetails(selectedMyBike.bike_id,binding.bikePlateNo.text.toString(),binding.brandName.text.toString(),binding.bikeName.text.toString(),
    binding.plateType.text.toString(),binding.weekdays.text.toString(),binding.weekends.text.toString(),binding.deposit.text.toString(),binding.hour.text.toString(),
    binding.limitday.text.toString(),binding.limitMonth.text.toString(),binding.additionalCost.text.toString(),binding.speed.text.toString(),binding.modelYear.text.toString()
    ,binding.bikeAddOn.text.toString(),binding.insurance.text.toString(),binding.pollution.text.toString(),binding.permit.text.toString(),binding.fitness.text.toString(),frontImage,backImage,leftImage,topImage,feulImage).enqueue(
        object : Callback<ChangeStatusResponseModel> {
            override fun onResponse(
                call: Call<ChangeStatusResponseModel>,
                response: Response<ChangeStatusResponseModel>
            ) {
                binding.pb.visibility=View.GONE

                    if (response.isSuccessful) {
                        if (response.body()?.success == Constants.SUCCESS) {
                            Toast.makeText(requireContext(), "Data updated", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(requireContext(), response.body()?.message, Toast.LENGTH_SHORT).show()
                        }
                    }

            }

            override fun onFailure(call: Call<ChangeStatusResponseModel>, t: Throwable) {
                Toast.makeText(requireContext(),Constants.WENT_WRONG,Toast.LENGTH_SHORT).show()
                binding.pb.visibility=View.GONE
            }


        })
}
binding.btnDiscount.setOnClickListener {
    binding.pb.visibility=View.VISIBLE
    API.get().setDiscountSubcription(selectedMyBike.bike_id,binding.d0.text.toString(),binding.d7.text.toString(),binding.d11.text.toString(),binding.d15.text.toString()
    ,binding.d21.text.toString(),binding.d30.text.toString(),binding.s1.text.toString(),binding.s2.text.toString(),binding.s3.text.toString(),binding.s6.text.toString(),binding.s12.text.toString()).enqueue(
        object : Callback<ChangeStatusResponseModel> {
            override fun onResponse(
                call: Call<ChangeStatusResponseModel>,
                response: Response<ChangeStatusResponseModel>
            ) {
                binding.pb.visibility=View.GONE
                if (response.isSuccessful) {
                    if (response.body()?.success == Constants.SUCCESS) {
                        Toast.makeText(requireContext(), "Data updated", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireContext(), response.body()?.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<ChangeStatusResponseModel>, t: Throwable) {
                binding.pb.visibility=View.GONE
                Toast.makeText(requireContext(),Constants.WENT_WRONG,Toast.LENGTH_SHORT).show()

            }


        })

}
binding.btnTC.setOnClickListener {
    startActivity(Intent(requireContext(),TermsConditonsActivity::class.java))
}
        binding.fUpload.setOnClickListener {

            ImagePicker.with(this).start(1)

        }
        binding.backUpload.setOnClickListener {
            ImagePicker.with(this).start(2)

        }
        binding.topUpload.setOnClickListener {
            ImagePicker.with(this).start(3)

        }
        binding.leftUpload.setOnClickListener {
            ImagePicker.with(this).start(4)

        }
        binding.fmUpload.setOnClickListener {
            ImagePicker.with(this).start(5)

        }






        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== Activity.RESULT_OK){
            when (requestCode) {
                1 -> {
                    binding.settingFront.setImageURI(data!!.data)
                    frontImage=photoConvert(data.data)
                }
                2 -> {
                    binding.BackImage.setImageURI(data!!.data)
                    backImage=photoConvert(data.data)
                }
                3 -> {
                    binding.topImage.setImageURI(data!!.data)
                    topImage=photoConvert(data.data)
                }
                4 -> {
                    binding.leftImage.setImageURI(data!!.data)
                    leftImage=photoConvert(data.data)

                }
                5 -> {
                    binding.fm.setImageURI(data!!.data)
                    feulImage=photoConvert(data.data)

                }

            }
        }
    }
    fun photoConvert(uri: Uri?): String? {
        try {
            return if (uri != null) {
//                val applicationContext: Context = BookingActivity.getContextOfApplication()
//                applicationContext.getContentResolver()
                val inputStream: InputStream = activity?.applicationContext!!.getContentResolver().openInputStream(uri)!!
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