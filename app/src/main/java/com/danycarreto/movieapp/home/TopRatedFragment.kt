package com.danycarreto.movieapp.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.danycarreto.movieapp.R
import com.danycarreto.movieapp.home.data.model.TopResults
import com.danycarreto.movieapp.home.manager.TopRatedManager
import com.danycarreto.movieapp.home.presenter.TopRatedContract
import com.danycarreto.movieapp.home.presenter.TopRatedPresenter
import kotlinx.android.synthetic.main.fragment_top_rated.*

class TopRatedFragment : Fragment(),
    TopRatedContract.TopRatedView {

    private lateinit var topRatePresenter:TopRatedContract.TopRatedPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_top_rated, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        topRatePresenter = TopRatedPresenter(
            this,
            TopRatedManager()
        )
    }

    override fun showLoading() {
        progressTop.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressTop.visibility = View.GONE
    }

    override fun showTopRatedMovies(topMovies: List<TopResults>) {

    }

    override fun showErrorData(message: String) {

    }





}
