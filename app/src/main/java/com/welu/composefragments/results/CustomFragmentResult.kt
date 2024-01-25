package com.welu.composefragments.results

import android.os.Parcel
import android.os.Parcelable
import com.welu.composefragments.result.FragmentResult

class CustomFragmentResult(
    val name: String
): FragmentResult {
    constructor(parcel: Parcel) : this(parcel.readString()!!) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CustomFragmentResult> {
        override fun createFromParcel(parcel: Parcel): CustomFragmentResult {
            return CustomFragmentResult(parcel)
        }

        override fun newArray(size: Int): Array<CustomFragmentResult?> {
            return arrayOfNulls(size)
        }
    }
}