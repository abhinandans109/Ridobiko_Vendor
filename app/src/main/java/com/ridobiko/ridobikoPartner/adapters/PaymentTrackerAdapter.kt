package com.ridobiko.ridobikoPartner.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ridobiko.ridobikoPartner.R

import com.ridobiko.ridobikoPartner.models.Payment_Tracker_Model

class PaymentTrackerAdapter(private val  paymentList: ArrayList<Payment_Tracker_Model>):RecyclerView.Adapter<PaymentTrackerAdapter.PaymentViewHolder>() {


    class PaymentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val orderid: TextView =itemView.findViewById(R.id.order_id)
        val bikestatus: TextView =itemView.findViewById(R.id.bike_status)
        val payment_status: TextView =itemView.findViewById(R.id.status_payment)
        val date: TextView =itemView.findViewById(R.id.date_range)
        val cust_name: TextView =itemView.findViewById(R.id.name)
        val cust_no: TextView =itemView.findViewById(R.id.number)
        val vehicleid: TextView =itemView.findViewById(R.id.vehicle_id)
        val paid_online: TextView =itemView.findViewById(R.id.paid_online)
        val paid_store: TextView =itemView.findViewById(R.id.paid_online)
        val ridobiko: TextView =itemView.findViewById(R.id.ridobiko_charges)
        val processed: TextView =itemView.findViewById(R.id.processed_on)
        val bike_name: TextView =itemView.findViewById(R.id.bike_name)
        val total: TextView =itemView.findViewById(R.id.total_rent)
        val moreLayout: LinearLayout =itemView.findViewById(R.id.more_drop_down)
        val dropdown: ImageView =itemView.findViewById(R.id.show_drop_down)
        val btn_call: ImageView =itemView.findViewById(R.id.call_button)




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.payment_tracker_items,parent,false)
        return PaymentViewHolder(view)
    }

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        val Payment_tracker_model = paymentList[position]

        holder.bike_name.text = Payment_tracker_model.bike_name
        holder.bikestatus.text = Payment_tracker_model.status
        holder.payment_status.text = Payment_tracker_model.payment_status
        holder.orderid.text = Payment_tracker_model.order_id
        holder.date.text = Payment_tracker_model.date
        holder.cust_name.text = Payment_tracker_model.cust_name
        holder.cust_no.text = Payment_tracker_model.cust_number
        holder.ridobiko.text = Payment_tracker_model.ridobiko_charges
        holder.processed.text = Payment_tracker_model.processed_on
        holder.vehicleid.text = Payment_tracker_model.vehicle_id
        holder.paid_online.text = Payment_tracker_model.paid_online
        holder.paid_store.text = Payment_tracker_model.paid_store
        holder.total.text = Payment_tracker_model.tot_rent


        //drop down button
        holder.dropdown.tag = R.drawable.drop_down
        holder.dropdown.setOnClickListener {
            if (holder.dropdown.tag == R.drawable.drop_down) {
                holder.moreLayout.visibility = View.VISIBLE
                holder.dropdown.setImageResource(R.drawable.drop_up)
                holder.dropdown.tag = R.drawable.drop_up

            } else {
                holder.moreLayout.visibility = View.GONE
                holder.dropdown.setImageResource(R.drawable.drop_down)
                holder.dropdown.tag = R.drawable.drop_down
            }
        }

    }

    override fun getItemCount(): Int {
        return paymentList.size
    }
    }


