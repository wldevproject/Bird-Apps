package com.cnd.birdapps.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MessageEvent(
    var hideMenu: Boolean
) : Parcelable
