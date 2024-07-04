package com.ervilitasila.githubrepopop.view.home

import com.ervilitasila.githubrepopop.data.repository.RepositoryRemote
import com.ervilitasila.githubrepopop.data.repository.RepositoryRemoteImpl
import javax.inject.Inject

class RepositoryViewModel @Inject constructor(private val repository: RepositoryRemote = RepositoryRemoteImpl()) {

    fun listRepositories() = repository.listRepositories(page = 1)

}