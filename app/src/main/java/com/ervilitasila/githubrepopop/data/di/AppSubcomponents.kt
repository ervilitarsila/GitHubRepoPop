package com.ervilitasila.githubrepopop.data.di

import com.ervilitasila.githubrepopop.view.home.RepositoryComponent
import com.ervilitasila.githubrepopop.view.pullrequest.PullRequestComponent
import dagger.Module

@Module(
    subcomponents = [
        RepositoryComponent::class,
        PullRequestComponent::class
    ]
)
class AppSubcomponents