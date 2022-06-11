package com.ridobiko.ridobikoPartner.models

import android.os.Parcel
import android.os.Parcelable

data class Bike_Database_Model(val bike_id:String,val order_id:String,val cust_name:String,val cust_number:String,val block_range:String,val puc_date:String,
                               val pick_date:String,val drop:String,val insurance_date:String,val tax_date:String,val permit :String,val drop_down:Int):Parcelable {
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
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(bike_id)
        parcel.writeString(order_id)
        parcel.writeString(cust_name)
        parcel.writeString(cust_number)
        parcel.writeString(block_range)
        parcel.writeString(puc_date)
        parcel.writeString(pick_date)
        parcel.writeString(drop)
        parcel.writeString(insurance_date)
        parcel.writeString(tax_date)
        parcel.writeString(permit)
        parcel.writeInt(drop_down)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Bike_Database_Model> {
        override fun createFromParcel(parcel: Parcel): Bike_Database_Model {
            return Bike_Database_Model(parcel)
        }

        override fun newArray(size: Int): Array<Bike_Database_Model?> {
            return arrayOfNulls(size)
        }
    }
}
