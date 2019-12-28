package com.danycarreto.movieapp.upcoming.presenter

import com.danycarreto.movieapp.home.data.model.TopResults

interface UpcomingContract {
    interface UpcomingView{
        fun showLoading()
        fun hideLoading()
        fun loadUpcoming(list:List<TopResults>)
        fun showErrorMessage(message:String)
    }

    interface UpcomingPresenter{
        fun getRequestUpcomingMovies()
    }

}