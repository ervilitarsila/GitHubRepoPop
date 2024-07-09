package com.ervilitasila.githubrepopop.view.pullrequest

import com.ervilitasila.githubrepopop.data.repository.RepositoryRemote
import com.ervilitasila.githubrepopop.data.repository.RepositoryRemoteImpl
import javax.inject.Inject

class PullRequestViewModel @Inject constructor(private val repository: RepositoryRemote = RepositoryRemoteImpl()) {

    fun listPullRequests(ownerName: String, repositoryName: String) =
        repository.listPullRequest(ownerName, repositoryName)

}