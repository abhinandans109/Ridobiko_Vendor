package com.ridobiko.ridobikoPartner.models

import android.os.Parcel
import android.os.Parcelable

data class Service_history_Model(val vehicle_no:String,val status:String,val main_in:String,val main_out:String,
                                 val vehicle_name:String,val created_by:String,val mechanic:String,val main_done:String,
                                 val card_id:String,val reading:String,val complaints:String,val tot_cost:String,
                                 val spare_part:String,val labour:String,):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
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

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<Service_history_Model> {
        override fun createFromParcel(parcel: Parcel): Service_history_Model {
            return Service_history_Model(parcel)
        }

        override fun newArray(size: Int): Array<Service_history_Model?> {
            return arrayOfNulls(size)
        }
    }
}
