package com.ervilitasila.githubrepopop.view.pullrequest

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ervilitasila.githubrepopop.R
import com.ervilitasila.githubrepopop.databinding.ItemPullRequestBinding
import com.ervilitasila.githubrepopop.data.model.PullRequest
import com.ervilitasila.githubrepopop.data.model.formattedDateCreated

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
            viewBinding.pullrequestCreated.text = pullRequest.formattedDateCreated
            Glide.with(this.itemView)
                .load(pullRequest.user.avatarUrl)
                .error(R.drawable.splash_bg)
                .into(viewBinding.userProfile)

            viewBinding.userLogin.text = pullRequest.user.login
            viewBinding.userName.text = pullRequest.user.name

            itemView.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(pullRequest.url))
                context?.startActivity(intent)
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val viewBinding = ItemPullRequestBinding.bind(itemView)
    }
}

