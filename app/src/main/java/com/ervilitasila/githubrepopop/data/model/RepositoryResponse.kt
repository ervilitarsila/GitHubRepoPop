package com.ervilitasila.githubrepopop.data.model

import Repository

data class RepositoryResponse(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<Repository>
)