package com.ervilitasila.githubrepopop.model

import com.squareup.moshi.Json

data class PullRequest(
    var id: Int,
    var title: String,
    var body: String,
    @Json(name = "created_at") var createdData: String,
    @Json(name = "update_at") var updateData: String,
    var user: User
)
