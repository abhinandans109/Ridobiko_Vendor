package com.ridobiko.ridobikoPartner.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ridobiko.ridobikoPartner.AppVendor
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.databinding.FragmentBookingCustomerBinding
import com.ridobiko.ridobikoPartner.models.BookingResponseModel
import com.squareup.picasso.Picasso

private lateinit var booking:BookingResponseModel
private lateinit var binding:FragmentBookingCustomerBinding

class BookingCustomer : Fragment() {

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentBookingCustomerBinding.inflate(inflater, container, false)
        booking=AppVendor.selectedBooking

        Picasso.get().load(booking.bike_image).placeholder(R.drawable.bike_placeholder).into(binding.bikeImage)
        binding.bookingStart.text= booking.pickup_date.split(" ")[0]
        binding.bookingEnd.text= booking.drop_date.split(" ")[0]
        binding.bookedOn.text= booking.bookedon
        binding.cusName.text= booking.customer_name
        binding.number.text= booking.customer_mobile

        //call button
        binding.callButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL);
            val number= booking.customer_mobile
            intent.data = Uri.parse("tel:$number")
            startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        }

        binding.documentUploadStatus.text = if(!booking.image_aadhar_front.isNullOrEmpty()) {
            "Done"
        } else {
            "Not Done"
        }
        binding.documentVerificationStatus.text = if(booking.aadhar_verified=="1") {
            "Done"
        } else {
            "Not Done"
        }
        binding.pickupType.text=if(booking.delivery_location.isNullOrEmpty()) "Home Delivery" else "From Store"
        binding.securityDeposit.text= booking.security_deposit
        binding.bikeName.text= booking.bike_name
        binding.bikeId.text= booking.bikes_id
        binding.totalRent.text= booking.rent
        binding.paidRent.text= booking.amount_paid
        binding.remainingRent.text= booking.amount_left
        binding.discount.text= booking.discount
        binding.paymentMode.text= booking.payment_mode
        binding.address.text= booking.delivery_location+" "+ booking.landmark

        return binding.root
    }

}