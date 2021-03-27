package com.cnd.birdapps.data.model.article

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
/**
 ** Written by CND_Studio 16/03/2021 22.29.
 ** Author @JoeFachrizal
 ** Happy Code...
 **/
@Parcelize
data class ArticleResponse(

	@field:SerializedName("data")
	val data: ArrayList<DataItem>,

	@field:SerializedName("status")
	val status: String
) : Parcelable