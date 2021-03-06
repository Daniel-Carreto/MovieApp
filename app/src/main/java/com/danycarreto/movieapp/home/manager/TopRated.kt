package com.danycarreto.movieapp.home.manager

import com.danycarreto.movieapp.detail.presenter.DetailContract
import com.danycarreto.movieapp.home.presenter.TopRatedContract

interface TopRated {
    fun getMoviesTopRated(topRatedData: TopRatedContract.TopRatedData)
    fun getMovieSearch(search:String, topRatedData: TopRatedContract.TopRatedData)
    fun getPopularMovies(topRatedData: TopRatedContract.TopRatedData)
    fun getUpComingMovies(topRatedData: TopRatedContract.TopRatedData)
    fun getMovieDetail(id:String,detailData: DetailContract.DetailData)
}