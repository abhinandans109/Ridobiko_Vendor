package com.ridobiko.ridobikoPartner.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ridobiko.ridobikoPartner.AppVendor
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.activities.SingleBikeActivity
import com.ridobiko.ridobikoPartner.models.MyBikesResponseModel
import com.squareup.picasso.Picasso

class MyBikeAdapter(val context: Context, private val bikeList: ArrayList<MyBikesResponseModel>)
    :RecyclerView.Adapter<MyBikeAdapter.BikesViewHolder>(){
    class BikesViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val bike_image:ImageView=itemView.findViewById(R.id.bike_Image)
        val bike_name:TextView=itemView.findViewById(R.id.bike_Name)
        val bike_id:TextView=itemView.findViewById(R.id.bike_Id)
        val insurance_expiry:TextView=itemView.findViewById(R.id.insurance_expiry)
        val pollution_expiry:TextView=itemView.findViewById(R.id.pollution_expiry)
        val permit_expiry:TextView=itemView.findViewById(R.id.permit_expiry)
        val fitness_expiry:TextView=itemView.findViewById(R.id.fitness_expiry)

        val showDropDownButton: ImageView =itemView.findViewById(R.id.drop_down_Button)
        val dropdown: LinearLayout =itemView.findViewById(R.id.drop_down_details)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BikesViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.mybike_item,parent,false)
        return BikesViewHolder(view)
    }

    override fun onBindViewHolder(holder: BikesViewHolder, position: Int) {
       val mybikeModel=bikeList[position]
        Picasso.get().load(mybikeModel.bike_image).placeholder(R.drawable.bike_placeholder).into(holder.bike_image)
        holder.bike_name.text=mybikeModel.bike_name
        holder.bike_id.text=mybikeModel.bike_id
        holder.pollution_expiry.text=mybikeModel.puc_expiry_date
        holder.permit_expiry.text=mybikeModel.permit_expiry_date
        holder.insurance_expiry.text=mybikeModel.insurance_expiry_date
        holder.fitness_expiry.text=mybikeModel.fitness_renewal_date

        //drop down button
        holder.showDropDownButton.tag = R.drawable.drop_down
        holder.showDropDownButton.setOnClickListener {
            if (holder.showDropDownButton.tag == R.drawable.drop_down) {
                holder.dropdown.visibility = View.VISIBLE
                holder.showDropDownButton.setImageResource(R.drawable.drop_up)
                holder.showDropDownButton.tag = R.drawable.drop_up

            } else {
                holder.dropdown.visibility = View.GONE
                holder.showDropDownButton.setImageResource(R.drawable.drop_down)
                holder.showDropDownButton.tag = R.drawable.drop_down
            }
        }


        holder.itemView.setOnClickListener {
            AppVendor.selectedMyBike=mybikeModel
            context.startActivity(Intent(context,SingleBikeActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        }
    }

    override fun getItemCount(): Int {
       return bikeList.size
    }

}