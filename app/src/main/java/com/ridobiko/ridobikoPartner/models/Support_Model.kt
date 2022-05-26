package com.ridobiko.ridobikoPartner.models

import android.os.Parcel
import android.os.Parcelable

data class Support_Model(val date:String,val type:String,val message:String,val id:String,val status:String): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(date)
        parcel.writeString(type)
        parcel.writeString(message)
        parcel.writeString(id)
        parcel.writeString(status)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Support_Model> {
        override fun createFromParcel(parcel: Parcel): Support_Model {
            return Support_Model(parcel)
        }

        override fun newArray(size: Int): Array<Support_Model?> {
            return arrayOfNulls(size)
        }
    }

}
