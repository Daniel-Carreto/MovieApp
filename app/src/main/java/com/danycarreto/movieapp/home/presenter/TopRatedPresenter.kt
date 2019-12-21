package com.danycarreto.movieapp.home.presenter

import com.danycarreto.movieapp.home.data.model.TopResults
import com.danycarreto.movieapp.home.manager.TopRated

class TopRatedPresenter(
    val topRatedView: TopRatedContract.TopRatedView,
    val topRated:TopRated
): TopRatedContract.TopRatedPresenter, TopRatedContract.TopRatedData {

    init {
        getRequestTopMovies()
    }

    override fun getRequestTopMovies() {
        topRatedView.showLoading()
        topRated.getMoviesTopRated(this)
    }

    override fun onSuccesDataResponse(topRatedMovies: List<TopResults>) {
        topRatedView.hideLoading()
        topRatedView.showTopRatedMovies(topRatedMovies)
    }

    override fun onErrorDataResponse(message: String) {
        topRatedView.hideLoading()
        topRatedView.showErrorData(message)
    }

}