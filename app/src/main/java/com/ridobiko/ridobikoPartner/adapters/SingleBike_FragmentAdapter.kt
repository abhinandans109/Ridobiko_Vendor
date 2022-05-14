package com.ridobiko.ridobikoPartner.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ridobiko.ridobikoPartner.fragments.*

class SingleBike_FragmentAdapter  (private val myContext: Context, fm: FragmentManager, internal var totalTabs: Int) : FragmentPagerAdapter(fm){
    override fun getCount(): Int {
        return totalTabs
    }

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return DashBoard()
            }
            1 -> {
                return Documents()
            }
            2 -> {

                return ServiceHistory()
            }
            3->{
                return  TripHistory()
            }
            4->{
                return Setting()
            }
            else -> return Fragment()

        }
    }
    }
