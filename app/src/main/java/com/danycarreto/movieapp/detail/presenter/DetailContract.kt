package com.danycarreto.movieapp.detail.presenter

import com.danycarreto.movieapp.detail.model.DetailResponse

interface DetailContract {
    interface DetailView{
        fun showDetailData(title:String, overview:String,
                           image:String, homepage:String,
                           showShare:Int)
        fun showErrorMessage(message:String)
    }
    interface DetailPresenter{
        fun getDetailMovie(id:String)
    }

    interface DetailData{
        fun onSuccessDataResponse(movieDetail:DetailResponse)
        fun onErrorDataResponse(message:String)
    }
}