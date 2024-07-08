package com.ervilitasila.githubrepopop.view.home

import Repository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ervilitasila.githubrepopop.data.repository.RepositoryRemote
import com.ervilitasila.githubrepopop.data.repository.RepositoryRemoteImpl
import javax.inject.Inject

class RepositoryViewModel @Inject constructor(private val repository: RepositoryRemote) : ViewModel() {
    val repositories = MutableLiveData<List<Repository>>()
    private val allRepositories = mutableListOf<Repository>()

    fun loadRepositories(page: Int) {
        repository.listRepositories(page.toString()).observeForever { newRepositories ->
            allRepositories.addAll(newRepositories)
            repositories.value = allRepositories
        }
    }
}
