package com.ridobiko.ridobikoPartner.models

import android.os.Parcel
import android.os.Parcelable

data class Payment_Tracker_Model(val bike_name:String, val order_id:String, val cust_name:String, val cust_number:String, val status:String, val tot_rent :String,
                                 val vehicle_id :String, val date:String, val btn_call:Int, val drop_down:Int, val paid_online :String, val processed_on :String, val paid_store :String, val ridobiko_charges :String, val payment_status :String):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(bike_name)
        parcel.writeString(order_id)
        parcel.writeString(cust_name)
        parcel.writeString(cust_number)
        parcel.writeString(status)
        parcel.writeString(tot_rent)
        parcel.writeString(vehicle_id)
        parcel.writeInt(drop_down)
        parcel.writeInt(btn_call)
        parcel.writeString(paid_online)
        parcel.writeString(processed_on)
        parcel.writeString(paid_store)
        parcel.writeString(ridobiko_charges)
        parcel.writeString(payment_status)
        parcel.writeString(date)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Payment_Tracker_Model> {
        override fun createFromParcel(parcel: Parcel): Payment_Tracker_Model {
            return Payment_Tracker_Model(parcel)
        }

        override fun newArray(size: Int): Array<Payment_Tracker_Model?> {
            return arrayOfNulls(size)
        }
    }
}
