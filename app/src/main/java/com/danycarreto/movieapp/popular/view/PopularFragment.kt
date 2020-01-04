package com.danycarreto.movieapp.popular.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.danycarreto.movieapp.R
import com.danycarreto.movieapp.detail.view.DetailActivity
import com.danycarreto.movieapp.home.adapter.OnItemClickListener
import com.danycarreto.movieapp.home.adapter.TopRatedAdapter
import com.danycarreto.movieapp.home.data.model.TopResults
import com.danycarreto.movieapp.home.manager.TopRatedManager
import com.danycarreto.movieapp.popular.presenter.PopularContract
import com.danycarreto.movieapp.popular.presenter.PopularPresenter
import kotlinx.android.synthetic.main.fragment_popular.*


class PopularFragment : Fragment(), PopularContract.PopularView, OnItemClickListener<TopResults> {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_popular, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val presenter = PopularPresenter(this, TopRatedManager())
        presenter.getRequestPopularMovies()
    }

    override fun showLoading() {
        progressUpcoming.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressUpcoming.visibility = View.GONE
    }

    override fun loadPopular(list: List<TopResults>) {
        rvUpcoming.apply {
            adapter = TopRatedAdapter(list, this@PopularFragment)
            layoutManager = GridLayoutManager(activity!!,2, RecyclerView.VERTICAL, false)
        }
    }

    override fun showErrorMessage(message: String) {
        Toast.makeText(activity!!, message, Toast.LENGTH_SHORT).show()
    }


    override fun onItemClick(item: TopResults, position: Int) {
        DetailActivity.lauch(activity!!, item.id.toString())
    }

}
