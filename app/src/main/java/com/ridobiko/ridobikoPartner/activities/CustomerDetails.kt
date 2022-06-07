package com.ridobiko.ridobikoPartner.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.adapters.AddEmployee_Adapter
import com.ridobiko.ridobikoPartner.adapters.CustomerDetailsAdapter
import com.ridobiko.ridobikoPartner.databinding.ActivityCustomerDetailsBinding
import com.ridobiko.ridobikoPartner.models.AddEmp_Model
import com.ridobiko.ridobikoPartner.models.CustDetailsModel

class CustomerDetails : AppCompatActivity() {
    private  lateinit var binding:ActivityCustomerDetailsBinding
    private  lateinit var CustDetailsList:ArrayList<CustDetailsModel>
    private  lateinit var CustomerDetailsAdapter: CustomerDetailsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityCustomerDetailsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.title="Customer Details"

       CustDetailsList= ArrayList()
        binding.rvCustDetails.layoutManager= LinearLayoutManager(this)

        CustDetailsList.add(CustDetailsModel("Shivani Paswan","01","xxxxxxxx0884","BIMPT3361A","B3123123123","abc@gmail.com","9718004651"))
        CustDetailsList.add(CustDetailsModel("Yash Gupta","02","xxxxxxxx0884","BIMPT3361A","B3123123123","abc@gmail.com","9718004651"))
        CustDetailsList.add(CustDetailsModel("Yash Gupta","03","xxxxxxxx0884","BIMPT3361A","B3123123123","abc@gmail.com","9718004651"))
        CustDetailsList.add(CustDetailsModel("Abhinandhan","04","xxxxxxxx0884","BIMPT3361A","B3123123123","abc@gmail.com","9718004651"))
        CustDetailsList.add(CustDetailsModel("Yash Gupta","05","xxxxxxxx0884","BIMPT3361A","B3123123123","abc@gmail.com","9718004651"))
        CustDetailsList.add(CustDetailsModel("Yash Gupta","06","xxxxxxxx0884","BIMPT3361A","B3123123123","abc@gmail.com","9718004651"))
        CustDetailsList.add(CustDetailsModel("Yash Gupta","07","xxxxxxxx0884","BIMPT3361A","B3123123123","abc@gmail.com","9718004651"))
        CustDetailsList.add(CustDetailsModel("Yash Gupta","08","xxxxxxxx0884","BIMPT3361A","B3123123123","abc@gmail.com","9718004651"))
        CustDetailsList.add(CustDetailsModel("Yash Gupta","01","xxxxxxxx0884","BIMPT3361A","B3123123123","abc@gmail.com","9718004651"))



        CustomerDetailsAdapter= CustomerDetailsAdapter(CustDetailsList)
        binding.rvCustDetails.adapter=CustomerDetailsAdapter

        CustomerDetailsAdapter.onItemClick={
            val intent= Intent(this,SingleCustDetailsActivity::class.java)
            startActivity(intent)
        }


    }
}