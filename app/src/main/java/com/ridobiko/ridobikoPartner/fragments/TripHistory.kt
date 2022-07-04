package com.ridobiko.ridobikoPartner.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ridobiko.ridobikoPartner.AppVendor
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.adapters.ServiceHistory_Adapter
import com.ridobiko.ridobikoPartner.adapters.TripHistoryAdapter
import com.ridobiko.ridobikoPartner.databinding.FragmentAllBinding
import com.ridobiko.ridobikoPartner.databinding.FragmentTripHistoryBinding
import com.ridobiko.ridobikoPartner.models.MyBikesResponseModel
import com.ridobiko.ridobikoPartner.models.Service_history_Model
import com.ridobiko.ridobikoPartner.models.TripHistoryModel


class TripHistory : Fragment() {
    lateinit var binding: FragmentTripHistoryBinding
    private  lateinit var TripHistoryList:ArrayList<TripHistoryModel>
    private  lateinit var TripHistoryAdapter: TripHistoryAdapter
    private lateinit var selectedMybike:MyBikesResponseModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
    binding=  FragmentTripHistoryBinding.inflate(inflater, container, false)
    TripHistoryList=ArrayList()
        binding.rvTripHistory.layoutManager= LinearLayoutManager(context)
        selectedMybike=AppVendor.selectedMyBike
        if(selectedMybike.Trip_History!=null){
            var count=1;
            for(trip in selectedMybike.Trip_History){
                if(trip!=null)
                TripHistoryList.add(
                    TripHistoryModel(
                        (count++).toString(),trip.admin_status,trip.customer_name,trip.customer_mobile,trip.amount_paid,trip.amount_left,trip.rent,trip.pickup_date,trip.drop_date
                    )
                )
            }
        }
        TripHistoryAdapter= TripHistoryAdapter(TripHistoryList)
        binding.rvTripHistory.adapter=TripHistoryAdapter
        return binding.root
    }
}