package com.ervilitasila.githubrepopop.model

data class PullRequest(
    var id: Int,
    var title: String,
    var body: String,
    var user: User
)
