package com.ervilitasila.githubrepopop.data.di

import android.content.Context
import com.ervilitasila.githubrepopop.MainActivity
import com.ervilitasila.githubrepopop.view.home.RepositoryComponent
import com.ervilitasila.githubrepopop.view.pullrequest.PullRequestComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class, AppSubcomponents::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
    fun inject(activity: MainActivity)

    fun repositoryComponent(): RepositoryComponent.Factory
    fun pullRequestComponent(): PullRequestComponent.Factory
}