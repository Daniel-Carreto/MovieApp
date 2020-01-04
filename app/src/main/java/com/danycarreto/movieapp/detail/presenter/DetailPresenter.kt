package com.danycarreto.movieapp.detail.presenter

import com.danycarreto.movieapp.detail.model.DetailResponse
import com.danycarreto.movieapp.home.manager.TopRated

class DetailPresenter(
    val detailView:DetailContract.DetailView,
    val topRated: TopRated
): DetailContract.DetailPresenter, DetailContract.DetailData {

    override fun getDetailMovie(id: String) {
        topRated.getMovieDetail(id, this)
    }

    override fun onSuccessDataResponse(movieDetail: DetailResponse) {
        detailView.showDetailData(
            movieDetail.title.orEmpty(),
            movieDetail.overview.orEmpty(),
            movieDetail.posterPath.orEmpty(),
            movieDetail.homepage.orEmpty()
        )
    }

    override fun onErrorDataResponse(message: String) {
        detailView.showErrorMessage(message)
    }
}