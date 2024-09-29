package com.example.movies.data

import com.example.movies.data.dto.MoviesSearchRequest
import com.example.movies.data.dto.MoviesSearchResponse
import com.example.movies.data.network.NetworkClient
import com.example.movies.domain.api.MoviesRepository
import com.example.movies.domain.models.Movie
import com.example.movies.util.Resource

class MoviesRepositoryImpl(private val networkClient: NetworkClient) : MoviesRepository {

    override fun searchMovies(expression: String): Resource<List<Movie>> {
        val response = networkClient.doRequest(MoviesSearchRequest(expression))
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }
            200 -> {
                Resource.Success((response as MoviesSearchResponse).results.map {
                    Movie(it.id, it.resultType, it.image, it.title, it.description)})
            }
            else -> {
                Resource.Error("Ошибка сервера")
            }
        }
    }
}