package com.ervilitasila.githubrepopop.data.model

import com.squareup.moshi.Json

data class User(
    var id: Int,
    var login: String,
    @Json(name = "avatar_url") var avatarUrl: String,
    var url: String,
    var name: String? = null
)
