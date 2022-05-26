package com.ridobiko.ridobikoPartner.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.models.MybikeModel

class MyBikeAdapter(private val bikeList: ArrayList<MybikeModel>)
    :RecyclerView.Adapter<MyBikeAdapter.BikesViewHolder>(){

       var onItemClick:((MybikeModel)->Unit)?= null

    class BikesViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val imageView:ImageView=itemView.findViewById(R.id.bike_Image)
        val textView:TextView=itemView.findViewById(R.id.bike_Name)
        val textView_Id:TextView=itemView.findViewById(R.id.bike_Id)
        val showDropDownButton: ImageView =itemView.findViewById(R.id.drop_down_Button)
        val dropdown: LinearLayout =itemView.findViewById(R.id.drop_down_details)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BikesViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.mybike_item,parent,false)
        return BikesViewHolder(view)
    }

    override fun onBindViewHolder(holder: BikesViewHolder, position: Int) {
       val mybikeModel=bikeList[position]
        holder.imageView.setImageResource(mybikeModel.image)
        holder.textView.text=mybikeModel.name
        holder.textView_Id.text=mybikeModel.id

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
            onItemClick?.invoke(mybikeModel)
        }
    }

    override fun getItemCount(): Int {
       return bikeList.size
    }

}