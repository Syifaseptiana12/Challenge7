package com.challenge5syifa.api

import com.challenge5syifa.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("popular")
    fun getMovies(
        @Query("api_key") key: String,
        @Query("page") page: Int
    ): Call<MovieResponse>
}