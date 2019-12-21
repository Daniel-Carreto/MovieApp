package com.danycarreto.movieapp.home.data.model

import com.google.gson.annotations.SerializedName

data class TopResults(
    val id: Long,
    @SerializedName("poster_path")
    val poster: String,
    val title: String,
    val overview:String,
    @SerializedName("release_date")
    val releaseDate:String
)