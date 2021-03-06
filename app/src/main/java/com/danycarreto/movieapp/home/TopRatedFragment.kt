package com.danycarreto.movieapp.home


import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.danycarreto.movieapp.R
import com.danycarreto.movieapp.detail.view.DetailActivity
import com.danycarreto.movieapp.home.adapter.OnItemClickListener
import com.danycarreto.movieapp.home.adapter.TopRatedAdapter
import com.danycarreto.movieapp.home.data.model.TopResults
import com.danycarreto.movieapp.home.manager.TopRatedManager
import com.danycarreto.movieapp.home.presenter.TopRatedContract
import com.danycarreto.movieapp.home.presenter.TopRatedPresenter
import kotlinx.android.synthetic.main.fragment_top_rated.*

class TopRatedFragment : Fragment(),
    TopRatedContract.TopRatedView, SearchView.OnQueryTextListener, OnItemClickListener<TopResults> {

    private lateinit var topRatePresenter: TopRatedContract.TopRatedPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_top_rated, menu)
        val searchItem: MenuItem? = menu?.findItem(R.id.action_search)
        val searchManager= activity!!.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView: SearchView? = searchItem?.actionView as SearchView
        searchView?.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
        searchView?.setOnQueryTextListener(this)
        searchView?.queryHint = "Escribe el título"
        searchView?.isIconified = true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        topRatePresenter.getSearchMovieWithTitle(query.orEmpty())
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        (rvTopRatedMovies?.adapter as? TopRatedAdapter)?.filter?.filter(newText.orEmpty())
        return true
    }


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
        progressTop?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressTop?.visibility = View.GONE
    }

    override fun showTopRatedMovies(topMovies: List<TopResults>) {
        rvTopRatedMovies?.apply {
            adapter = TopRatedAdapter(topMovies, this@TopRatedFragment)
            layoutManager = LinearLayoutManager(activity!!)
        }
    }

    override fun showErrorData(message: String) {
        val dialog = AlertDialog.Builder(activity!!)
        dialog.setTitle(getString(R.string.app_name))
        dialog.setMessage(message)
        dialog.setNeutralButton(getString(R.string.login_neutral_label), null)
        dialog.create()
        dialog.show()
    }

    override fun onItemClick(item: TopResults, position: Int) {
        DetailActivity.lauch(activity!!, item.id.toString())
    }
}
