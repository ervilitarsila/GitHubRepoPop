package com.ervilitasila.githubrepopop.view.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import Repository
import com.ervilitasila.githubrepopop.data.repository.RepositoryRemote
import javax.inject.Inject

class RepositoryViewModel @Inject constructor(private val repository: RepositoryRemote) : ViewModel() {
    val repositories = MutableLiveData<List<Repository>>()
    private val allRepositories = mutableListOf<Repository>()
    val error = MutableLiveData<String>()

    fun loadRepositories(page: Int) {
        try {
            repository.listRepositories(page.toString()).observeForever { newRepositories ->
                if (newRepositories != null) {
                    allRepositories.addAll(newRepositories)
                    repositories.value = allRepositories
                } else {
                    error.value = "Failed to load repositories"
                }
            }
        } catch (e: Exception) {
            error.value = e.message
        }
    }
}

