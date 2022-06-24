package com.ridobiko.ridobikoPartner.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        binding.btnBlockVehicle.setOnClickListener {
            API.get().setPickupDropdate(binding.bookedFrom.text.toString(),binding.bookedTill.text.toString(),selectedMyBike.bike_id).enqueue(
                object : Callback<ChangeStatusResponseModel> {
                    override fun onResponse(
                        call: Call<ChangeStatusResponseModel>,
                        response: Response<ChangeStatusResponseModel>
                    ) {
                        TODO("Not yet implemented")
                    }

                    override fun onFailure(call: Call<ChangeStatusResponseModel>, t: Throwable) {
                        TODO("Not yet implemented")
                    }


                })
        }


        return binding.root
    }
}