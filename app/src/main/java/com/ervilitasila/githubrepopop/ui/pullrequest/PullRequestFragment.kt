package com.ervilitasila.githubrepopop.ui.pullrequest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ervilitasila.githubrepopop.R
import com.ervilitasila.githubrepopop.databinding.FragmentHomeBinding
import com.ervilitasila.githubrepopop.databinding.FragmentPullRequestBinding

class PullRequestFragment : Fragment() {

    private var viewBinding: FragmentPullRequestBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentPullRequestBinding.inflate(inflater, container, false)
        return viewBinding?.root
    }

}