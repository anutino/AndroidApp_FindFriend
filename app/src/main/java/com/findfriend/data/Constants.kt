package com.findfriend.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Constants(
    val type: AnimalType
) : Parcelable {

    enum class AnimalType {
        DOG, CAT, ALL
    }
}