package com.example.movies.util

import android.content.Context
import com.example.movies.data.MoviesRepositoryImpl
import com.example.movies.data.network.RetrofitNetworkClient
import com.example.movies.domain.api.MoviesInteractor
import com.example.movies.domain.api.MoviesRepository
import com.example.movies.domain.impl.MoviesInteractorImpl
import com.example.movies.presentation.movies.MoviesSearchPresenter
import com.example.movies.presentation.poster.PosterPresenter
import com.example.movies.presentation.movies.MoviesView
import com.example.movies.presentation.poster.PosterView

object Creator {
    private fun getMoviesRepository(context: Context): MoviesRepository {
        return MoviesRepositoryImpl(RetrofitNetworkClient(context))
    }

    fun provideMoviesInteractor(context: Context): MoviesInteractor {
        return MoviesInteractorImpl(getMoviesRepository(context))
    }

    fun provideMoviesSearchPresenter(moviesView: MoviesView, context: Context): MoviesSearchPresenter {
        return MoviesSearchPresenter(view = moviesView,context=context)
    }

    fun providePosterPresenter(posterView: PosterView, imageUrl: String): PosterPresenter {
        return PosterPresenter(posterView, imageUrl)
    }
}