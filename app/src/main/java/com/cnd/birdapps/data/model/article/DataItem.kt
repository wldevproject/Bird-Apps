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
data class DataItem(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("birdSpeciesId")
	val birdSpeciesId: Int,

	@field:SerializedName("publish")
	val publish: Boolean,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("userId")
	val userId: Int,

	@field:SerializedName("user")
	val user: User,

	@field:SerializedName("updatedAt")
	val updatedAt: String,

	@field:SerializedName("birdSpecies")
	val birdSpecies: BirdSpecies
) : Parcelable