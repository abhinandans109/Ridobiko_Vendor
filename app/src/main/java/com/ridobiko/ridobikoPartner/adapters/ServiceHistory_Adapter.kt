package com.ridobiko.ridobikoPartner.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.models.Service_history_Model

class ServiceHistory_Adapter(private  val  serviceHistoryList: ArrayList<Service_history_Model>):RecyclerView.Adapter<ServiceHistory_Adapter.ServiceHistoryViewHolder>() {

    class ServiceHistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val vehicle_no: TextView =itemView.findViewById(R.id.vehicle_no)
        val status: TextView =itemView.findViewById(R.id.status)
        val main_out: TextView =itemView.findViewById(R.id.main_out)
        val main_in: TextView =itemView.findViewById(R.id.main_in)
        val vehicle_name: TextView =itemView.findViewById(R.id.vehicle_name)
        val created_by: TextView =itemView.findViewById(R.id.created_by)
        val mechanic: TextView =itemView.findViewById(R.id.mechanic_name)
        val main_done: TextView =itemView.findViewById(R.id.main_done)
        val meter_read: TextView =itemView.findViewById(R.id.meter_reading)
        val job_card_id: TextView =itemView.findViewById(R.id.job_card_id)
        val complaints: TextView =itemView.findViewById(R.id.complaints)
        val tot_cost: TextView =itemView.findViewById(R.id.total_cost)
        val spare_cost: TextView =itemView.findViewById(R.id.spare_part_cost)
        val labour: TextView =itemView.findViewById(R.id.labour_cost)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceHistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.service_history_items, parent, false)
        return ServiceHistory_Adapter.ServiceHistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: ServiceHistoryViewHolder, position: Int) {
        val ServiceHistory_Model=serviceHistoryList[position]
        holder.vehicle_no.text=ServiceHistory_Model.vehicle_no
        holder.status.text=ServiceHistory_Model.status
        holder.main_in.text=ServiceHistory_Model.main_in
        holder.main_out.text=ServiceHistory_Model.main_out
        holder.vehicle_name.text=ServiceHistory_Model.vehicle_name
        holder.created_by.text=ServiceHistory_Model.created_by
        holder.mechanic.text=ServiceHistory_Model.mechanic
        holder.main_done.text=ServiceHistory_Model.main_done
        holder.meter_read.text=ServiceHistory_Model.reading
        holder.job_card_id.text=ServiceHistory_Model.card_id
        holder.complaints.text=ServiceHistory_Model.complaints
        holder.tot_cost.text=ServiceHistory_Model.tot_cost
        holder.spare_cost.text=ServiceHistory_Model.spare_part
        holder.labour.text=ServiceHistory_Model.labour

    }

    override fun getItemCount(): Int {
        return serviceHistoryList.size
    }
}