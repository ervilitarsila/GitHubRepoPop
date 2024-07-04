package com.ervilitasila.githubrepopop.data.di

import com.ervilitasila.githubrepopop.storage.SharedPreferencesStorage
import com.ervilitasila.githubrepopop.storage.Storage
import dagger.Binds
import dagger.Module

@Module
abstract class StorageModule {
    @Binds
    abstract fun provideStorage(storage: SharedPreferencesStorage): Storage
}