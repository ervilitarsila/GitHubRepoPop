package com.ervilitasila.githubrepopop

import Repository
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.ervilitasila.githubrepopop.data.model.User
import com.ervilitasila.githubrepopop.data.repository.RepositoryRemote
import com.ervilitasila.githubrepopop.view.home.RepositoryViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RepositoryViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private lateinit var viewModel: RepositoryViewModel

    @MockK
    lateinit var repository: RepositoryRemote

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = RepositoryViewModel(repository)
    }

    @Test
    fun loadRepositories_success() {
        val mockRepositories = mockRepositories()
        every { repository.listRepositories("1") } returns mockRepositories
        viewModel.loadRepositories(1)
        verify { repository.listRepositories("1") }
        assert(viewModel.repositories.value == mockRepositories.value)
    }

    @Test
    fun loadRepositories_emptyList() {
        val emptyList = MutableLiveData<List<Repository>>().apply { value = emptyList() }
        every { repository.listRepositories("1") } returns emptyList

        viewModel.loadRepositories(1)

        verify { repository.listRepositories("1") }
        assert(viewModel.repositories.value?.isEmpty() ?: false)
    }

    @Test
    fun loadRepositories_error() {
        val errorMessage = "Erro de rede"
        val errorLiveData = MutableLiveData<List<Repository>>()
        every { repository.listRepositories("1") } returns errorLiveData

        viewModel.loadRepositories(1)

        // Simula a chamada do método onChanged com uma lista nula para gerar erro
        errorLiveData.postValue(null)

        verify { repository.listRepositories("1") }
        assert(viewModel.repositories.value == null)
        assert(viewModel.error.value == "Failed to load repositories")
    }

    private fun mockRepositories() = MutableLiveData<List<Repository>>().apply {
        value = listOf(
            Repository(
                0,
                "repo1",
                "Description 1",
                10,
                1,
                5,
                20,
                User(1, "owner1", "https://example.com/avatar1.jpg", "https://example.com/owner1")
            ),
            Repository(
                1,
                "repo2",
                "Description 2",
                10,
                1,
                5,
                20,
                User(2, "owner2", "https://example.com/avatar2.jpg", "https://example.com/owner2")
            )
        )
    }
}
