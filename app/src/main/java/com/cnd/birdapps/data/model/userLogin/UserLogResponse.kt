package com.cnd.birdapps.data.model.userLogin

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserLogResponse(

	@field:SerializedName("data")
	val data: DataLog,

	@field:SerializedName("status")
	val status: String
) : Parcelable