package com.ervilitasila.githubrepopop.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ervilitasila.githubrepopop.databinding.FragmentHomeBinding
import com.ervilitasila.githubrepopop.data.model.Repository
import com.ervilitasila.githubrepopop.data.model.User
import com.ervilitasila.githubrepopop.viewmodel.RepositoryViewModel

class HomeFragment : Fragment() {

    private lateinit var viewModel: RepositoryViewModel
    private var viewBinding: FragmentHomeBinding? = null
    private var selectedRepository: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingRepositories()
    }

    private fun loadingRepositories() {
        val layoutManager = LinearLayoutManager(context)
        viewBinding?.recyclerRepositories?.layoutManager = layoutManager

        val adapter = RepositoryAdapter(
            context,
            setMockData(),
            itemClickListener = { repository, viewHolder ->
                selectedRepository = repository
                navigateToPullRequestFragment(selectedRepository!!)
            }
        )
        viewBinding?.recyclerRepositories?.adapter = adapter
    }

    private fun navigateToPullRequestFragment(repositoryName: String) {
        val action = HomeFragmentDirections.actionHomeFragmentToPullRequestFragment(repositoryName)
        findNavController().navigate(action)
    }

    private fun setMockData()= listOf(
        Repository(1, "Repository 1", "Body teste 1 fsdfs lksn kjn jjbj hvh gvh bkj bk jn kh hv j vjh vj hvjhvjhvjhvj jhvjhvj hvjhvjhv jhvjhvjh vjhv jhv jhv jh vjh vj hvj hvj hvjhv jhv jh v",500, 1523, 456, 5 , User(1, "user 1", "https://avatars.githubusercontent.com/u/1?v=4", "www.google.com")),
        Repository(1, "Repository 2", "Body teste 1",500, 1523, 456, 5 , User(1, "user 1", "https://avatars.githubusercontent.com/u/1?v=4", "www.google.com")),
        Repository(1, "Repository 3", "Body teste 1",500, 1523, 456, 5 , User(1, "user 1", "https://avatars.githubusercontent.com/u/1?v=4", "www.google.com")),
        Repository(1, "Repository 4", "Body teste 1",500, 1523, 456, 5 , User(1, "user 1", "https://avatars.githubusercontent.com/u/1?v=4", "www.google.com")),
        Repository(1, "Repository 5", "Body teste 1",500, 1523, 456, 5 , User(1, "user 1", "https://avatars.githubusercontent.com/u/1?v=4", "www.google.com")),
        Repository(1, "Repository 6", "Body teste 1",500, 1523, 456, 5 , User(1, "user 1", "https://avatars.githubusercontent.com/u/1?v=4", "www.google.com"))
    )
}