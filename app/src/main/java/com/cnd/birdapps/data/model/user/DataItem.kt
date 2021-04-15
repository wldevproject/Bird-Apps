package com.cnd.birdapps.data.model.user

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
/**
 ** Written by CND_Studio 16/03/2021 22.29.
 ** Author @JoeFachrizal
 ** Happy Code...
 **/
@Parcelize
data class DataItem(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("role")
	val role: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("username")
	val username: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
) : Parcelable