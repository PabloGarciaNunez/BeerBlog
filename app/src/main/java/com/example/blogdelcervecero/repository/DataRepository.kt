package com.example.blogdelcervecero.repository

import com.example.blogdelcervecero.model.Beer
import com.example.blogdelcervecero.model.Recipe

interface DataRepository {

    /**
     * Obtain beers from repository
     *
     * @return - list of beers
     */
    fun obtainBeers(callback: BaseCallback<List<Beer>>)

    /**
     * Obtain recipes from repository
     *
     * @return - list of recipes
     */
    fun obtainRecipes(callback: BaseCallback<List<Recipe>>)

}