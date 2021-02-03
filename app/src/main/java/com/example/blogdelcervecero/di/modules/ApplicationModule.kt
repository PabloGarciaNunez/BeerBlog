package com.example.blogdelcervecero.di.modules

import android.content.Context
import com.example.blogdelcervecero.di.BeerApplication
import com.example.blogdelcervecero.repository.BaseRepository
import com.example.blogdelcervecero.repository.LocalRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: BeerApplication) {

    @Provides
    @Singleton
    fun provideApplication() = application

    @Provides
    fun provideAppContext(): Context = application

    @Provides
    fun provideRepository(): BaseRepository = LocalRepository(application)

}