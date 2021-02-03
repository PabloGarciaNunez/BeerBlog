package com.example.blogdelcervecero.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Beer(
        var name: String,
        var style: String,
        var grades: Float
) : Parcelable

