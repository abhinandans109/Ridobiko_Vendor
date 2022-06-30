package com.ridobiko.ridobikoPartner.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ridobiko.ridobikoPartner.AppVendor
import com.ridobiko.ridobikoPartner.adapters.CustomerHistoryAdapter
import com.ridobiko.ridobikoPartner.api.API
import com.ridobiko.ridobikoPartner.constants.Constants
import com.ridobiko.ridobikoPartner.databinding.FragmentBookingCustBinding
import com.ridobiko.ridobikoPartner.models.ApiResponseModel
import com.ridobiko.ridobikoPartner.models.CustomerDetailsResponseModel
import com.ridobiko.ridobikoPartner.models.CustomerHistoryResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BookingFragment : Fragment() {
    private lateinit var binding: FragmentBookingCustBinding
    private lateinit var selectedCustomer: CustomerDetailsResponseModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentBookingCustBinding.inflate(inflater, container, false)
        selectedCustomer=AppVendor.selectedCustomer
        API.get().getBookingHistory(selectedCustomer.login_mobile).enqueue(object :Callback<ApiResponseModel<ArrayList<CustomerHistoryResponseModel>>>{
            override fun onResponse(
                call: Call<ApiResponseModel<ArrayList<CustomerHistoryResponseModel>>>,
                response: Response<ApiResponseModel<ArrayList<CustomerHistoryResponseModel>>>
            ) {
                if(response.isSuccessful){
                    if(response.body()?.success==Constants.SUCCESS){
                        binding.rvCustHistory.layoutManager= LinearLayoutManager(requireContext())
                        binding.rvCustHistory.adapter=CustomerHistoryAdapter(requireContext(),response.body()?.data!!)
                    }
                }
            }

            override fun onFailure(
                call: Call<ApiResponseModel<ArrayList<CustomerHistoryResponseModel>>>,
                t: Throwable
            ) {
            }

        })
        return binding.root
    }



}