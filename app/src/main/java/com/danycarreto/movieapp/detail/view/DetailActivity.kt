package com.danycarreto.movieapp.detail.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.danycarreto.movieapp.R
import com.danycarreto.movieapp.detail.presenter.DetailContract
import com.danycarreto.movieapp.detail.presenter.DetailPresenter
import com.danycarreto.movieapp.home.manager.TopRatedManager
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), DetailContract.DetailView,
    View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbarDetail)
        /*toolbarDetail.setNavigationOnClickListener{
            onBackPressed()
        }*/
        supportActionBar?.apply{
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        val presenter = DetailPresenter(
            this, TopRatedManager()
        )
        presenter.getDetailMovie(intent.getStringExtra("IdMovie"))

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }



    @SuppressLint("RestrictedApi")
    override fun showDetailData(title: String, overview: String,
                                image: String, homepage: String,
                                showShare:Int) {
        collapsingDetail.title = title
        txtOverview.text = overview
        fabShare.visibility = showShare
        fabShare.tag = homepage
        fabShare.setOnClickListener(this)
        Glide.with(this).load("https://image.tmdb.org/t/p/w500$image").into(ivDetailPoster)
    }

    override fun showErrorMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            fabShare.id->{
                val intent = Intent()
                intent.action = Intent.ACTION_VIEW
                intent.data = Uri.parse(v.tag.toString())
                startActivity(Intent.createChooser(intent, "Abrir con..."))
            }
        }

    }



    companion object{
        fun lauch(context: Context, idMovie:String = "0"){
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("IdMovie", idMovie)
            context.startActivity(intent)
        }
    }
}
