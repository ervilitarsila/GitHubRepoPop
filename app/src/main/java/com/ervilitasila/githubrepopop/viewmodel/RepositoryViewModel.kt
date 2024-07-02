package com.ervilitasila.githubrepopop.viewmodel

import com.ervilitasila.githubrepopop.data.repository.RepositoryRemote
import com.ervilitasila.githubrepopop.data.repository.RepositoryRemoteImpl

class RepositoryViewModel(private val repository: RepositoryRemote = RepositoryRemoteImpl()) {

    fun listRepositories() = repository.listRepositories(page = 1)

}