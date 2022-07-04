 package com.ridobiko.ridobikoPartner.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.contains
import androidx.recyclerview.widget.LinearLayoutManager
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.adapters.MyBikeAdapter
import com.ridobiko.ridobikoPartner.api.API
import com.ridobiko.ridobikoPartner.constants.Constants
import com.ridobiko.ridobikoPartner.databinding.ActivityMyBikeBinding
import com.ridobiko.ridobikoPartner.models.ApiResponseModel
import com.ridobiko.ridobikoPartner.models.MyBikesResponseModel
import com.ridobiko.ridobikoPartner.models.MybikeModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import java.util.Locale.filter
import kotlin.collections.ArrayList

 class MyBikeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMyBikeBinding
//    private  lateinit var recyclerView: RecyclerView
    private  lateinit var bikeList: ArrayList<MyBikesResponseModel>

    private  lateinit var myBikeAdapter: MyBikeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityMyBikeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.title="My Bike"
        bikeList= ArrayList()

        val sharedPreferences=getSharedPreferences(Constants.PREFS_LOGIN_DETAILS, MODE_PRIVATE)
        val email=sharedPreferences.getString(Constants.EMAIL,"none");
        API.get().getMyBikes(email).enqueue(object : Callback<ApiResponseModel<ArrayList<MyBikesResponseModel>>>{
            override fun onResponse(
                call: Call<ApiResponseModel<ArrayList<MyBikesResponseModel>>>,
                response: Response<ApiResponseModel<ArrayList<MyBikesResponseModel>>>
            ) {
                binding.pb.visibility= View.GONE
                if(response.isSuccessful){
                    if(response.body()?.success==Constants.SUCCESS){
                        bikeList=response.body()?.data!!
                        binding.rvMyBike.layoutManager=LinearLayoutManager(applicationContext)
                        myBikeAdapter= MyBikeAdapter(applicationContext,bikeList)
                        binding.rvMyBike.adapter=myBikeAdapter
                    }
                }
            }

            override fun onFailure(
                call: Call<ApiResponseModel<ArrayList<MyBikesResponseModel>>>,
                t: Throwable
            ) {
                binding.pb.visibility= View.GONE
                print(t.message)
//                TODO("Not yet implemented")
            }

        });





    }

     override fun onCreateOptionsMenu(menu: Menu?): Boolean {
         menuInflater.inflate(R.menu.new_menu,menu)

         val search = menu!!.findItem(R.id.action_search)
         val searchView = search.actionView as SearchView
         searchView.queryHint = "Search"
         searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
             override fun onQueryTextSubmit(query: String?): Boolean {
                 return false
             }
             override fun onQueryTextChange(newText: String?): Boolean {
               MyBikeAdapter.filter.(newText)


                 return true
             }
         })

         return super.onCreateOptionsMenu(menu)
     }
}