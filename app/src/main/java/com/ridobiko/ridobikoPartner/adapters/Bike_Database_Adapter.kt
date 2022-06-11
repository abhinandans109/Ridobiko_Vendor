package com.ridobiko.ridobikoPartner.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.models.Bike_Database_Model

class Bike_Database_Adapter(private val  bikeDBList: ArrayList<Bike_Database_Model>):RecyclerView.Adapter<Bike_Database_Adapter.BikeDbViewHolder>() {


    class BikeDbViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val bikeid: TextView =itemView.findViewById(R.id.bike_id)
        val orderid: TextView =itemView.findViewById(R.id.order_id)
        val custname: TextView =itemView.findViewById(R.id.cust_name)
        val custnumber: TextView =itemView.findViewById(R.id.cust_number)
        val block_range: TextView =itemView.findViewById(R.id.block_rang)
        val pickdate: TextView =itemView.findViewById(R.id.pickup_date)
        val dropdate: TextView =itemView.findViewById(R.id.drop_date)
        val pucdate: TextView =itemView.findViewById(R.id.puc_date)
        val permitdate: TextView =itemView.findViewById(R.id.permit_date)
        val insurancedate: TextView =itemView.findViewById(R.id.insurance_date)
        val taxdate: TextView =itemView.findViewById(R.id.tax_date)
        val dropdown: ImageView =itemView.findViewById(R.id.btn_drop_down)
        val layout: LinearLayout =itemView.findViewById(R.id.vs_layout )

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BikeDbViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.bike_database_items,parent,false)
        return Bike_Database_Adapter.BikeDbViewHolder(view)
    }

    override fun onBindViewHolder(holder: BikeDbViewHolder, position: Int) {
        val bikeDatabaseModel=bikeDBList[position]

        holder.bikeid.text=bikeDatabaseModel.bike_id
        holder.orderid.text=bikeDatabaseModel.order_id
        holder.custname.text=bikeDatabaseModel.cust_name
        holder.custnumber.text=bikeDatabaseModel.cust_number
        holder.block_range.text=bikeDatabaseModel.block_range
        holder.pickdate.text=bikeDatabaseModel.pick_date
        holder.dropdate.text=bikeDatabaseModel.drop
        holder.permitdate.text=bikeDatabaseModel.permit
        holder.insurancedate.text=bikeDatabaseModel.insurance_date
        holder.taxdate.text=bikeDatabaseModel.tax_date
        holder.pucdate.text=bikeDatabaseModel.puc_date

        //drop down button
        holder.dropdown.tag = R.drawable.drop_down
        holder.dropdown.setOnClickListener {
            if (holder.dropdown.tag == R.drawable.drop_down) {
                holder.layout.visibility = View.VISIBLE
                holder.dropdown.setImageResource(R.drawable.drop_up)
                holder.dropdown.tag = R.drawable.drop_up

            } else {
                holder.layout.visibility = View.GONE
                holder.dropdown.setImageResource(R.drawable.drop_down)
                holder.dropdown.tag = R.drawable.drop_down
            }
        }

    }

    override fun getItemCount(): Int {
       return bikeDBList.size
    }
}