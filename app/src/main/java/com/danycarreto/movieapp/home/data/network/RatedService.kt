package com.danycarreto.movieapp.home.data.network

import com.danycarreto.movieapp.home.data.model.RatedResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface RatedService {

    @GET
    fun listRatedMovies(@Url url:String): Call<RatedResponse>


}