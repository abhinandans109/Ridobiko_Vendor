package com.ridobiko.ridobikoPartner.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.adapters.PaymentTrackerAdapter
import com.ridobiko.ridobikoPartner.databinding.ActivityPaymentTrackerBinding
import com.ridobiko.ridobikoPartner.models.Payment_Tracker_Model

class PaymentTracker_Activity : AppCompatActivity() {
    private  lateinit var binding:ActivityPaymentTrackerBinding
    private  lateinit var paymentList:ArrayList<Payment_Tracker_Model>
    private  lateinit var PaymentTrackerAdapter: PaymentTrackerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityPaymentTrackerBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.title="Payment Tracker"
        paymentList= ArrayList()


        binding.rvPayment.layoutManager= LinearLayoutManager(this)
        paymentList.add(Payment_Tracker_Model("Activa 6g","#20932809425","Shivani","9718004651","Confirmed","Rs 2,000","HDUS8E6","2 June’22 3 PM – 7 June’22 3 PM",R.drawable.call_button,R.drawable.drop_down,"Rs 18,00","28-05-2022","Rs 18,00","Rs 18,00","Pending"))
        paymentList.add(Payment_Tracker_Model("Activa 6g","#20932809425","Shivani","9718004651","Confirmed","Rs 2,000","HDUS8E6","2 June’22 3 PM – 7 June’22 3 PM",R.drawable.call_button,R.drawable.drop_down,"Rs 18,00","28-05-2022","Rs 18,00","Rs 18,00","Pending"))
        paymentList.add(Payment_Tracker_Model("Activa 6g","#20932809425","Shivani","9718004651","Confirmed","Rs 2,000","HDUS8E6","2 June’22 3 PM – 7 June’22 3 PM",R.drawable.call_button,R.drawable.drop_down,"Rs 18,00","28-05-2022","Rs 18,00","Rs 18,00","Pending"))
        paymentList.add(Payment_Tracker_Model("Activa 6g","#20932809425","Shivani","9718004651","Confirmed","Rs 2,000","HDUS8E6","2 June’22 3 PM – 7 June’22 3 PM",R.drawable.call_button,R.drawable.drop_down,"Rs 18,00","28-05-2022","Rs 18,00","Rs 18,00","Pending"))
        paymentList.add(Payment_Tracker_Model("Activa 6g","#20932809425","Shivani","9718004651","Confirmed","Rs 2,000","HDUS8E6","2 June’22 3 PM – 7 June’22 3 PM",R.drawable.call_button,R.drawable.drop_down,"Rs 18,00","28-05-2022","Rs 18,00","Rs 18,00","Pending"))
        paymentList.add(Payment_Tracker_Model("Activa 6g","#20932809425","Shivani","9718004651","Confirmed","Rs 2,000","HDUS8E6","2 June’22 3 PM – 7 June’22 3 PM",R.drawable.call_button,R.drawable.drop_down,"Rs 18,00","28-05-2022","Rs 18,00","Rs 18,00","Pending"))
        paymentList.add(Payment_Tracker_Model("Activa 6g","#20932809425","Shivani","9718004651","Confirmed","Rs 2,000","HDUS8E6","2 June’22 3 PM – 7 June’22 3 PM",R.drawable.call_button,R.drawable.drop_down,"Rs 18,00","28-05-2022","Rs 18,00","Rs 18,00","Pending"))
        paymentList.add(Payment_Tracker_Model("Activa 6g","#20932809425","Shivani","9718004651","Confirmed","Rs 2,000","HDUS8E6","2 June’22 3 PM – 7 June’22 3 PM",R.drawable.call_button,R.drawable.drop_down,"Rs 18,00","28-05-2022","Rs 18,00","Rs 18,00","Pending"))


        PaymentTrackerAdapter= PaymentTrackerAdapter(paymentList)
        binding.rvPayment.adapter=PaymentTrackerAdapter
    }
}