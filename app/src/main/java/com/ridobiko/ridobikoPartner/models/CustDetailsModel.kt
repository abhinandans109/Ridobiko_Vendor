package com.ridobiko.ridobikoPartner.models

import android.os.Parcel
import android.os.Parcelable

data class CustDetailsModel(val cust_name:String,val cust_s_no:String,val cust_aadhar:String,
                            val cust_pan:String,val cust_license:String,val cust_email:String,val cust_number:String):Parcelable {
    constructor(parcel: Parcel) : this(
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
        parcel.writeString(cust_name)
        parcel.writeString(cust_s_no)
        parcel.writeString(cust_aadhar)
        parcel.writeString(cust_pan)
        parcel.writeString(cust_license)
        parcel.writeString(cust_email)
        parcel.writeString(cust_number)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CustDetailsModel> {
        override fun createFromParcel(parcel: Parcel): CustDetailsModel {
            return CustDetailsModel(parcel)
        }

        override fun newArray(size: Int): Array<CustDetailsModel?> {
            return arrayOfNulls(size)
        }
    }
}
