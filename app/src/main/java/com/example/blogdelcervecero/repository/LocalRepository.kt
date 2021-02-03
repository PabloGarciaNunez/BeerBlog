package com.example.blogdelcervecero.repository

import android.content.Context
import com.example.blogdelcervecero.model.Beer
import com.example.blogdelcervecero.model.Recipe
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*
import java.io.IOException

const val DEFAULT_DELAY = 3000L
const val DEFAULT_SUCCESS_HTTP_CODE = 200

class LocalRepository(var context: Context) : BaseRepository() {

    override fun obtainBeers(callback: BaseCallback<List<Beer>>) {

        GlobalScope.launch(Dispatchers.IO) {

            val jsonFileString = getJsonDataFromAsset("beers.json")
            val beerListType = object : TypeToken<List<Beer>>() {}.type
            val beers: List<Beer> = Gson().fromJson(jsonFileString, beerListType)
            callback.onSuccess(DEFAULT_SUCCESS_HTTP_CODE, beers)
        }
    }

    override fun obtainRecipes(callback: BaseCallback<List<Recipe>>){

        GlobalScope.launch(Dispatchers.IO) {

            val jsonFileString = getJsonDataFromAsset("recipes.json")
            val recipeListType = object : TypeToken<List<Recipe>>() {}.type
            val recipes: List<Recipe> = Gson().fromJson(jsonFileString, recipeListType)
            callback.onSuccess(DEFAULT_SUCCESS_HTTP_CODE, recipes)
        }
    }

    /**
     * Get json from local asset file
     *
     * @param fileName - name of file
     */
    private suspend fun getJsonDataFromAsset(fileName: String,
                                             delayInMillis: Long = DEFAULT_DELAY
    ): String? {

        val jsonString: String
        try {

            delay(delayInMillis)
            jsonString = context.assets.open(fileName).bufferedReader().run {

                readText()
            }

        } catch (ioException: IOException) {

            ioException.printStackTrace()
            return null
        }

        return jsonString
    }
}
