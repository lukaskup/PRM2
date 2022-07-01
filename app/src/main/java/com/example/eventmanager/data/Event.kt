package com.example.eventmanager.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.Date

@Parcelize
data class Event(
    var id: String,
    var name: String,
    var location: String,
    var imageUrl: String,
    var date: Date,
): Parcelable {
    override fun toString(): String {
        return name
    }
}
