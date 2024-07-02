package com.ervilitasila.githubrepopop.ui.pullrequest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ervilitasila.githubrepopop.R
import com.ervilitasila.githubrepopop.databinding.ItemPullRequestBinding
import com.ervilitasila.githubrepopop.databinding.ItemRepositoryBinding
import com.ervilitasila.githubrepopop.model.PullRequest
import com.ervilitasila.githubrepopop.model.Repository

class PullRequestAdapter (private val context: Context?,
    private var pullRequestList: List<PullRequest>
) : RecyclerView.Adapter<PullRequestAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = LayoutInflater.from(context).inflate(R.layout.item_pull_request, parent, false)
        return ViewHolder(viewHolder)
    }

    override fun getItemCount(): Int {
        return pullRequestList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pullRequest: PullRequest = pullRequestList[position]

        with(holder) {
            viewBinding.pullrequestTitle.text = pullRequest.title
            viewBinding.pullrequestDescription.text = pullRequest.body

            Glide.with(this.itemView)
                .load(pullRequest.user.avatarUrl)
                .error(R.drawable.splash_bg)
                .into(viewBinding.userProfile)

            viewBinding.userLogin.text = pullRequest.user.login
            viewBinding.userName.text = pullRequest.user.name
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val viewBinding = ItemPullRequestBinding.bind(itemView)
    }
}

