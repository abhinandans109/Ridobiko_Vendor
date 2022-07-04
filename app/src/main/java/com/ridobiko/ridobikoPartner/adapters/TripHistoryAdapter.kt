package com.ridobiko.ridobikoPartner.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.models.Bike_Database_Model
import com.ridobiko.ridobikoPartner.models.TripHistoryModel

class TripHistoryAdapter(private  val TripList:ArrayList<TripHistoryModel>):RecyclerView.Adapter<TripHistoryAdapter.TripHistoryViewHolder>() {
    class TripHistoryViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val s_no: TextView =itemView.findViewById(R.id.s_no)
        val trip_status: TextView =itemView.findViewById(R.id.trip_status)
        val cust_name: TextView =itemView.findViewById(R.id.cust_name)
        val cust_num: TextView =itemView.findViewById(R.id.cust_num)
        val pickup_time: TextView =itemView.findViewById(R.id.pickup_time)
        val drop_time: TextView =itemView.findViewById(R.id.drop_time)
        val paid_amount: TextView =itemView.findViewById(R.id.paid_amount)
        val remain_amt: TextView =itemView.findViewById(R.id.remain_amt)
        val tot_amt: TextView =itemView.findViewById(R.id.tot_amt)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripHistoryViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.trip_history_items,parent,false)
        return TripHistoryAdapter.TripHistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: TripHistoryViewHolder, position: Int) {
        val tripHistoryModel = TripList[position]
        holder.s_no.text = tripHistoryModel.s_no
        holder.trip_status.text = tripHistoryModel.trip_status
        if (tripHistoryModel.trip_status.lowercase()=="cancelled")
            holder.trip_status.setTextColor(Color.parseColor("#FFCC0000"))
        else if(tripHistoryModel.trip_status.lowercase()=="pending")
            holder.trip_status.setTextColor(Color.parseColor("#FAD02C"))
        holder.cust_name.text = tripHistoryModel.cust_name
        holder.cust_num.text = tripHistoryModel.cust_num
        holder.pickup_time.text = tripHistoryModel.pickup_time
        holder.drop_time.text = tripHistoryModel.drop_time
        holder.paid_amount.text = tripHistoryModel.paid_amt
        holder.remain_amt.text = tripHistoryModel.remain_amt
        holder.tot_amt.text = tripHistoryModel.tot_amt
    }

    override fun getItemCount(): Int {
        return TripList.size

    }
}