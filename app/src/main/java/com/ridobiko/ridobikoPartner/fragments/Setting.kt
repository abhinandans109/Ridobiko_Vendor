package com.ridobiko.ridobikoPartner.fragments

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ridobiko.ridobikoPartner.AppVendor
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.api.API
import com.ridobiko.ridobikoPartner.constants.Constants
import com.ridobiko.ridobikoPartner.databinding.FragmentSettingBinding
import com.ridobiko.ridobikoPartner.databinding.FragmentTodaysDropBinding
import com.ridobiko.ridobikoPartner.models.ChangeStatusResponseModel
import com.ridobiko.ridobikoPartner.models.MyBikesResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Setting : Fragment() {
    lateinit var selectedMyBike:MyBikesResponseModel;
    lateinit var binding: FragmentSettingBinding
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

        API.get().getMyBikes(requireContext().getSharedPreferences(Constants.PREFS_LOGIN_DETAILS, AppCompatActivity.MODE_PRIVATE).getString(Constants.EMAIL,null))

        binding.btnBlockVehicle.setOnClickListener {
            API.get().setPickupDropdate(binding.bookedFrom.text.toString(),binding.bookedTill.text.toString(),selectedMyBike.bike_id).enqueue(
                object : Callback<ChangeStatusResponseModel> {
                    override fun onResponse(
                        call: Call<ChangeStatusResponseModel>,
                        response: Response<ChangeStatusResponseModel>
                    ) {
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

                    }


                })
        }

        binding.btnFuelMeter.setOnClickListener {
            API.get().setFuelMeter(binding.fuelSize.selectedItem.toString(),binding.fuelBar.selectedItem.toString(),selectedMyBike.bike_id).enqueue(
                object : Callback<ChangeStatusResponseModel> {
                    override fun onResponse(
                        call: Call<ChangeStatusResponseModel>,
                        response: Response<ChangeStatusResponseModel>
                    ) {
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

                    }


                })
        }

        binding.btnhsrp.setOnClickListener {
            API.get().setStatusHsrp(binding.rentType.selectedItem.toString(),if(binding.hsrpYes.isChecked)"1" else "0",selectedMyBike.bike_id).enqueue(
                object : Callback<ChangeStatusResponseModel> {
                    override fun onResponse(
                        call: Call<ChangeStatusResponseModel>,
                        response: Response<ChangeStatusResponseModel>
                    ) {
                        if (response.isSuccessful) {
                            if (response.body()?.success == Constants.SUCCESS) {
                                Toast.makeText(requireContext(), "Hsrp updated", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(requireContext(), response.body()?.message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                    override fun onFailure(call: Call<ChangeStatusResponseModel>, t: Throwable) {
                        Toast.makeText(requireContext(),Constants.WENT_WRONG,Toast.LENGTH_SHORT).show()

                    }


                })
        }
binding.btnVehicleDetails.setOnClickListener {
    API.get().setMyBikesVehicleDetails(selectedMyBike.bike_id,binding.bikePlateNo.text.toString(),binding.brandName.text.toString(),binding.bikeName.text.toString(),
    binding.plateType.text.toString(),binding.weekdays.text.toString(),binding.weekends.text.toString(),binding.deposit.text.toString(),binding.hour.text.toString(),
    binding.limitday.text.toString(),binding.limitMonth.text.toString(),binding.additionalCost.text.toString(),binding.speed.text.toString(),binding.modelYear.text.toString()
    ,binding.bikeAddOn.text.toString(),binding.insurance.text.toString(),binding.pollution.text.toString(),binding.permit.text.toString(),binding.fitness.text.toString()).enqueue(
        object : Callback<ChangeStatusResponseModel> {
            override fun onResponse(
                call: Call<ChangeStatusResponseModel>,
                response: Response<ChangeStatusResponseModel>
            ) {

                    if (response.isSuccessful) {
                        if (response.body()?.success == Constants.SUCCESS) {
                            Toast.makeText(requireContext(), "Hsrp updated", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(requireContext(), response.body()?.message, Toast.LENGTH_SHORT).show()
                        }
                    }

            }

            override fun onFailure(call: Call<ChangeStatusResponseModel>, t: Throwable) {
                Toast.makeText(requireContext(),Constants.WENT_WRONG,Toast.LENGTH_SHORT).show()
            }


        })
}
binding.btnDiscount.setOnClickListener {
    API.get().setDiscountSubcription(selectedMyBike.bike_id,binding.d0.text.toString(),binding.d7.text.toString(),binding.d11.text.toString(),binding.d15.text.toString()
    ,binding.d21.text.toString(),binding.d30.text.toString(),binding.s1.text.toString(),binding.s2.text.toString(),binding.s3.text.toString(),binding.s6.text.toString(),binding.s12.text.toString()).enqueue(
        object : Callback<ChangeStatusResponseModel> {
            override fun onResponse(
                call: Call<ChangeStatusResponseModel>,
                response: Response<ChangeStatusResponseModel>
            ) {
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

            }


        })

}




        return binding.root
    }
}