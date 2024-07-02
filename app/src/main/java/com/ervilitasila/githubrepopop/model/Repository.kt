package com.ervilitasila.githubrepopop.model

import com.squareup.moshi.Json

data class Repository(
    var id: Int,
    var name: String,
    var description: String,
    @Json(name = "stargazers_count") var stargazers: Int,
    @Json(name = "watchers_count") var watchers: Int,
    @Json(name = "forks_count") var forks: Int,
    @Json(name = "open_issues_count") var openIssues: Int,
    var owner: User
)