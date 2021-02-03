package com.example.blogdelcervecero.usecases

import com.example.blogdelcervecero.model.Beer
import com.example.blogdelcervecero.repository.BaseCallBackContinuation
import com.example.blogdelcervecero.repository.DataResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.Continuation
import kotlin.coroutines.suspendCoroutine

class ObtainBeersUseCase @Inject constructor() : BaseUseCase() {

    suspend fun get() = withContext(Dispatchers.IO) {

        suspendCoroutine { continuation : Continuation<DataResult<List<Beer>?>> ->

            repository.obtainBeers(BaseCallBackContinuation(continuation))
        }
    }
}
