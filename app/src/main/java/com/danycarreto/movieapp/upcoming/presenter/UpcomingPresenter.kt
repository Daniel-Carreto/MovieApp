package com.danycarreto.movieapp.upcoming.presenter

import com.danycarreto.movieapp.home.data.model.TopResults
import com.danycarreto.movieapp.home.manager.TopRated
import com.danycarreto.movieapp.home.presenter.TopRatedContract

class UpcomingPresenter(
    private val upcomingView: UpcomingContract.UpcomingView,
    private val topRated: TopRated
): UpcomingContract.UpcomingPresenter, TopRatedContract.TopRatedData {


    override fun getRequestUpcomingMovies() {
        upcomingView.showLoading()
        topRated.getUpComingMovies(this)
    }

    override fun onSuccesDataResponse(topRatedMovies: List<TopResults>) {
        upcomingView.hideLoading()
        upcomingView.loadUpcoming(topRatedMovies)
    }

    override fun onErrorDataResponse(message: String) {
        upcomingView.hideLoading()
        upcomingView.showErrorMessage(message)
    }


}