package com.danycarreto.movieapp.popular.presenter

import com.danycarreto.movieapp.home.data.model.TopResults
import com.danycarreto.movieapp.home.manager.TopRated
import com.danycarreto.movieapp.home.presenter.TopRatedContract

class PopularPresenter(
    private val popularView: PopularContract.PopularView,
    private val topRated: TopRated
): PopularContract.PopularPresenter, TopRatedContract.TopRatedData {


    override fun getRequestPopularMovies() {
        popularView.showLoading()
        topRated.getPopularMovies(this)
    }

    override fun onSuccesDataResponse(topRatedMovies: List<TopResults>) {
        popularView.hideLoading()
        popularView.loadPopular(topRatedMovies)
    }

    override fun onErrorDataResponse(message: String) {
        popularView.hideLoading()
        popularView.showErrorMessage(message)
    }


}