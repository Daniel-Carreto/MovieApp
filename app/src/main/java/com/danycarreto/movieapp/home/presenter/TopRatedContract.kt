package com.danycarreto.movieapp.home.presenter

import com.danycarreto.movieapp.home.data.model.RatedResponse
import com.danycarreto.movieapp.home.data.model.TopResults

interface TopRatedContract {

    interface TopRatedView{
        fun showLoading()
        fun hideLoading()
        fun showTopRatedMovies(topMovies: List<TopResults>)
        fun showErrorData(message:String)
    }

    interface TopRatedPresenter{
        fun getRequestTopMovies()
    }

    interface TopRatedData{
        fun onSuccesDataResponse(topRatedMovies:List<TopResults>)
        fun onErrorDataResponse(message:String)
    }

}