package com.ervilitasila.githubrepopop.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ervilitasila.githubrepopop.data.model.Repository
import com.ervilitasila.githubrepopop.data.repository.RepositoryRemote
import com.ervilitasila.githubrepopop.data.repository.RepositoryRemoteImpl
import javax.inject.Inject

class RepositoryViewModel @Inject constructor(private val repository: RepositoryRemote) : ViewModel() {

    fun listRepositories(): LiveData<List<Repository>> {
        return repository.listRepositories("1")
    }

}