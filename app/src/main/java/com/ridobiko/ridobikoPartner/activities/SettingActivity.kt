package com.ridobiko.ridobikoPartner.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.databinding.ActivityAddEmployeeBinding
import com.ridobiko.ridobikoPartner.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {
    private  lateinit var binding: ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivitySettingBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        supportActionBar?.title="Settings"
       binding.btShowEmp.setOnClickListener{
           startActivity(Intent(applicationContext,AddEmployeeActivity::class.java))
       }
    }
}