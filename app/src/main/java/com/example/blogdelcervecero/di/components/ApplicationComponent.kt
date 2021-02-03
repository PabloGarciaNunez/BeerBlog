package com.example.blogdelcervecero.di.components

import android.content.Context
import com.example.blogdelcervecero.di.BeerApplication
import com.example.blogdelcervecero.di.modules.ApplicationModule
import com.example.blogdelcervecero.repository.BaseRepository
import com.example.blogdelcervecero.ui.beer.list.BeerListFragment
import com.example.blogdelcervecero.ui.recipes.RecipesListFragment
import com.example.blogdelcervecero.usecases.BaseUseCase
import dagger.Component

@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun getContext(): Context

    fun getRepository(): BaseRepository

    fun inject(app: BeerApplication)

    fun inject(beerListFragment: BeerListFragment)

    fun inject(recipesListFragment: RecipesListFragment)

}