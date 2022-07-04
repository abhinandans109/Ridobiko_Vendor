package com.ridobiko.ridobikoPartner.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.api.API
import com.ridobiko.ridobikoPartner.databinding.ActivityTermsConditonsBinding

class TermsConditonsActivity : AppCompatActivity() {
    private  lateinit var binding:ActivityTermsConditonsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityTermsConditonsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.updateTc.setOnClickListener {

        }

    }
}