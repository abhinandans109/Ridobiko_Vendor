package com.ridobiko.ridobikoPartner.models

import android.os.Parcel
import android.os.Parcelable

data class TripHistoryModel(val s_no:String,val trip_status:String,val cust_name:String,val cust_num:String,val paid_amt:String,val remain_amt:String,
                              val tot_amt:String, val pickup_time:String,val drop_time:String): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(s_no)
        parcel.writeString(trip_status)
        parcel.writeString(cust_name)
        parcel.writeString(cust_num)
        parcel.writeString(paid_amt)
        parcel.writeString(remain_amt)
        parcel.writeString(tot_amt)
        parcel.writeString(pickup_time)
        parcel.writeString(drop_time)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TripHistoryModel> {
        override fun createFromParcel(parcel: Parcel): TripHistoryModel {
            return TripHistoryModel(parcel)
        }

        override fun newArray(size: Int): Array<TripHistoryModel?> {
            return arrayOfNulls(size)
        }
    }

}
