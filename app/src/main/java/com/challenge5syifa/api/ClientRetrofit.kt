package com.challenge5syifa.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ClientRetrofit {
    private const val BASE_URL = "https://api.themoviedb.org/3/movie/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
    val key = "1805007963a4760044640bde94121491"
    val page = 500;
}