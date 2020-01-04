package com.danycarreto.movieapp.detail.model

import com.google.gson.annotations.SerializedName

data class DetailResponse(
    val id:Long?,
    @SerializedName("original_title")
    val title:String?,
    val overview:String?,
    val homepage:String?,
    @SerializedName("poster_path")
    val posterPath:String?
)