package com.ervilitasila.githubrepopop.view.pullrequest

import com.ervilitasila.githubrepopop.data.di.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface PullRequestComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): PullRequestComponent
    }
}