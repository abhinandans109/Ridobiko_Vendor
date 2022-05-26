package com.ridobiko.ridobikoPartner.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.databinding.FragmentBookingCustomerBinding
import com.ridobiko.ridobikoPartner.databinding.FragmentServiceHistoryBinding


class ServiceHistory : Fragment() {
    private lateinit var binding: FragmentServiceHistoryBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentServiceHistoryBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        return binding.root

    }



}