package com.ervilitasila.githubrepopop

import android.app.Application
import com.ervilitasila.githubrepopop.data.di.AppComponent
import com.ervilitasila.githubrepopop.data.di.DaggerAppComponent

open class MyApplication : Application() {
    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }
}