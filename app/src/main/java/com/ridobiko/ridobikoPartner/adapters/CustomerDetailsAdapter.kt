package com.ridobiko.ridobikoPartner.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.models.AddEmp_Model
import com.ridobiko.ridobikoPartner.models.CustDetailsModel
import com.ridobiko.ridobikoPartner.models.MybikeModel

class CustomerDetailsAdapter(private  val  CustDetailsList: ArrayList<CustDetailsModel>):RecyclerView.Adapter<CustomerDetailsAdapter.CustDwtailsViewHolder>() {
    var onItemClick:((CustDetailsModel)->Unit)?= null

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
        val CustDetailsModel=CustDetailsList[position]
        holder.cust_name.text=CustDetailsModel.cust_name
        holder.cust_s_no.text=CustDetailsModel.cust_s_no
        holder.cust_aadhar.text=CustDetailsModel.cust_aadhar
        holder.cust_pan.text=CustDetailsModel.cust_pan
        holder.cust_license.text=CustDetailsModel.cust_license
        holder.cust_mail.text=CustDetailsModel.cust_email
        holder.cust_number.text=CustDetailsModel.cust_number

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(CustDetailsModel)
        }
    }

    override fun getItemCount(): Int {
       return CustDetailsList.size
    }
}