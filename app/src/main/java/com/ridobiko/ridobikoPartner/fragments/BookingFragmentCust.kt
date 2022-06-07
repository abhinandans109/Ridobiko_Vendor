package com.ridobiko.ridobikoPartner.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ridobiko.ridobikoPartner.databinding.FragmentBookingCustBinding


class BookingFragment : Fragment() {
    private lateinit var binding: FragmentBookingCustBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentBookingCustBinding.inflate(inflater, container, false)

        return binding.root
    }



}