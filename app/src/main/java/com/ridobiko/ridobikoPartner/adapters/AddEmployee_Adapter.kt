package com.ridobiko.ridobikoPartner.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.models.AddEmp_Model
import com.ridobiko.ridobikoPartner.models.Service_history_Model

class AddEmployee_Adapter(private  val  AddEmpList: ArrayList<AddEmp_Model>):RecyclerView.Adapter<AddEmployee_Adapter.AddEmpViewHolder>() {

    class AddEmpViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val emp_name: TextView =itemView.findViewById(R.id.emp_name)
        val emp_id: TextView =itemView.findViewById(R.id.emp_id)
        val emp_mail: TextView =itemView.findViewById(R.id.emp_mail)
        val emp_number: TextView =itemView.findViewById(R.id.emp_number)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddEmpViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.show_employee_items, parent, false)
        return AddEmployee_Adapter.AddEmpViewHolder(view)
    }

    override fun onBindViewHolder(holder: AddEmpViewHolder, position: Int) {
        val AddEmp_Model=AddEmpList[position]
        holder.emp_name.text=AddEmp_Model.emp_name
        holder.emp_id.text=AddEmp_Model.emp_id
        holder.emp_mail.text=AddEmp_Model.emp_mail
        holder.emp_number.text=AddEmp_Model.emp_number


    }

    override fun getItemCount(): Int {
        return AddEmpList.size
    }
}