package com.ridobiko.ridobikoPartner.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.adapters.AddEmployee_Adapter
import com.ridobiko.ridobikoPartner.adapters.MyBikeAdapter
import com.ridobiko.ridobikoPartner.databinding.ActivityAddEmployeeBinding
import com.ridobiko.ridobikoPartner.models.AddEmp_Model
import com.ridobiko.ridobikoPartner.models.MybikeModel

class AddEmployeeActivity : AppCompatActivity() {
    private  lateinit var binding:ActivityAddEmployeeBinding
    private  lateinit var AddEmpList:ArrayList<AddEmp_Model>
    private  lateinit var AddEmployee_Adapter:AddEmployee_Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddEmployeeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title="Add Employees"
        AddEmpList= ArrayList()
        binding.rvAddEmp.layoutManager= LinearLayoutManager(this)
        AddEmpList.add(AddEmp_Model("Yash Gupta","01","abc123@gmail.com","9718004651"))
        AddEmpList.add(AddEmp_Model("Gagan Chauhan","02","abc123@gmail.com","9718004651"))
        AddEmpList.add(AddEmp_Model("Ashu Goutam","03","abc123@gmail.com","9718004651"))
        AddEmpList.add(AddEmp_Model("Shahid ali","04","abc123@gmail.com","9718004651"))
        AddEmpList.add(AddEmp_Model("Yash Gupta","05","abc123@gmail.com","9718004651"))
        AddEmpList.add(AddEmp_Model("Yash Gupta","06","abc123@gmail.com","9718004651"))
        AddEmpList.add(AddEmp_Model("Yash Gupta","07","abc123@gmail.com","9718004651"))
        AddEmpList.add(AddEmp_Model("Yash Gupta","08","abc123@gmail.com","9718004651"))
        AddEmpList.add(AddEmp_Model("Yash Gupta","09","abc123@gmail.com","9718004651"))



        AddEmployee_Adapter= AddEmployee_Adapter(AddEmpList)
        binding.rvAddEmp.adapter=AddEmployee_Adapter

    }
}