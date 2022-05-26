package com.ridobiko.ridobikoPartner.activities

import android.Manifest
import android.Manifest.permission.*
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.ridobiko.ridobikoPartner.AppVendor
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.adapters.MyBikeAdapter
import com.ridobiko.ridobikoPartner.adapters.SupportAdapter
import com.ridobiko.ridobikoPartner.databinding.ActivitySupportBinding
import com.ridobiko.ridobikoPartner.models.BookingResponseModel
import com.ridobiko.ridobikoPartner.models.MybikeModel
import com.ridobiko.ridobikoPartner.models.Support_Model

class SupportActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySupportBinding
    private  lateinit var supportList: ArrayList<Support_Model>
    private  lateinit var SupportAdapter: SupportAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySupportBinding.inflate(layoutInflater)

        setContentView(binding.root)
        supportActionBar?.title="Support"

//        recyclerView
        supportList=ArrayList()
        binding.rvSupport.layoutManager= LinearLayoutManager(this)
        supportList.add(Support_Model("23-04-22","Suggestion","Portal UI need to be changed","#RB196383","pending"))
        supportList.add(Support_Model("23-04-22","Suggestion","Portal UI need to be changed","#RB196383","pending"))
        supportList.add(Support_Model("23-04-22","Suggestion","Portal UI need to be changed","#RB196383","pending"))
        supportList.add(Support_Model("23-04-22","Suggestion","Portal UI need to be changed","#RB196383","pending"))
        supportList.add(Support_Model("23-04-22","Suggestion","Portal UI need to be changed","#RB196383","pending"))
        supportList.add(Support_Model("23-04-22","Suggestion","Portal UI need to be changed","#RB196383","pending"))
        supportList.add(Support_Model("23-04-22","Suggestion","Portal UI need to be changed","#RB196383","pending"))
        supportList.add(Support_Model("23-04-22","Suggestion","Portal UI need to be changed","#RB196383","pending"))


        SupportAdapter= SupportAdapter(supportList)
        binding.rvSupport.adapter=SupportAdapter


        //call button
        binding.sCallButton.setOnClickListener {
            val intent=Intent(Intent.ACTION_DIAL)
            val number=binding.sNumber.text.toString()
            intent.data = Uri.parse("tel:$number")
            startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }

        //Bottom sheet
        binding.btnNewRequest.setOnClickListener {

            val dialog = BottomSheetDialog(this)
            val view = layoutInflater.inflate(R.layout.support_bottom_sheet, null)
            val btnClose = view.findViewById<Button>(R.id.btn_close)
            val btnSave = view.findViewById<Button>(R.id.btn_save)
            btnClose.setOnClickListener {
                dialog.dismiss()
            }
            btnSave.setOnClickListener {
            dialog.dismiss()
        }

            dialog.setCancelable(false)

            dialog.setContentView(view)

            dialog.show()



        }
    }

}




