package com.ridobiko.ridobikoPartner.models

import android.os.Parcel
import android.os.Parcelable

data class AccountModel(val cust_name:String,val cust_num:String,val cust_add:String,val cust_per:String,val emergency:String,val btn_call:Int,val btn_emergency:Int):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(cust_name)
        parcel.writeString(cust_num)
        parcel.writeString(cust_add)
        parcel.writeString(cust_per)
        parcel.writeString(emergency)
        parcel.writeInt(btn_call)
        parcel.writeInt(btn_emergency)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AccountModel> {
        override fun createFromParcel(parcel: Parcel): AccountModel {
            return AccountModel(parcel)
        }

        override fun newArray(size: Int): Array<AccountModel?> {
            return arrayOfNulls(size)
        }
    }
}
