package com.example.testetecnico.ui.movies.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.testetecnico.commons.Results
import com.example.testetecnico.repositories.movie.MovieRepositoryContract
import kotlinx.coroutines.Dispatchers

class MovieViewModel(private val movieRepository: MovieRepositoryContract) : ViewModel() {

    fun upcomings() = liveData(Dispatchers.IO) {
        emit(Results.loading(null))
        try {
            val movies = movieRepository.upcomings()
            emit(Results.success(movies.results))
        } catch (exception: Exception){
            exception.printStackTrace()
            emit(Results.error(null,"Ocorreu um erro"))
        }
    }
}