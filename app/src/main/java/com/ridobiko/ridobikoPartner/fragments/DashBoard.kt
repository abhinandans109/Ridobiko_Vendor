package com.ridobiko.ridobikoPartner.fragments

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ridobiko.ridobikoPartner.AppVendor
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.databinding.FragmentDashBoardBinding
import com.ridobiko.ridobikoPartner.models.MyBikesResponseModel

class DashBoard : Fragment() {
    lateinit var selectedMyBike:MyBikesResponseModel
    lateinit var binding:FragmentDashBoardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentDashBoardBinding.inflate(layoutInflater)
        selectedMyBike=AppVendor.selectedMyBike
        binding.bookingStart.text=selectedMyBike.pickup.split(" ")[0]
        binding.bookingEnd.text=selectedMyBike.drop.split(" ")[0]
        binding.noOfBookings.text=selectedMyBike.Dashboard.bookings
        binding.maintainanceAmount.text=selectedMyBike.Dashboard.Total_Maintenance_Cost
        binding.totalBookingAmount.text=selectedMyBike.Dashboard.amount
        binding.uniqueCustomers.text=selectedMyBike.Dashboard.customer_name_count
        binding.daysBooked.text=selectedMyBike.Dashboard.difference
        return binding.root
    }


}