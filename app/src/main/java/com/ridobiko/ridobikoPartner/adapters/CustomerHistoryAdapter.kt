package com.ridobiko.ridobikoPartner.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ridobiko.ridobikoPartner.AppVendor
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.activities.SingleCustDetailsActivity
import com.ridobiko.ridobikoPartner.models.CustomerDetailsResponseModel
import com.ridobiko.ridobikoPartner.models.CustomerHistoryResponseModel

class CustomerHistoryAdapter(private val context: Context, private  val  cusHistoryList: ArrayList<CustomerHistoryResponseModel>):RecyclerView.Adapter<CustomerHistoryAdapter.CustDwtailsViewHolder>() {

    class CustDwtailsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val pickup_date: TextView =itemView.findViewById(R.id.pickup_date)
        val drop_date: TextView =itemView.findViewById(R.id.drop_date)
        val booked_on: TextView =itemView.findViewById(R.id.booked_on)
        val rent: TextView =itemView.findViewById(R.id.rent)
        val amount_paid: TextView =itemView.findViewById(R.id.amount_paid)
        val amount_left: TextView =itemView.findViewById(R.id.amount_left)
        val admin_status: TextView =itemView.findViewById(R.id.admin_status)
        val bike_plate: TextView =itemView.findViewById(R.id.bike_id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustDwtailsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cust_history_item, parent, false)
        return CustomerHistoryAdapter.CustDwtailsViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustDwtailsViewHolder, position: Int) {
        val custDetailsModel=cusHistoryList[position]
        holder.pickup_date.text=custDetailsModel.pickup_date
        holder.drop_date.text= custDetailsModel.drop_date
        holder.booked_on.text=custDetailsModel.bookedon
        holder.rent.text=custDetailsModel.rent
        holder.amount_paid.text=custDetailsModel.amount_paid
        holder.amount_left.text=custDetailsModel.amount_left
        holder.admin_status.text=custDetailsModel.admin_status
    }

    override fun getItemCount(): Int {
       return cusHistoryList.size
    }
}