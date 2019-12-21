package com.danycarreto.movieapp.home.manager

import com.danycarreto.movieapp.home.presenter.TopRatedContract

interface TopRated {
    fun getMoviesTopRated(topRatedData: TopRatedContract.TopRatedData)
}