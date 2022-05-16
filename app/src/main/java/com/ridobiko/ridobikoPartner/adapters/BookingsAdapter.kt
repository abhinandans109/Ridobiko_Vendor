package com.ridobiko.ridobikoPartner.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ridobiko.ridobikoPartner.AppVendor
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.activities.BookingActivity
import com.ridobiko.ridobikoPartner.models.BookingResponseModel

class BookingsAdapter(var context:Context,var list: ArrayList<BookingResponseModel>) : RecyclerView.Adapter<BookingsAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val orderId: TextView =itemView.findViewById<TextView>(R.id.order_id)
            val dateRange: TextView =itemView.findViewById<TextView>(R.id.date_range)
            val name: TextView =itemView.findViewById<TextView>(R.id.name)
            val status: TextView =itemView.findViewById<TextView>(R.id.status)
            val totalRent: TextView =itemView.findViewById<TextView>(R.id.total_rent)
            val bikeName: TextView =itemView.findViewById<TextView>(R.id.bike_name)
            val pickup: TextView =itemView.findViewById<TextView>(R.id.pickup)
            val drop: TextView =itemView.findViewById<TextView>(R.id.drop)
            val rentPaid: TextView =itemView.findViewById<TextView>(R.id.rent_paid)
            val rentRemaining: TextView =itemView.findViewById<TextView>(R.id.remaining_amount)
            val securityDeposit: TextView =itemView.findViewById<TextView>(R.id.security_deposit)
            val documentUploadStatus: TextView =itemView.findViewById<TextView>(R.id.document_upload_status)
            val documentVerificationStatus: TextView =itemView.findViewById<TextView>(R.id.document_verification_status)

            val showDropDownButton: ImageView =itemView.findViewById<ImageView>(R.id.showdrop_down)
            val dropdown: LinearLayout =itemView.findViewById<LinearLayout>(R.id.more_drop_down)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.booking_item,parent,false))
    }

    override fun getItemViewType(position: Int): Int {

        return position
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val model = list[position]
        holder.orderId.text = "#" + model.trans_id
        holder.dateRange.text =
            model.pickup_date.split(" ")[0] + " - " + model.drop_date.split(" ")[0]
        holder.name.text = model.customer_name.split(" ")[0]
        if (model.admin_status=="pending")
            holder.status.setTextColor(Color.parseColor("#FFCC0000"))
        holder.status.text = model.admin_status
        holder.totalRent.text = "Rs " + model.rent
        holder.bikeName.text = model.bike_name
        if (model.pickup=="Done")
        holder.pickup.setTextColor(Color.parseColor("#4CAF50"))
        if (model.drop=="Done")
        holder.drop.setTextColor(Color.parseColor("#4CAF50"))

        holder.pickup.text = model.pickup

        holder.drop.text = model.drop
        holder.rentPaid.text = "Rs " + model.amount_paid
        holder.rentRemaining.text = "Rs " + model.amount_left
        holder.securityDeposit.text = "Rs " + model.security_deposit
        holder.documentUploadStatus.text = if (!model.image_aadhar_front.isNullOrEmpty()) {
            "Done"
        } else {
            "Not Done"
        }
        holder.documentVerificationStatus.text = if (model.aadhar_verified == "1") {
            "Done"
        } else {
            "Not Done"
        }
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
            AppVendor.selectedBooking = list[position]
            context.startActivity(
                Intent(
                    context,
                    BookingActivity::class.java
                ).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            )
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}