package com.ervilitasila.githubrepopop.data.model

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var id: Int,
    var login: String,
    @Json(name = "avatar_url") var avatarUrl: String,
    var url: String,
    var name: String? = null
) : Parcelable
