package com.example.testetecnico.repositories.movie

import com.example.testetecnico.repositories.RetrofitHelper
import com.example.testetecnico.repositories.entities.movie.MovieResult
import retrofit2.http.GET

interface MovieRepositoryContract {

    @GET("movie/upcoming?api_key=a06cb74dc8372e72c4fc7ce30c3088cc&language=pt-BR&page=1")
    suspend fun upcomings(): MovieResult
}

class MovieRepository: MovieRepositoryContract {

    override suspend fun upcomings(): MovieResult {
        val movieApi = RetrofitHelper().instance().create(MovieRepositoryContract::class.java)
        return movieApi.upcomings()
    }
}