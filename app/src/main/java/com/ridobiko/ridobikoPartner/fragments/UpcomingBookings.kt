package com.ridobiko.ridobikoPartner.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ridobiko.ridobikoPartner.adapters.BookingsAdapter
import com.ridobiko.ridobikoPartner.api.API
import com.ridobiko.ridobikoPartner.constants.Constants
import com.ridobiko.ridobikoPartner.databinding.FragmentUpcomingBookingsBinding
import com.ridobiko.ridobikoPartner.models.ApiResponseModel
import com.ridobiko.ridobikoPartner.models.BookingResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpcomingBookings : Fragment() {
    private var list: ArrayList<BookingResponseModel>?=null
    private var adapter: BookingsAdapter?=null
    lateinit var binding: FragmentUpcomingBookingsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        binding = FragmentUpcomingBookingsBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        binding.searchView.queryHint="Search"
        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(adapter!=null){
                    if(list!=null){
                        var filList=ArrayList<BookingResponseModel>();
                        for(item in list!!){
                            if(item.customer_name.lowercase().contains(newText.toString().lowercase())) filList.add(item)
                            if(item.trans_id.lowercase().contains(newText.toString().lowercase())) filList.add(item)
                            if(item.customer_mobile.lowercase().contains(newText.toString().lowercase())) filList.add(item)
                            if(item.bikes_id.lowercase().contains(newText.toString().lowercase())) filList.add(item)
                        }
                        adapter!!.filterList(filList)
                    }
                }
                return false
            }

        })

        API.get()
            .getUpcommingBookings(requireActivity().getSharedPreferences(Constants.PREFS_LOGIN_DETAILS,
                AppCompatActivity.MODE_PRIVATE
            ).getString(Constants.EMAIL,"")).enqueue(object:Callback<ApiResponseModel<ArrayList<BookingResponseModel>>>{
                override fun onResponse(
                    call: Call<ApiResponseModel<ArrayList<BookingResponseModel>>>,
                    response: Response<ApiResponseModel<ArrayList<BookingResponseModel>>>
                ) {
                    binding.rvTodaysPickups.layoutManager=LinearLayoutManager(requireActivity().applicationContext)
                    binding.rvTodaysPickups.adapter=BookingsAdapter(requireActivity().applicationContext,response.body()!!.data)
                    binding.pb.visibility= View.GONE
                }

                override fun onFailure(
                    call: Call<ApiResponseModel<ArrayList<BookingResponseModel>>>,
                    t: Throwable
                ) {
                    Toast.makeText(requireActivity().applicationContext,Constants.WENT_WRONG,Toast.LENGTH_SHORT).show()
                    binding.pb.visibility= View.GONE
                }

            })

        return binding.root

    }
}