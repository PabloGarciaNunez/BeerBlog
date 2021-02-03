package com.example.blogdelcervecero.ui.recipes

import androidx.lifecycle.MutableLiveData
import com.example.blogdelcervecero.model.BaseViewModel
import com.example.blogdelcervecero.model.Recipe
import com.example.blogdelcervecero.usecases.ObtainRecipesUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class RecipesListViewModel @Inject constructor(
    var obtainRecipesUseCase: ObtainRecipesUseCase
) : BaseViewModel() {

    val recipes : MutableLiveData<List<Recipe>> = MutableLiveData()

    fun loadRecipes() {

        GlobalScope.launch {

            dataLoading.postValue(true)
            val (_, beersResult) = obtainRecipesUseCase.get()
            dataLoading.postValue(false)
            if (beersResult != null) {

                recipes.postValue(beersResult)
            }
        }
    }
}

