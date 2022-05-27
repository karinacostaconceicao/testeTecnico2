package com.example.testetecnico.ui.movies.detail

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.testetecnico.commons.Results
import com.example.testetecnico.repositories.entities.Movie
import com.example.testetecnico.repositories.movie.MovieRepositoryContract
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.Dispatchers

class MovieDetailViewModel : ViewModel() {

    val title = MutableLiveData<String>()
    val raiting = MutableLiveData<Float>()
    val description = MutableLiveData<String>()
    val language = MutableLiveData<String>()
    val urlImage = MutableLiveData<String>()
    val isAdult = MutableLiveData<Boolean>()

    fun loadMovie(args: Bundle?) {
        val movie = Gson().fromJson(args?.getString("movie"), Movie::class.java)
        title.postValue(movie.title)
        description.postValue(movie.overview)
        raiting.postValue(movie.voteAverage)
        language.postValue(movie.originalLanguage.uppercase())
        isAdult.postValue(movie.adult)
        urlImage.postValue(movie.urlImage())
        title.postValue(movie.title)
    }
}