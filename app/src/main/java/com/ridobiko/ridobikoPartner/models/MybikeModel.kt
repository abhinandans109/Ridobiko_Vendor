package com.ridobiko.ridobikoPartner.models

import android.os.Parcel
import android.os.Parcelable

data class MybikeModel(val image:Int,val name:String,val id:String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(image)
        parcel.writeString(name)
        parcel.writeString(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MybikeModel> {
        override fun createFromParcel(parcel: Parcel): MybikeModel {
            return MybikeModel(parcel)
        }

        override fun newArray(size: Int): Array<MybikeModel?> {
            return arrayOfNulls(size)
        }
    }
}
