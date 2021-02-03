package com.example.blogdelcervecero.ui.beer.list


import androidx.lifecycle.MutableLiveData
import com.example.blogdelcervecero.model.BaseViewModel
import com.example.blogdelcervecero.model.Beer
import com.example.blogdelcervecero.usecases.ObtainBeersUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class BeerListViewModel @Inject constructor(
    var obtainBeersUseCase: ObtainBeersUseCase
) : BaseViewModel() {

    val beers : MutableLiveData<List<Beer>> = MutableLiveData()

    /**
     * Obtain beers from repository
     */
    fun loadBeers()  {

        GlobalScope.launch {

            dataLoading.postValue(true)
            val (_, beersResult) = obtainBeersUseCase.get()
            dataLoading.postValue(false)
            if (beersResult != null) {

                beers.postValue(beersResult)
            }
        }
    }
}
