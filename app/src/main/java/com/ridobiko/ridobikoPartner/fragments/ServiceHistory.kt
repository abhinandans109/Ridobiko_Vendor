package com.ridobiko.ridobikoPartner.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.adapters.ServiceHistory_Adapter
import com.ridobiko.ridobikoPartner.adapters.SupportAdapter
import com.ridobiko.ridobikoPartner.databinding.FragmentBookingCustomerBinding
import com.ridobiko.ridobikoPartner.databinding.FragmentServiceHistoryBinding
import com.ridobiko.ridobikoPartner.models.Service_history_Model
import com.ridobiko.ridobikoPartner.models.Support_Model


class ServiceHistory : Fragment() {
    private lateinit var binding: FragmentServiceHistoryBinding
    private  lateinit var serviceHistoryList:ArrayList<Service_history_Model>
    private  lateinit var ServiceHistory_Adapter: ServiceHistory_Adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentServiceHistoryBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        serviceHistoryList= ArrayList()
        binding.rvServiceHis.layoutManager= LinearLayoutManager(context)

        serviceHistoryList.add(Service_history_Model("#HDJSU7D","Done","21-05-22","25-05-22","Yamaha","Yash gupta","Gagan chauhan","Done","74hj2","4673","NA","2000 Rs.","10,000 Rs.","10,000 Rs."))
        serviceHistoryList.add(Service_history_Model("#HDJSU7D","Done","21-05-22","25-05-22","Yamaha","Yash gupta","Gagan chauhan","Done","74hj2","4673","NA","2000 Rs.","10,000 Rs.","10,000 Rs."))
        serviceHistoryList.add(Service_history_Model("#HDJSU7D","Done","21-05-22","25-05-22","Yamaha","Yash gupta","Gagan chauhan","Done","74hj2","4673","NA","2000 Rs.","10,000 Rs.","10,000 Rs."))
        serviceHistoryList.add(Service_history_Model("#HDJSU7D","Done","21-05-22","25-05-22","Yamaha","Yash gupta","Gagan chauhan","Done","74hj2","4673","NA","2000 Rs.","10,000 Rs.","10,000 Rs."))
        serviceHistoryList.add(Service_history_Model("#HDJSU7D","Done","21-05-22","25-05-22","Yamaha","Yash gupta","Gagan chauhan","Done","74hj2","4673","NA","2000 Rs.","10,000 Rs.","10,000 Rs."))
        serviceHistoryList.add(Service_history_Model("#HDJSU7D","Done","21-05-22","25-05-22","Yamaha","Yash gupta","Gagan chauhan","Done","74hj2","4673","NA","2000 Rs.","10,000 Rs.","10,000 Rs."))
        serviceHistoryList.add(Service_history_Model("#HDJSU7D","Done","21-05-22","25-05-22","Yamaha","Yash gupta","Gagan chauhan","Done","74hj2","4673","NA","2000 Rs.","10,000 Rs.","10,000 Rs."))
        serviceHistoryList.add(Service_history_Model("#HDJSU7D","Done","21-05-22","25-05-22","Yamaha","Yash gupta","Gagan chauhan","Done","74hj2","4673","NA","2000 Rs.","10,000 Rs.","10,000 Rs."))
        serviceHistoryList.add(Service_history_Model("#HDJSU7D","Done","21-05-22","25-05-22","Yamaha","Yash gupta","Gagan chauhan","Done","74hj2","4673","NA","2000 Rs.","10,000 Rs.","10,000 Rs."))

        ServiceHistory_Adapter= ServiceHistory_Adapter(serviceHistoryList)
        binding.rvServiceHis.adapter=ServiceHistory_Adapter



        return binding.root

    }



}