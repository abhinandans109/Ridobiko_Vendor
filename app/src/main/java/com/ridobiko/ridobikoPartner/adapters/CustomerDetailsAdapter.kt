package com.ridobiko.ridobikoPartner.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.ridobiko.ridobikoPartner.AppVendor
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.activities.SingleCustDetailsActivity
import com.ridobiko.ridobikoPartner.models.CustomerDetailsResponseModel

class CustomerDetailsAdapter(private val context: Context,private  val  CustDetailsList: ArrayList<CustomerDetailsResponseModel>):RecyclerView.Adapter<CustomerDetailsAdapter.CustDwtailsViewHolder>() {

    class CustDwtailsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val cust_name: TextView =itemView.findViewById(R.id.cust_name)
        val cust_s_no: TextView =itemView.findViewById(R.id.cust_s_no)
        val cust_aadhar: TextView =itemView.findViewById(R.id.cust_aadhar)
        val cust_pan: TextView =itemView.findViewById(R.id.cust_pan)
        val cust_license: TextView =itemView.findViewById(R.id.cust_license)
        val cust_mail: TextView =itemView.findViewById(R.id.cust_mail)
        val cust_number: TextView =itemView.findViewById(R.id.cust_number)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustDwtailsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.customer_items, parent, false)
        return CustomerDetailsAdapter.CustDwtailsViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustDwtailsViewHolder, position: Int) {
        val custDetailsModel=CustDetailsList[position]
        holder.cust_name.text=custDetailsModel.name
        holder.cust_s_no.text= (position+1).toString()
        holder.cust_aadhar.text=custDetailsModel.aadhar_id
        holder.cust_pan.text=custDetailsModel.pan_no
        holder.cust_license.text=custDetailsModel.driving_id
        holder.cust_mail.text=custDetailsModel.email
        holder.cust_number.text=custDetailsModel.login_mobile

        if(custDetailsModel.aadhar_verified=="1"){
            holder.cust_aadhar.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.green_tick,0)
        }

        if(custDetailsModel.pan_verified=="1"){
            holder.cust_pan.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.green_tick,0)
        }

        if(custDetailsModel.driving_license_verified=="1"){
            holder.cust_license.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.green_tick,0)
        }
        holder.itemView.setOnClickListener {
            AppVendor.selectedCustomer=custDetailsModel
            val intent= Intent(context, SingleCustDetailsActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
       return CustDetailsList.size
    }
}