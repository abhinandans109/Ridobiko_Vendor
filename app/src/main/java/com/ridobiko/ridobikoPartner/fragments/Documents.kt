package com.ridobiko.ridobikoPartner.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ridobiko.ridobikoPartner.AppVendor
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.databinding.FragmentDashBoardBinding
import com.ridobiko.ridobikoPartner.databinding.FragmentDocumentsBinding
import com.ridobiko.ridobikoPartner.models.MyBikesResponseModel
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

class Documents : Fragment() {
    lateinit var selectedMyBike: MyBikesResponseModel
    lateinit var binding: FragmentDocumentsBinding
    var BASE_IMAGE="https://www.ridobiko.com/android_app_ridobiko_owned_store/vehicle_images/"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentDocumentsBinding.inflate(layoutInflater)
        selectedMyBike=AppVendor.selectedMyBike
        BASE_IMAGE+=selectedMyBike.vendor_email_id+"/"
        Picasso.get().load(BASE_IMAGE + selectedMyBike.RC).resize(180,200)
            .placeholder(R.drawable.bike_placeholder).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(
                MemoryPolicy.NO_CACHE).into(binding.RcImage)
        Picasso.get().load(BASE_IMAGE + selectedMyBike.Insurance).resize(180,200)
            .placeholder(R.drawable.bike_placeholder).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(
                MemoryPolicy.NO_CACHE).into(binding.InsuranceImage)
        Picasso.get().load(BASE_IMAGE + selectedMyBike.PUC).resize(180,200)
            .placeholder(R.drawable.bike_placeholder).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(
                MemoryPolicy.NO_CACHE).into(binding.PucImage)
        Picasso.get().load(BASE_IMAGE + selectedMyBike.Permit).resize(180,200)
            .placeholder(R.drawable.bike_placeholder).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(
                MemoryPolicy.NO_CACHE).into(binding.permitAImage)
        Picasso.get().load(BASE_IMAGE + selectedMyBike.permit_b).resize(180,200)
            .placeholder(R.drawable.bike_placeholder).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(
                MemoryPolicy.NO_CACHE).into(binding.permitBImage)
        Picasso.get().load(BASE_IMAGE + selectedMyBike.Purchase_Bill).resize(180,200)
            .placeholder(R.drawable.bike_placeholder).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(
                MemoryPolicy.NO_CACHE).into(binding.purchaseImage)

        return binding.root
    }

}