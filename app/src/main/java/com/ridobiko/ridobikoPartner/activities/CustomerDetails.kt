package com.ridobiko.ridobikoPartner.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.adapters.CustomerDetailsAdapter
import com.ridobiko.ridobikoPartner.api.API
import com.ridobiko.ridobikoPartner.constants.Constants
import com.ridobiko.ridobikoPartner.databinding.ActivityCustomerDetailsBinding
import com.ridobiko.ridobikoPartner.models.ApiResponseModel
import com.ridobiko.ridobikoPartner.models.CustomerDetailsResponseModel
import com.ridobiko.ridobikoPartner.models.MyBikesResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CustomerDetails : AppCompatActivity() {
    private  lateinit var binding:ActivityCustomerDetailsBinding
    private  lateinit var CustDetailsList:ArrayList<CustomerDetailsResponseModel>
    private  lateinit var CustomerDetailsAdapter: CustomerDetailsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityCustomerDetailsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.title="Customer Details"

       CustDetailsList= ArrayList()
        binding.rvCustDetails.layoutManager= LinearLayoutManager(this)
        API.get().getCustDetails(getSharedPreferences(Constants.PREFS_LOGIN_DETAILS, MODE_PRIVATE).getString(Constants.EMAIL,"null")).enqueue(object :Callback<ApiResponseModel<ArrayList<CustomerDetailsResponseModel>>>{
            override fun onResponse(
                call: Call<ApiResponseModel<ArrayList<CustomerDetailsResponseModel>>>,
                response: Response<ApiResponseModel<ArrayList<CustomerDetailsResponseModel>>>
            ) {
                binding.pb.visibility= View.GONE

                if(response.isSuccessful){
                    if(response.body()?.success==Constants.SUCCESS){
                        CustDetailsList= response.body()?.data!!
                        CustomerDetailsAdapter= CustomerDetailsAdapter(applicationContext,CustDetailsList)
                        binding.rvCustDetails.layoutManager=LinearLayoutManager(applicationContext)
                        binding.rvCustDetails.adapter=CustomerDetailsAdapter
                    }
                }
            }

            override fun onFailure(
                call: Call<ApiResponseModel<ArrayList<CustomerDetailsResponseModel>>>,
                t: Throwable
            ) {
                binding.pb.visibility= View.GONE
            }

        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.new_menu,menu)
        val search = menu!!.findItem(R.id.action_search)
        val searchView = search.actionView as SearchView
        searchView.isFocusable=true
        searchView.queryHint="Search"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                var flist=ArrayList<CustomerDetailsResponseModel>();
                for(item in CustDetailsList){
                    if (item.name!=null)
                    if(item.name.lowercase().contains(newText.toString().lowercase())){
                        flist.add(item)
                    }
                    if(item.customer_mobile.lowercase().contains(newText.toString().lowercase())){
                        flist.add(item)
                    }
                }
                CustomerDetailsAdapter.filterList(flist)

                return true
            }


        })

        return super.onCreateOptionsMenu(menu)
    }

}