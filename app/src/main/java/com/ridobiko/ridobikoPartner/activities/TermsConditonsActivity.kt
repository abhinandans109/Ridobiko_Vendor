package com.ridobiko.ridobikoPartner.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ridobiko.ridobikoPartner.AppVendor
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.databinding.ActivityTermsConditonsBinding
import com.ridobiko.ridobikoPartner.databinding.FragmentCustomProfileBinding
import com.ridobiko.ridobikoPartner.models.MyBikesResponseModel

class TermsConditonsActivity : AppCompatActivity() {
    lateinit var selectedMyBike:MyBikesResponseModel
    lateinit var binding: ActivityTermsConditonsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityTermsConditonsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        selectedMyBike=AppVendor.selectedMyBike
        val tc=selectedMyBike.tc.split("|")
        for(stc in tc){
            if(stc.contains("PAN Card")) binding.cPan.isChecked=true;
            if(stc.contains("damages")) binding.cDamages.isChecked=true;
            if(stc.contains("extra helmet")) binding.cExtraHelmet.isChecked=true;
            if(stc.contains("Fuel charges")) binding.cFuelCharges.isChecked=true;
            if(stc.contains("satisfactory")) binding.c200.isChecked=true;
            if(stc.contains("ability")) binding.cAbility.isChecked=true;
            if(stc.contains("Zero")) binding.cZero.isChecked=true;
            if(stc.contains("2000")) binding.c2000.isChecked=true;
            if(stc.contains("4000")) binding.c400.isChecked=true;
            if(stc.contains("3000")) binding.c3000.isChecked=true;
        }

    }
}