package com.ervilitasila.githubrepopop.view.home

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
import com.ervilitasila.githubrepopop.data.di.DaggerAppComponent
import com.ervilitasila.githubrepopop.databinding.FragmentHomeBinding
import com.ervilitasila.githubrepopop.data.model.User
import androidx.lifecycle.Observer
import com.ervilitasila.githubrepopop.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import javax.inject.Inject

class HomeFragment : Fragment() {
    @Inject
    lateinit var repositoryViewModel: RepositoryViewModel
    private var viewBinding: FragmentHomeBinding? = null
    private var selectedRepository: Repository? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("HomeFragment", "onAttach")
        val appComponent = DaggerAppComponent.factory().create(context.applicationContext)
        appComponent.repositoryComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("HomeFragment", "onCreateView")
        viewBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("HomeFragment", "onViewCreated")
//        loadingRepositories()
        observeAllRepositories()
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

    private fun observeAllRepositories() {
        Log.d("HomeFragment", "observeAllRepositories")
        repositoryViewModel.listRepositories().observe(viewLifecycleOwner, Observer { repositories ->
            Log.d("HomeFragment", "Repositories observed: $repositories")
            displayRepositories(repositories)
        })
    }

    private fun displayRepositories(repositories: List<Repository>) {
        Log.d("HomeFragment", "displayRepositories: ${repositories.size}")
        if (repositories.isEmpty()) {
            showErrorDialog("Repository Empty")
            return
        }

        val layoutManager = LinearLayoutManager(context)
        viewBinding?.recyclerRepositories?.layoutManager = layoutManager
        viewBinding?.recyclerRepositories?.adapter =
            RepositoryAdapter(
                context,
                repositories,
                itemClickListener = { repository, viewHolder ->
                    selectedRepository = repository
                    navigateToPullRequestFragment(selectedRepository!!)
                }
            )
    }
    private fun navigateToPullRequestFragment(repository: Repository) {
        val bundle = Bundle().apply {
            putParcelable("repository", repository)
        }
        findNavController().navigate(R.id.action_homeFragment_to_pullRequestFragment, bundle)
    }

    private fun showErrorDialog(message: String) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Error")
            .setMessage(message)
            .show()
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