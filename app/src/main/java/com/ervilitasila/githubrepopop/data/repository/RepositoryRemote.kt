package com.ervilitasila.githubrepopop.data.repository

import androidx.lifecycle.LiveData
import com.ervilitasila.githubrepopop.data.model.PullRequest
import com.ervilitasila.githubrepopop.data.model.Repository
import com.ervilitasila.githubrepopop.data.model.User

interface RepositoryRemote {

    fun listRepositories(page: String) : LiveData<List<Repository>>

    fun getOwner(ownerName: String) : LiveData<User>

    fun listPullRequest(ownerName: String, repositoryName: String) : LiveData<List<PullRequest>>

}