package com.ridobiko.ridobikoPartner.adapters

import android.accounts.Account
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.models.AccountModel
import com.ridobiko.ridobikoPartner.models.Support_Model

class AccountAdapter (private  val  accountList: ArrayList<AccountModel>): RecyclerView.Adapter<AccountAdapter.AccountViewHolder>() {

class AccountViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
    val cust_name: EditText =itemView.findViewById(R.id.et_name)
    val cust_add: EditText =itemView.findViewById(R.id.et_address)
    val cust_per: EditText =itemView.findViewById(R.id.et_perAdd)
    val cust_num: EditText =itemView.findViewById(R.id.et_num)
    val emergency: EditText =itemView.findViewById(R.id.et_emergency)
    val btn_mobile: ImageView =itemView.findViewById(R.id.btn_mobile)
    val btn_emer: ImageView=itemView.findViewById(R.id.btn_emergency)


}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.account_items, parent, false)
        return AccountAdapter.AccountViewHolder(view)
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        val Account_Model=accountList[position]
        holder.cust_name.setText(Account_Model.cust_name)
        holder.cust_num.setText(Account_Model.cust_num)
        holder.cust_add.setText(Account_Model.cust_add)
        holder.cust_per.setText(Account_Model.cust_per)
        holder.emergency.setText(Account_Model.emergency)

        holder.btn_mobile.setOnClickListener {  }
        holder.btn_emer.setOnClickListener {  }
    }

    override fun getItemCount(): Int {
        return accountList.size
    }
}