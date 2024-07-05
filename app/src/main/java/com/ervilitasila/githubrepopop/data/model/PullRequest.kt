package com.ervilitasila.githubrepopop.data.model

data class PullRequest(
    var id: Int,
    var title: String,
    var body: String? = null,
    var user: User
)
