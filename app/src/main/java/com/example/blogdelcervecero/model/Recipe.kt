package com.example.blogdelcervecero.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Recipe(
        val name: String,
        val volume: Float,
        val hops: List<String>
) : Parcelable