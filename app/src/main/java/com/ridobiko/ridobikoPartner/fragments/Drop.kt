package com.ridobiko.ridobikoPartner.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ridobiko.ridobikoPartner.AppVendor
import com.ridobiko.ridobikoPartner.databinding.FragmentDropBinding

class Drop : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding:FragmentDropBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentDropBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        if(AppVendor.selectedBooking.pickup!="Not Done"){
            binding.pickupNotDone.visibility=View.VISIBLE
        }else{
            binding.pickupDone.visibility=View.VISIBLE
        }
        return binding.root
    }
}