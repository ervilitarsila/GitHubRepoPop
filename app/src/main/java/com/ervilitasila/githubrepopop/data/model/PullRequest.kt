package com.ervilitasila.githubrepopop.data.model

import com.squareup.moshi.Json

data class PullRequest(
    var id: Int,
    var title: String,
    var body: String? = null,
    @Json(name = "html_url") var url: String,
    @Json(name = "created_at") var dateCreated: String,
    var user: User
)

val PullRequest.formattedDateCreated: String
    get() {
        val regex = Regex("""(\d{4}-\d{2}-\d{2})""")
        val matchResult = regex.find(this.dateCreated)
        return matchResult?.value ?: ""
    }