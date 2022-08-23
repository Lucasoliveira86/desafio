package com.app.testecred.api.model

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

data class Events(
    val people: ArrayList<String>?,
    val date: Long,
    val description: String,
    val image: String,
    val longitude: Double,
    val latitude: Double,
    val price: Double,
    val title: String,
    val id: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.createStringArrayList(),
        parcel.readLong(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeStringList(people)
        parcel.writeLong(date)
        parcel.writeString(description)
        parcel.writeString(image)
        parcel.writeDouble(longitude)
        parcel.writeDouble(latitude)
        parcel.writeDouble(price)
        parcel.writeString(title)
        parcel.writeString(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Events> {
        override fun createFromParcel(parcel: Parcel): Events {
            return Events(parcel)
        }

        override fun newArray(size: Int): Array<Events?> {
            return arrayOfNulls(size)
        }
    }
}