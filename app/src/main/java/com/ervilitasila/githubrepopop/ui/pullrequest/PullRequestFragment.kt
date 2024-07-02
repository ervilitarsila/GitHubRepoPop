package com.ervilitasila.githubrepopop.ui.pullrequest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ervilitasila.githubrepopop.R
import com.ervilitasila.githubrepopop.databinding.FragmentHomeBinding
import com.ervilitasila.githubrepopop.databinding.FragmentPullRequestBinding
import com.ervilitasila.githubrepopop.model.PullRequest
import com.ervilitasila.githubrepopop.model.Repository
import com.ervilitasila.githubrepopop.model.User
import com.ervilitasila.githubrepopop.ui.home.RepositoryAdapter

class PullRequestFragment : Fragment() {

    private var repositoryName: String? = null
    private var viewBinding: FragmentPullRequestBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentPullRequestBinding.inflate(inflater, container, false)
        arguments?.let {
            repositoryName = it.getString("repositoryName")
        }
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding?.repositoryName?.text = repositoryName.toString()

        loadingPullRequests()
        setupBackButton()
    }

    private fun loadingPullRequests() {
        val layoutManager = LinearLayoutManager(context)

        viewBinding?.recyclerPullrequests?.layoutManager = layoutManager

        val adapter = PullRequestAdapter(
            context,
            setMockData()
        )
        viewBinding?.recyclerPullrequests?.adapter = adapter

    }

    private fun setupBackButton() {
        viewBinding?.btnBack?.setOnClickListener {
            findNavController().navigate(R.id.action_userDetailFragment_to_homeFragment)
        }
    }

    private fun setMockData()= listOf(
        PullRequest(1, "teste1sdsdsdsdsdsdsdsdsdsdddddddssssssss", "Body teste 1 fsdfs lksn kjn jjbj hvh gvh bkj bk jn kh hv j vjh vj hvjhvjhvjhvj jhvjhvj hvjhvjhv jhvjhvjh vjhv jhv jhv jh vjh vj hvj hvj hvjhv jhv jh v", User(1, "user 1", "https://avatars.githubusercontent.com/u/1?v=4", "www.google.com")),
        PullRequest(1, "teste2", "Body teste 1", User(1, "user 1", "https://avatars.githubusercontent.com/u/1?v=4", "www.google.com")),
        PullRequest(1, "teste3", "Body teste 1", User(1, "user 1", "https://avatars.githubusercontent.com/u/1?v=4", "www.google.com")),
        PullRequest(1, "teste4", "Body teste 1", User(1, "user 1", "https://avatars.githubusercontent.com/u/1?v=4", "www.google.com")),
        PullRequest(1, "teste5", "Body teste 1", User(1, "user 1", "https://avatars.githubusercontent.com/u/1?v=4", "www.google.com"))
    )

}