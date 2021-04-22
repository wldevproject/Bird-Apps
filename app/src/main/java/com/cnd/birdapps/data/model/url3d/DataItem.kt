package com.cnd.birdapps.data.model.url3d

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 ** Written by CND_Studio 20/04/2021 22.29.
 ** Author @JoeFachrizal
 ** Happy Code...
 **/

@Parcelize
data class DataItem(
    val createdAt: String,
    val name: String,
    val id: Int,
    val url: String,
    val updatedAt: String
) : Parcelable
