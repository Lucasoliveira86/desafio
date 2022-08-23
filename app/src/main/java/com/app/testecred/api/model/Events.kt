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
)