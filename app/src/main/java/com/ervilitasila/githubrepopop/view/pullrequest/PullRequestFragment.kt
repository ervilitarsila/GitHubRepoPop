package com.ervilitasila.githubrepopop.view.pullrequest

import Repository
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ervilitasila.githubrepopop.R
import com.ervilitasila.githubrepopop.data.di.DaggerAppComponent
import com.ervilitasila.githubrepopop.databinding.FragmentPullRequestBinding
import com.ervilitasila.githubrepopop.data.model.PullRequest
import com.ervilitasila.githubrepopop.data.model.User
import com.ervilitasila.githubrepopop.view.home.RepositoryAdapter
import androidx.lifecycle.Observer
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import javax.inject.Inject

class PullRequestFragment : Fragment() {

    @Inject
    lateinit var pullRequestViewModel: PullRequestViewModel

    private var repositorySelected: Repository? = null
    private var viewBinding: FragmentPullRequestBinding? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("PullRequestFragment", "onAttach")
        val appComponent = DaggerAppComponent.factory().create(context.applicationContext)
        appComponent.repositoryComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentPullRequestBinding.inflate(inflater, container, false)
        arguments?.getParcelable<Repository>("repository")?.let { repository ->
            repositorySelected = repository
            Log.d("PullRequestFragment", "repositorySelected: $repositorySelected")
        }

        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding?.repositoryName?.text = repositorySelected?.name.toString()
        viewBinding?.pullrequestTotal?.text = "/ " + repositorySelected?.openIssues.toString() + " closed"

        showLoading(true)
        observeAllPullRequests()
        setupBackButton()
    }

    private fun observeAllPullRequests() {
        Log.d("PullRequestFragment", "observeAllPullRequests")

        pullRequestViewModel.listPullRequests(repositorySelected?.owner?.login.toString(), repositorySelected?.name.toString()).observe(viewLifecycleOwner, Observer { pullRequests ->
                    Log.d("PullRequestFragment", "pullRequests observed: $pullRequests")
                    displayPullRequests(pullRequests)
                })
    }


    private fun displayPullRequests(pullRequests: List<PullRequest>) {
        Log.d("PullRequestFragment", "displayRepositories: ${pullRequests.size}")
        showLoading(false)
        if (pullRequests.isEmpty()) {
            showErrorDialog("There are no pull requests for this repository!")
            return
        }
        viewBinding?.pullrequestIssueCount?.text = pullRequests.size.toString() + " opened"

        val layoutManager = LinearLayoutManager(context)
        viewBinding?.recyclerPullrequests?.layoutManager = layoutManager
        viewBinding?.recyclerPullrequests?.adapter =
            PullRequestAdapter(
                context,
                pullRequests
            )
    }

    private fun setupBackButton() {
        viewBinding?.btnBack?.setOnClickListener {
            findNavController().navigate(R.id.action_pullRequestFragment_to_homeFragment)
        }
    }

    private fun showErrorDialog(message: String) {
        viewBinding?.pullrequestTotal?.text = ""
        viewBinding?.pullrequestIssueCount?.text = ""
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Error")
            .setMessage(message)
            .setPositiveButton("Back to Home") { _, _ ->
                findNavController().navigate(R.id.action_pullRequestFragment_to_homeFragment)
            }
            .show()
    }

    private fun showLoading(isLoading: Boolean) {
        viewBinding?.loadingFrame?.visibility = if (isLoading) View.VISIBLE else View.GONE
        viewBinding?.recyclerPullrequests?.visibility = if (isLoading) View.GONE else View.VISIBLE
    }

    private fun setMockData()= listOf(
        PullRequest(1, "teste1", "Body teste 1", "https://github.com/user/repo/pull/1", "2023-05-20", User(1, "user 1", "https://avatars.githubusercontent.com/u/1?v=4", "www.google.com")),
        PullRequest(1, "teste2", "Body teste 1", "https://github.com/user/repo/pull/1", "2023-05-20",User(1, "user 1", "https://avatars.githubusercontent.com/u/1?v=4", "www.google.com")),
        PullRequest(1, "teste3", "Body teste 1", "https://github.com/user/repo/pull/1", "2023-05-20",User(1, "user 1", "https://avatars.githubusercontent.com/u/1?v=4", "www.google.com")),
        PullRequest(1, "teste4", "Body teste 1", "https://github.com/user/repo/pull/1", "2023-05-20",User(1, "user 1", "https://avatars.githubusercontent.com/u/1?v=4", "www.google.com")),
        PullRequest(1, "teste5", "Body teste 1", "https://github.com/user/repo/pull/1", "2023-05-20",User(1, "user 1", "https://avatars.githubusercontent.com/u/1?v=4", "www.google.com"))
    )

}