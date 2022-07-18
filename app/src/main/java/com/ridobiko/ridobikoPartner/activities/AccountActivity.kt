package com.ridobiko.ridobikoPartner.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.adapters.AccountAdapter
import com.ridobiko.ridobikoPartner.adapters.SupportAdapter
import com.ridobiko.ridobikoPartner.databinding.ActivityAccountBinding
import com.ridobiko.ridobikoPartner.databinding.ActivitySupportBinding
import com.ridobiko.ridobikoPartner.models.AccountModel
import com.ridobiko.ridobikoPartner.models.Support_Model

class AccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAccountBinding
    private  lateinit var accountList: ArrayList<AccountModel>
    private  lateinit var AccountAdapter: AccountAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAccountBinding.inflate(layoutInflater)
        supportActionBar?.title="Account"

        setContentView(binding.root)

        accountList=ArrayList()
        binding.rvAccount.layoutManager= LinearLayoutManager(this)
        accountList.add(AccountModel("Shivani","9718004651","Uttam Nagar","Uttam Nagar","8076889036",R.drawable.call_button,R.drawable.call_button))
        accountList.add(AccountModel("Shivani","9718004651","Uttam Nagar","Uttam Nagar","8076889036",R.drawable.call_button,R.drawable.call_button))
        accountList.add(AccountModel("Shivani","9718004651","Uttam Nagar","Uttam Nagar","8076889036",R.drawable.call_button,R.drawable.call_button))
        accountList.add(AccountModel("Shivani","9718004651","Uttam Nagar","Uttam Nagar","8076889036",R.drawable.call_button,R.drawable.call_button))
        accountList.add(AccountModel("Shivani","9718004651","Uttam Nagar","Uttam Nagar","8076889036",R.drawable.call_button,R.drawable.call_button))
        accountList.add(AccountModel("Shivani","9718004651","Uttam Nagar","Uttam Nagar","8076889036",R.drawable.call_button,R.drawable.call_button))
        accountList.add(AccountModel("Shivani","9718004651","Uttam Nagar","Uttam Nagar","8076889036",R.drawable.call_button,R.drawable.call_button))
        accountList.add(AccountModel("Shivani","9718004651","Uttam Nagar","Uttam Nagar","8076889036",R.drawable.call_button,R.drawable.call_button))

        AccountAdapter= AccountAdapter(accountList)
         binding.rvAccount.adapter=AccountAdapter

    }
}