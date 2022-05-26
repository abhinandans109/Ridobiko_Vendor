package com.ridobiko.ridobikoPartner.fragments

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import com.ridobiko.ridobikoPartner.AppVendor
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.api.API
import com.ridobiko.ridobikoPartner.databinding.FragmentOtherBinding
import com.ridobiko.ridobikoPartner.models.ApiResponseModel
import com.ridobiko.ridobikoPartner.models.BookingResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS

class Other : Fragment() {
    lateinit var binding:FragmentOtherBinding
    lateinit var selectedBooking:BookingResponseModel
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentOtherBinding.inflate(inflater)
        selectedBooking=AppVendor.selectedBooking
        binding.pDate.setText(selectedBooking.pickup_date.split(" ")[0])
        binding.pTime.setText(selectedBooking.pickup_date.split(" ")[1])
        binding.dDate.setText(selectedBooking.drop_date.split(" ")[0])
        binding.dTime.setText(selectedBooking.drop_date.split(" ")[1])

        binding.dDate.setOnClickListener{

            val dpd = DatePickerDialog(requireContext(),  { view, year, monthOfYear, dayOfMonth ->
                var dom="0"
                if(monthOfYear<10)dom+=monthOfYear+1
                else dom=(monthOfYear+1).toString()
                binding.dDate.setText("$year-$dom-$dayOfMonth")

            }, selectedBooking.drop_date.split(" ")[0].split("-")[0].toInt(), selectedBooking.drop_date
                .split(" ")[0].split("-")[1].toInt()-1, selectedBooking.drop_date
                .split(" ")[0].split("-")[2].toInt())

            dpd.show()
        }
        binding.dTime.setOnClickListener{
            TimePickerDialog(context, {view,hr,min->
                        binding.dTime.setText("$hr:$min:00")
                }, selectedBooking.drop_date.split(" ")[1].split(":")[0].toInt()
                , selectedBooking.drop_date.split(" ")[1].split(":")[1].toInt(), true).show()
        }
        binding.calRent.setOnClickListener{
            API.get().getrent(selectedBooking.vendor_email_id,selectedBooking.bikes_id,
                selectedBooking.drop_date.split(" ")[0]
                ,binding.dDate.text.toString(),selectedBooking.drop_date.split(" ")[1],binding.dTime.text.toString()).enqueue(object:Callback<ApiResponseModel<Int>>{
                override fun onResponse(
                    call: Call<ApiResponseModel<Int>>,
                    response: Response<ApiResponseModel<Int>>
                ) {
                    if(response.isSuccessful)
                    binding.totalRent.setText(response.body()!!.data.toString())
                }

                override fun onFailure(call: Call<ApiResponseModel<Int>>, t: Throwable) {
                }

            })
        }
        return binding.root
    }

}