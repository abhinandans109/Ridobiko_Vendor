package com.ridobiko.ridobikoPartner.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.adapters.Bike_Database_Adapter
import com.ridobiko.ridobikoPartner.adapters.MyBikeAdapter
import com.ridobiko.ridobikoPartner.databinding.ActivityBikeDatabaseBinding
import com.ridobiko.ridobikoPartner.models.Bike_Database_Model
import com.ridobiko.ridobikoPartner.models.MybikeModel

class BikeDatabase_Activity : AppCompatActivity() {
    private  lateinit var binding:ActivityBikeDatabaseBinding
    private  lateinit var biksdbList: ArrayList<Bike_Database_Model>
    private  lateinit var Bike_Database_Adapter: Bike_Database_Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityBikeDatabaseBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.title="Bike Database"

        biksdbList= ArrayList()
        binding.rvBikeDB.layoutManager= LinearLayoutManager(this)
        biksdbList.add(Bike_Database_Model("HDHE7G","DJ7JW","Shivani","9718004651","Na","23-04-2022","24-05-2022","25-05-2022","26-05-2022","27-05-2022","28-05-2022",R.drawable.drop_down))
        biksdbList.add(Bike_Database_Model("HDHE7G","DJ7JW","Shivani","9718004651","Na","23-04-2022","24-05-2022","25-05-2022","26-05-2022","27-05-2022","28-05-2022",R.drawable.drop_down))
        biksdbList.add(Bike_Database_Model("HDHE7G","DJ7JW","Shivani","9718004651","Na","23-04-2022","24-05-2022","25-05-2022","26-05-2022","27-05-2022","28-05-2022",R.drawable.drop_down))
        biksdbList.add(Bike_Database_Model("HDHE7G","DJ7JW","Shivani","9718004651","Na","23-04-2022","24-05-2022","25-05-2022","26-05-2022","27-05-2022","28-05-2022",R.drawable.drop_down))
        biksdbList.add(Bike_Database_Model("HDHE7G","DJ7JW","Shivani","9718004651","Na","23-04-2022","24-05-2022","25-05-2022","26-05-2022","27-05-2022","28-05-2022",R.drawable.drop_down))
        biksdbList.add(Bike_Database_Model("HDHE7G","DJ7JW","Shivani","9718004651","Na","23-04-2022","24-05-2022","25-05-2022","26-05-2022","27-05-2022","28-05-2022",R.drawable.drop_down))
        biksdbList.add(Bike_Database_Model("HDHE7G","DJ7JW","Shivani","9718004651","Na","23-04-2022","24-05-2022","25-05-2022","26-05-2022","27-05-2022","28-05-2022",R.drawable.drop_down))
        biksdbList.add(Bike_Database_Model("HDHE7G","DJ7JW","Shivani","9718004651","Na","23-04-2022","24-05-2022","25-05-2022","26-05-2022","27-05-2022","28-05-2022",R.drawable.drop_down))

        Bike_Database_Adapter= Bike_Database_Adapter(biksdbList)
        binding.rvBikeDB.adapter=Bike_Database_Adapter
    }
}