package com.challenge5syifa.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id")
    val id:String,
    @SerializedName("title")
    val judul:String,
    @SerializedName("overview")
    val deskripsi:String,
    @SerializedName("poster_path")
    val foto:String,
    @SerializedName("popularity")
    val popularity:String,
    @SerializedName("release_date")
    val releaseDate:String,
    @SerializedName("vote_average")
    val voteAverage:String
)

