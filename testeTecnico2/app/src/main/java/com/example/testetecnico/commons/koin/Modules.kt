package com.example.testetecnico.commons.koin

import com.example.testetecnico.repositories.movie.MovieRepository
import com.example.testetecnico.repositories.login.LoginRepository
import com.example.testetecnico.repositories.login.LoginRepositoryContract
import com.example.testetecnico.repositories.movie.MovieRepositoryContract
import com.example.testetecnico.ui.login.LoginViewModel
import com.example.testetecnico.ui.movies.detail.MovieDetailViewModel
import com.example.testetecnico.ui.movies.list.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { LoginViewModel(get()) }
    viewModel { MovieViewModel(get()) }
    viewModel { MovieDetailViewModel() }
}

val repositoryModule = module {
    factory<LoginRepositoryContract> { LoginRepository() }
    factory<MovieRepositoryContract> { MovieRepository() }
}