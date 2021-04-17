package com.cnd.birdapps.data.model.userLogin

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataLog(

	@field:SerializedName("role")
	val role: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("username")
	val username: String
) : Parcelable