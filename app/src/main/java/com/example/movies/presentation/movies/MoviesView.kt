package com.example.movies.presentation.movies

import com.example.movies.domain.models.Movie
import com.example.movies.ui.movies.models.MoviesState


interface MoviesView {


    // Методы, меняющие внешний вид экрана

    // Состояние «загрузки»
    fun showLoading()

    // Состояние «ошибки»
    fun showError(errorMessage: String)

    // Состояние «пустого списка»
    fun showEmpty(emptyMessage: String)

    // Состояние «контента»
    fun showContent(movies: List<Movie>)

    fun render(state: MoviesState)

    // Методы «одноразовых событий»

    fun showToast(additionalMessage: String)

}
