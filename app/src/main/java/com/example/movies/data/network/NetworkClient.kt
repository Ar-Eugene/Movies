package com.example.movies.data.network

import com.example.movies.data.dto.Response


interface NetworkClient {
    fun doRequest(dto: Any): Response

}