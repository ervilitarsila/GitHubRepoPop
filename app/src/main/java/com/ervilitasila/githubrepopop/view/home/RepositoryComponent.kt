package com.ervilitasila.githubrepopop.view.home

import com.ervilitasila.githubrepopop.MainActivity
import com.ervilitasila.githubrepopop.data.di.ActivityScope
import com.ervilitasila.githubrepopop.view.pullrequest.PullRequestFragment
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface RepositoryComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): RepositoryComponent
    }

    fun inject(activity: MainActivity)
    fun inject(fragment: HomeFragment)
    fun inject(fragment: PullRequestFragment)
}