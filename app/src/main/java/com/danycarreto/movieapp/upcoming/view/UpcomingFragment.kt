package com.danycarreto.movieapp.upcoming.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

import com.danycarreto.movieapp.R
import com.danycarreto.movieapp.detail.view.DetailActivity
import com.danycarreto.movieapp.home.adapter.OnItemClickListener
import com.danycarreto.movieapp.home.adapter.TopRatedAdapter
import com.danycarreto.movieapp.home.data.model.TopResults
import com.danycarreto.movieapp.home.manager.TopRatedManager
import com.danycarreto.movieapp.upcoming.presenter.UpcomingContract
import com.danycarreto.movieapp.upcoming.presenter.UpcomingPresenter
import kotlinx.android.synthetic.main.fragment_popular.*
import kotlinx.android.synthetic.main.fragment_popular.progressUpcoming
import kotlinx.android.synthetic.main.fragment_popular.rvUpcoming
import kotlinx.android.synthetic.main.fragment_upcoming.*

class UpcomingFragment : Fragment(), UpcomingContract.UpcomingView,
    SwipeRefreshLayout.OnRefreshListener, OnItemClickListener<TopResults> {

    private lateinit var presenter: UpcomingContract.UpcomingPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_upcoming, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeUpcoming.setOnRefreshListener(this)
        presenter = UpcomingPresenter(this, TopRatedManager())
        presenter.getRequestUpcomingMovies()
    }

    override fun showLoading() {
        progressUpcoming.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressUpcoming.visibility = View.GONE
    }

    override fun loadUpcoming(list: List<TopResults>) {
        swipeUpcoming.isRefreshing = false
        rvUpcoming.apply {
            adapter = TopRatedAdapter(list,this@UpcomingFragment)
            layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        }
    }

    override fun showErrorMessage(message: String) {
        Toast.makeText(activity!!, message, Toast.LENGTH_SHORT).show()
    }


    override fun onRefresh() {
        presenter.getRequestUpcomingMovies()
    }

    override fun onItemClick(item: TopResults, position: Int) {
        DetailActivity.lauch(activity!!, item.id.toString())
    }
}
