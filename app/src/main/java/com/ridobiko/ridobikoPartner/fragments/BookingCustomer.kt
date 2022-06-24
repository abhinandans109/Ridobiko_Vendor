package com.ridobiko.ridobikoPartner.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.ridobiko.ridobikoPartner.AppVendor
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.api.API
import com.ridobiko.ridobikoPartner.constants.Constants
import com.ridobiko.ridobikoPartner.databinding.FragmentBookingCustomerBinding
import com.ridobiko.ridobikoPartner.models.BookingResponseModel
import com.ridobiko.ridobikoPartner.models.ChangeStatusResponseModel
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private lateinit var selectedBooking:BookingResponseModel
private lateinit var binding:FragmentBookingCustomerBinding

class BookingCustomer : Fragment() {

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentBookingCustomerBinding.inflate(inflater, container, false)
        selectedBooking=AppVendor.selectedBooking

        Picasso.get().load(selectedBooking.bike_image).placeholder(R.drawable.bike_placeholder).into(binding.bikeImage)

        binding.bookingStart.text= selectedBooking.pickup_date.split(" ")[0]
        binding.bookingEnd.text= selectedBooking.drop_date.split(" ")[0]
        binding.bookedOn.text= selectedBooking.bookedon
        binding.cusName.text= selectedBooking.customer_name
        binding.number.text= selectedBooking.customer_mobile

        binding.bookingStatus.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if(position==1||position==2)
                API.get().changeAdminStatus(selectedBooking.trans_id, binding.bookingStatus.selectedItem.toString()).enqueue(
                    object :Callback<ChangeStatusResponseModel>{
                        override fun onResponse(
                            call: Call<ChangeStatusResponseModel>,
                            response: Response<ChangeStatusResponseModel>
                        ) {
                            if(response.isSuccessful){
                                Toast.makeText(requireContext(),response.body()?.message,Toast.LENGTH_SHORT).show()

                            }
                        }

                        override fun onFailure(call: Call<ChangeStatusResponseModel>, t: Throwable) {
                           Toast.makeText(requireContext(),Constants.WENT_WRONG,Toast.LENGTH_SHORT).show()
                        }

                    }
                )
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
        binding.bookingStatus.adapter=ArrayAdapter<String>(requireContext(),R.layout.spinner_item,
            mutableListOf(selectedBooking.admin_status,if(selectedBooking.admin_status=="pending")"confirmed" else "pending","Cancelled"))

        //call button
        binding.callButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL);
            val number= selectedBooking.customer_mobile
            intent.data = Uri.parse("tel:$number")
            startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        }

        binding.documentUploadStatus.text = if(!selectedBooking.image_aadhar_front.isNullOrEmpty()) {
            "Done"
        } else {
            "Not Done"
        }
        binding.documentVerificationStatus.text = if(selectedBooking.aadhar_verified=="1") {
            "Done"
        } else {
            "Not Done"
        }
        binding.pickupType.text=if(selectedBooking.delivery_location.isNullOrEmpty()) "Home Delivery" else "From Store"
        binding.securityDeposit.text= selectedBooking.security_deposit
        binding.bikeName.text= selectedBooking.bike_name
        binding.bikeId.text= selectedBooking.bikes_id
        binding.totalRent.text= selectedBooking.rent
        binding.paidRent.text= selectedBooking.amount_paid
        binding.remainingRent.text= selectedBooking.amount_left
        binding.discount.text= selectedBooking.discount
        binding.paymentMode.text= selectedBooking.payment_mode
        binding.address.text= selectedBooking.delivery_location+" "+ selectedBooking.landmark

        return binding.root
    }

}