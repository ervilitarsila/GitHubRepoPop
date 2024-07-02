package com.ervilitasila.githubrepopop.data.model

data class PullRequest(
    var id: Int,
    var title: String,
    var body: String,
    var user: User
)
