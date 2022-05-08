package com.ridobiko.ridobikoPartner.activities.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.fragments.BookingCustomer
import com.ridobiko.ridobikoPartner.fragments.Drop
import com.ridobiko.ridobikoPartner.fragments.Other
import com.ridobiko.ridobikoPartner.fragments.Pickup

private val TAB_TITLES = arrayOf(
    R.string.booking,
    R.string.pickup,
    R.string.drop,
    R.string.Other
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        if(position==0) return BookingCustomer()
        else if(position==1) return Pickup()
        else if(position==2) return Drop()
        else return Other()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 4
    }
}