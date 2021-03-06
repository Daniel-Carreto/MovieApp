package com.danycarreto.movieapp.home.manager

import com.danycarreto.movieapp.detail.model.DetailResponse
import com.danycarreto.movieapp.detail.presenter.DetailContract
import com.danycarreto.movieapp.home.data.model.RatedResponse
import com.danycarreto.movieapp.home.data.network.NetworkConnection
import com.danycarreto.movieapp.home.presenter.TopRatedContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopRatedManager: TopRated {

    override fun getMoviesTopRated(topRatedData: TopRatedContract.TopRatedData) {
        NetworkConnection()
            .getNetworkConnection()
            ?.listRatedMovies("https://api.themoviedb.org/3/movie/top_rated?api_key=568ff4df19633325b978ea1b75fe2290&language=en-US&page=1")
            ?.enqueue(object: Callback<RatedResponse>{
                override fun onFailure(call: Call<RatedResponse>, t: Throwable) {
                    topRatedData.onErrorDataResponse(t.message.orEmpty())
                }

                override fun onResponse(
                    call: Call<RatedResponse>,
                    response: Response<RatedResponse>
                ) {
                    topRatedData.onSuccesDataResponse(response.body()?.results ?: listOf() )
                }

            })
    }


    override fun getMovieSearch(search: String, topRatedData: TopRatedContract.TopRatedData) {
        NetworkConnection()
            .getNetworkConnection()
            ?.listRatedMovies("\n" +
                    "https://api.themoviedb.org/3/search/movie?api_key=568ff4df19633325b978ea1b75fe2290&language=en-US&query=${search.replace(" ", "+")}")
            ?.enqueue(object: Callback<RatedResponse>{
                override fun onFailure(call: Call<RatedResponse>, t: Throwable) {
                    topRatedData.onErrorDataResponse(t.message.orEmpty())
                }

                override fun onResponse(
                    call: Call<RatedResponse>,
                    response: Response<RatedResponse>
                ) {
                    topRatedData.onSuccesDataResponse(response.body()?.results ?: listOf() )
                }

            })
    }

    override fun getPopularMovies(topRatedData: TopRatedContract.TopRatedData) {
        NetworkConnection()
            .getNetworkConnection()
            ?.listRatedMovies("https://api.themoviedb.org/3/movie/popular?api_key=568ff4df19633325b978ea1b75fe2290&language=en-US&page=1")
            ?.enqueue(object: Callback<RatedResponse>{
                override fun onFailure(call: Call<RatedResponse>, t: Throwable) {
                    topRatedData.onErrorDataResponse(t.message.orEmpty())
                }

                override fun onResponse(
                    call: Call<RatedResponse>,
                    response: Response<RatedResponse>
                ) {
                    topRatedData.onSuccesDataResponse(response.body()?.results ?: listOf() )
                }

            })
    }


    override fun getUpComingMovies(topRatedData: TopRatedContract.TopRatedData) {
        NetworkConnection()
            .getNetworkConnection()
            ?.listRatedMovies("https://api.themoviedb.org/3/movie/upcoming?api_key=568ff4df19633325b978ea1b75fe2290&language=en-US&page=1")
            ?.enqueue(object: Callback<RatedResponse>{
                override fun onFailure(call: Call<RatedResponse>, t: Throwable) {
                    topRatedData.onErrorDataResponse(t.message.orEmpty())
                }

                override fun onResponse(
                    call: Call<RatedResponse>,
                    response: Response<RatedResponse>
                ) {
                    topRatedData.onSuccesDataResponse(response.body()?.results ?: listOf() )
                }

            })
    }


    override fun getMovieDetail(id: String, detailData: DetailContract.DetailData) {
        NetworkConnection()
            .getNetworkConnection()
            ?.getDetailMovie("https://api.themoviedb.org/3/movie/{movie_id}?api_key=568ff4df19633325b978ea1b75fe2290&language=en-US".replace("{movie_id}",id))
            ?.enqueue(object : Callback<DetailResponse> {
                override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                    detailData.onErrorDataResponse(t.message.orEmpty())
                }

                override fun onResponse(
                    call: Call<DetailResponse>,
                    response: Response<DetailResponse>
                ) {
                    detailData.onSuccessDataResponse(response.body()!!)
                }

            })

    }
}