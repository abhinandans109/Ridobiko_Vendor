package com.ridobiko.ridobikoPartner.adapters

import android.content.Context
import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ridobiko.ridobikoPartner.fragments.All_Fragment
import com.ridobiko.ridobikoPartner.fragments.TodaysDrop
import com.ridobiko.ridobikoPartner.fragments.TodaysPickup_Fragment
import com.ridobiko.ridobikoPartner.fragments.UpcomingBookings


class MainBooking_FragmentAdapter(private val myContext: Context, fm: FragmentManager, internal var totalTabs: Int) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
       return totalTabs
    }

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
               return TodaysPickup_Fragment()
            }
            1 -> {
                return TodaysDrop()
            }
            2 -> {

                return UpcomingBookings()
            }
            3->{
                return  All_Fragment()
            }
            else -> return Fragment()

        }
    }


}