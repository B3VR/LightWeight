package com.example.lightweight.models

import android.os.Parcel
import android.os.Parcelable
import com.google.type.DateTime
import java.time.LocalDate

class Training() : Parcelable{
    var date: String? = null
    var done: Boolean = false
    var exercises = mutableListOf<Exercise>()

    constructor(parcel: Parcel) : this() {
        date = parcel.readString()
        done = parcel.readByte() != 0.toByte()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(date)
        parcel.writeByte(if (done) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Training> {
        override fun createFromParcel(parcel: Parcel): Training {
            return Training(parcel)
        }

        override fun newArray(size: Int): Array<Training?> {
            return arrayOfNulls(size)
        }
    }
}