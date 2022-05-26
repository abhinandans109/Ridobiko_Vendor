package com.ridobiko.ridobikoPartner.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.models.Support_Model

class SupportAdapter(private  val  supportList: ArrayList<Support_Model>):RecyclerView.Adapter<SupportAdapter.SupportViewHolder>() {

    class SupportViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val date: TextView =itemView.findViewById(R.id.s_date)
        val type: TextView =itemView.findViewById(R.id.support_type)
        val id: TextView =itemView.findViewById(R.id.support_id)
        val status: TextView =itemView.findViewById(R.id.support_status)
        val message: TextView =itemView.findViewById(R.id.support_msg)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SupportAdapter.SupportViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.support_items, parent, false)
        return SupportAdapter.SupportViewHolder(view)
    }

    override fun onBindViewHolder(holder: SupportViewHolder, position: Int) {

        val Support_Model=supportList[position]
        holder.date.text=Support_Model.date
        holder.id.text=Support_Model.id
        holder.message.text=Support_Model.message
        holder.status.text=Support_Model.status
        holder.type.text=Support_Model.type
    }

    override fun getItemCount(): Int {
      return  supportList.size
    }
}