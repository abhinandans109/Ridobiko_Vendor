package com.ridobiko.ridobikoPartner.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.adapters.ServiceHistory_Adapter
import com.ridobiko.ridobikoPartner.adapters.TripHistoryAdapter
import com.ridobiko.ridobikoPartner.databinding.FragmentAllBinding
import com.ridobiko.ridobikoPartner.databinding.FragmentTripHistoryBinding
import com.ridobiko.ridobikoPartner.models.Service_history_Model
import com.ridobiko.ridobikoPartner.models.TripHistoryModel


class TripHistory : Fragment() {
    lateinit var binding: FragmentTripHistoryBinding
    private  lateinit var TripHistoryList:ArrayList<TripHistoryModel>
    private  lateinit var TripHistoryAdapter: TripHistoryAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
    binding=  FragmentTripHistoryBinding.inflate(inflater, container, false)
TripHistoryList=ArrayList()
        binding.rvTripHistory.layoutManager= LinearLayoutManager(context)
        TripHistoryList.add(TripHistoryModel("00","Confirmed","Ashish","8076889036","INR 1.00","INR 00","INR 1","2022-06-19 18:00:00","2022-06-19 18:00:00"))
        TripHistoryList.add(TripHistoryModel("01","Confirmed","Ashish","8076889036","INR 1.00","INR 00","INR 1","2022-06-19 18:00:00","2022-06-19 18:00:00"))
        TripHistoryList.add(TripHistoryModel("02","Confirmed","Ashish","8076889036","INR 1.00","INR 00","INR 1","2022-06-19 18:00:00","2022-06-19 18:00:00"))
        TripHistoryList.add(TripHistoryModel("03","Confirmed","Ashish","8076889036","INR 1.00","INR 00","INR 1","2022-06-19 18:00:00","2022-06-19 18:00:00"))
        TripHistoryList.add(TripHistoryModel("04","Confirmed","Ashish","8076889036","INR 1.00","INR 00","INR 1","2022-06-19 18:00:00","2022-06-19 18:00:00"))
        TripHistoryList.add(TripHistoryModel("05","Confirmed","Ashish","8076889036","INR 1.00","INR 00","INR 1","2022-06-19 18:00:00","2022-06-19 18:00:00"))
        TripHistoryList.add(TripHistoryModel("06","Confirmed","Ashish","8076889036","INR 1.00","INR 00","INR 1","2022-06-19 18:00:00","2022-06-19 18:00:00"))






        TripHistoryAdapter= TripHistoryAdapter(TripHistoryList)
        binding.rvTripHistory.adapter=TripHistoryAdapter





        return binding.root
    }
}