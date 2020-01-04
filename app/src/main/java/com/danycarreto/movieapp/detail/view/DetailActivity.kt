package com.danycarreto.movieapp.detail.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.danycarreto.movieapp.R
import com.danycarreto.movieapp.detail.presenter.DetailContract
import com.danycarreto.movieapp.detail.presenter.DetailPresenter
import com.danycarreto.movieapp.home.manager.TopRatedManager
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), DetailContract.DetailView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbarDetail)

        val presenter = DetailPresenter(
            this, TopRatedManager()
        )
        presenter.getDetailMovie(intent.getStringExtra("IdMovie"))

    }

    override fun showDetailData(title: String, overview: String, image: String, homepage: String) {
        collapsingDetail.title = title
        txtOverview.text = overview
        Glide.with(this).load("https://image.tmdb.org/t/p/w500$image").into(ivDetailPoster)
    }

    override fun showErrorMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    companion object{
        fun lauch(context: Context, idMovie:String = "0"){
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("IdMovie", idMovie)
            context.startActivity(intent)
        }
    }
}
