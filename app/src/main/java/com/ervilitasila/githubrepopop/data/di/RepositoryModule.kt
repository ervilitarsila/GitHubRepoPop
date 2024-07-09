package com.ervilitasila.githubrepopop.data.di

import com.ervilitasila.githubrepopop.data.repository.RepositoryRemote
import com.ervilitasila.githubrepopop.data.repository.RepositoryRemoteImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideRepositoryRemote(): RepositoryRemote {
        return RepositoryRemoteImpl()
    }
}