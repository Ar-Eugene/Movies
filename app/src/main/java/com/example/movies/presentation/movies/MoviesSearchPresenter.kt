package com.example.movies.presentation.movies

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.example.movies.util.Creator
import com.example.movies.R
import com.example.movies.domain.api.MoviesInteractor
import com.example.movies.domain.models.Movie


class MoviesSearchPresenter (private val view: MoviesView,
                             private val context: Context,) {

    private val moviesInteractor = Creator.provideMoviesInteractor(context)
    private var lastSearchText: String? = null
    private val movies = ArrayList<Movie>()
    private val handler = Handler(Looper.getMainLooper())

    private val searchRunnable = Runnable {
        val newSearchText = lastSearchText ?: ""
        searchRequest(newSearchText)
    }

    fun onDestroy() {
        handler.removeCallbacks(searchRunnable)
    }


    fun searchDebounce(changedText: String) {
        this.lastSearchText = changedText
        handler.removeCallbacks(searchRunnable)
        handler.postDelayed(searchRunnable, SEARCH_DEBOUNCE_DELAY)
    }

    private fun searchRequest(newSearchText: String) {
        if (newSearchText.isNotEmpty()) {
            view.showLoading()

            moviesInteractor.searchMovies(newSearchText, object : MoviesInteractor.MoviesConsumer {
                override fun consume(foundMovies: List<Movie>?, errorMessage: String?) {
                    handler.post {
                        if (foundMovies != null) {
                            movies.clear()
                            movies.addAll(foundMovies)
                        }

                        when {
                            errorMessage != null -> {
                                view.showError(context.getString(R.string.something_went_wrong))
                                view.showToast(errorMessage)
                            }

                            movies.isEmpty() -> {
                                view.showEmpty(context.getString(R.string.nothing_found))
                            }

                            else -> {
                                view.showContent(movies)
                            }
                        }

                    }
                }
            })
        }
    }

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
    }
}