package com.ervilitasila.githubrepopop.view.home

import Repository
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ervilitasila.githubrepopop.R
import com.ervilitasila.githubrepopop.databinding.ItemRepositoryBinding

class RepositoryAdapter(
    private val context: Context?,
    private val repositories: MutableList<Repository>,
    private val itemClickListener: (Repository, ViewHolder) -> Unit
) : RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

    fun addRepositories(newRepositories: List<Repository>) {
        val startPosition = repositories.size
        repositories.addAll(newRepositories)
        notifyItemRangeInserted(startPosition, newRepositories.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = LayoutInflater.from(context).inflate(R.layout.item_repository, parent, false)
        return ViewHolder(viewHolder)
    }

    override fun getItemCount(): Int {
        return repositories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repository: Repository = repositories[position]

        with(holder) {
            viewBinding.repositoryName.text = repository.name
            viewBinding.repositoryDescription.text = repository.description
            viewBinding.repositoryWatchers.text = repository.watchers.toString()
            viewBinding.repositoryStar.text = repository.stargazers.toString()
            viewBinding.repositoryForks.text = repository.forks.toString()

            Glide.with(this.itemView)
                .load(repository.owner.avatarUrl)
                .error(R.drawable.splash_bg)
                .into(viewBinding.ownerProfile)

            viewBinding.ownerLogin.text = repository.owner.login
            viewBinding.ownerName.text = repository.owner.name

            viewBinding.itemRepository.setOnClickListener {
                itemClickListener?.invoke(repository, this)
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val viewBinding = ItemRepositoryBinding.bind(itemView)
    }
}

typealias OnItemClickedListener = (repository: Repository, viewHolder: RepositoryAdapter.ViewHolder) -> Unit
