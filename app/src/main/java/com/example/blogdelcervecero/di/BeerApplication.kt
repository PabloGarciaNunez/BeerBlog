package com.example.blogdelcervecero.di

import android.app.Application
import com.example.blogdelcervecero.di.components.ApplicationComponent
import com.example.blogdelcervecero.di.components.DaggerApplicationComponent
import com.example.blogdelcervecero.di.modules.ApplicationModule

class BeerApplication : Application() {

    val component: ApplicationComponent by lazy {

        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    override fun onCreate() {
        // this is a test
        super.onCreate()
        component.inject(this)
    }
}
