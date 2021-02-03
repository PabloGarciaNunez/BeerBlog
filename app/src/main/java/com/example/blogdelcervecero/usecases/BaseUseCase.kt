package com.example.blogdelcervecero.usecases

import com.example.blogdelcervecero.repository.BaseRepository
import javax.inject.Inject

abstract class BaseUseCase {

    @Inject
    lateinit var repository: BaseRepository

}