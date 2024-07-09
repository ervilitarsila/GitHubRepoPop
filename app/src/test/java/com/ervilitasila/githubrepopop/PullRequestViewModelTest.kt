package com.ervilitasila.githubrepopop

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.ervilitasila.githubrepopop.data.model.PullRequest
import com.ervilitasila.githubrepopop.data.model.User
import com.ervilitasila.githubrepopop.data.repository.RepositoryRemote
import com.ervilitasila.githubrepopop.view.pullrequest.PullRequestViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PullRequestViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private lateinit var viewModel: PullRequestViewModel

    @MockK
    lateinit var repository: RepositoryRemote

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = PullRequestViewModel(repository)
    }

    @Test
    fun listPullRequests_success() {
        val mockPullRequests = mockPullRequests()
        every { repository.listPullRequest("user", "repository1") } returns mockPullRequests
        val result = viewModel.listPullRequests("user", "repository1")
        verify { viewModel.listPullRequests("user", "repository1") }
        assert(result.value == mockPullRequests.value)
    }

    @Test
    fun listPullRequests_emptyList() {
        val emptyList = MutableLiveData<List<PullRequest>>().apply { value = emptyList() }
        every { repository.listPullRequest("user", "repository1") } returns emptyList

        val result = viewModel.listPullRequests("user", "repository1")

        verify { repository.listPullRequest("user", "repository1") }
        assert(result.value?.isEmpty() ?: false)
    }

    @Test
    fun listPullRequests_error() {
        val errorMessage = "Erro de rede"
        // ...
    }

    private fun mockPullRequests() = MutableLiveData<List<PullRequest>>().apply {
        value = listOf(
            PullRequest(
            id = 1,
            title = "Fix issue #123",
            body = "This pull request fixes the issue with the login functionality.",
            url = "https://github.com/user/repo/pull/1",
            dateCreated = "2024-01-01T10:00:00Z",
            user = User(
                id = 1,
                login = "user1",
                avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
                url = "https://github.com/user1"
            )
        ),
        PullRequest(
            id = 2,
            title = "Add new feature for settings",
            body = "This pull request adds a new feature to manage user settings.",
            url = "https://github.com/user/repo/pull/2",
            dateCreated = "2024-02-01T11:00:00Z",
            user = User(
                id = 2,
                login = "user2",
                avatarUrl = "https://avatars.githubusercontent.com/u/2?v=4",
                url = "https://github.com/user2"
            )
        ),
        PullRequest(
            id = 3,
            title = "Update documentation",
            body = "This pull request updates the project documentation to reflect recent changes.",
            url = "https://github.com/user/repo/pull/3",
            dateCreated = "2024-03-01T12:00:00Z",
            user = User(
                id = 3,
                login = "user3",
                avatarUrl = "https://avatars.githubusercontent.com/u/3?v=4",
                url = "https://github.com/user3"
            )
        )
    )}

}