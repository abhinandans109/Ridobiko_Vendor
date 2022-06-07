package com.ridobiko.ridobikoPartner.models

import android.os.Parcel
import android.os.Parcelable

data class AddEmp_Model(val emp_name:String,val emp_id:String,val emp_mail:String,val emp_number:String):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(emp_name)
        parcel.writeString(emp_id)
        parcel.writeString(emp_mail)
        parcel.writeString(emp_number)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AddEmp_Model> {
        override fun createFromParcel(parcel: Parcel): AddEmp_Model {
            return AddEmp_Model(parcel)
        }

        override fun newArray(size: Int): Array<AddEmp_Model?> {
            return arrayOfNulls(size)
        }
    }
}
