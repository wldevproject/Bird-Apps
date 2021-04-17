package com.cnd.birdapps.data.model.kategory

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class KategoryResponse(

    @field:SerializedName("data")
	val data: ArrayList<DataItemKat>,

    @field:SerializedName("status")
	val status: String
) : Parcelable