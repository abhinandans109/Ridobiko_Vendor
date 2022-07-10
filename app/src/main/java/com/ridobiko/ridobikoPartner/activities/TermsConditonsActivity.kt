package com.ridobiko.ridobikoPartner.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ridobiko.ridobikoPartner.AppVendor
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.api.API
import com.ridobiko.ridobikoPartner.constants.Constants
import com.ridobiko.ridobikoPartner.databinding.ActivityTermsConditonsBinding
import com.ridobiko.ridobikoPartner.databinding.FragmentCustomProfileBinding
import com.ridobiko.ridobikoPartner.models.ChangeStatusResponseModel
import com.ridobiko.ridobikoPartner.models.MyBikesResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        binding.updateTc.setOnClickListener{
            var data="";
            if(binding.c200.isChecked)data+=binding.t200.text.toString()+"|"
            if(binding.c2000.isChecked)data+=binding.t2000.text.toString()+"|"
            if(binding.c3000.isChecked)data+=binding.t3000.text.toString()+"|"
            if(binding.c400.isChecked)data+=binding.t4000.text.toString()+"|"
            if(binding.cAbility.isChecked)data+=binding.ability.text.toString()+"|"
            if(binding.cDamages.isChecked)data+=binding.damages.text.toString()+"|"
            if(binding.cExtraHelmet.isChecked)data+=binding.extraHelmet.text.toString()+"|"
            if(binding.cFuelCharges.isChecked)data+=binding.fuelCharges.text.toString()+"|"
            if(binding.cPan.isChecked)data+=binding.pan.text.toString()+"|"
            if(binding.cRightToCancel.isChecked)data+=binding.rightToCance.text.toString()+"|"
            if(binding.cZero.isChecked)data+=binding.zero.text.toString()+"|"

            API.get().setTandC(data,selectedMyBike.bike_id).enqueue(object:Callback<ChangeStatusResponseModel>{
                override fun onResponse(
                    call: Call<ChangeStatusResponseModel>,
                    response: Response<ChangeStatusResponseModel>
                ) {
                    if(response.isSuccessful){
                        if(response.body()?.success==Constants.SUCCESS){
                            Toast.makeText(applicationContext.applicationContext,"Terms and conditions updated",Toast.LENGTH_SHORT).show()
                            startActivity(Intent(applicationContext,MyBikeActivity::class.java))
                            finish()
                        }
                    }
                }

                override fun onFailure(call: Call<ChangeStatusResponseModel>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }



    }
}