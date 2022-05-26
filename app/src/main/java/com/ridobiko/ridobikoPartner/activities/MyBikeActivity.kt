package com.ridobiko.ridobikoPartner.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.adapters.MyBikeAdapter
import com.ridobiko.ridobikoPartner.databinding.ActivityMyBikeBinding
import com.ridobiko.ridobikoPartner.models.MybikeModel

class MyBikeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMyBikeBinding
//    private  lateinit var recyclerView: RecyclerView
    private  lateinit var biksList: ArrayList<MybikeModel>
    private  lateinit var myBikeAdapter: MyBikeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityMyBikeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.title="My Bike"
        biksList= ArrayList()

   binding.rvMyBike.layoutManager=LinearLayoutManager(this)
        biksList.add(MybikeModel(R.drawable.my_bikes,"Bike Name","Bike Id"))
        biksList.add(MybikeModel(R.drawable.my_bikes,"Bike Name","Bike Id"))
        biksList.add(MybikeModel(R.drawable.my_bikes,"Bike Name","Bike Id"))
        biksList.add(MybikeModel(R.drawable.my_bikes,"Bike Name","Bike Id"))
        biksList.add(MybikeModel(R.drawable.my_bikes,"Bike Name","Bike Id"))
        biksList.add(MybikeModel(R.drawable.my_bikes,"Bike Name","Bike Id"))
        biksList.add(MybikeModel(R.drawable.my_bikes,"Bike Name","Bike Id"))
        biksList.add(MybikeModel(R.drawable.my_bikes,"Bike Name","Bike Id"))
        biksList.add(MybikeModel(R.drawable.my_bikes,"Bike Name","Bike Id"))
        biksList.add(MybikeModel(R.drawable.my_bikes,"Bike Name","Bike Id"))
        biksList.add(MybikeModel(R.drawable.my_bikes,"Bike Name","Bike Id"))

        myBikeAdapter= MyBikeAdapter(biksList)
        binding.rvMyBike.adapter=myBikeAdapter

        myBikeAdapter.onItemClick={
            val intent=Intent(this,SingleBikeActivity::class.java)
            startActivity(intent)
        }
    }

}