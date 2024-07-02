package com.ervilitasila.githubrepopop.viewmodel

import com.ervilitasila.githubrepopop.data.repository.RepositoryRemote
import com.ervilitasila.githubrepopop.data.repository.RepositoryRemoteImpl

class PullRequestViewModel(private val repository: RepositoryRemote = RepositoryRemoteImpl()) {

    fun listPullRequests(ownerName: String, repositoryName: String) =
        repository.listPullRequest(ownerName, repositoryName)

}