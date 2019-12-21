package com.danycarreto.movieapp.home

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.danycarreto.movieapp.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbarTop)
        supportFragmentManager
            .beginTransaction()
            .replace(topRatedFragment.id, TopRatedFragment())
            .commit()
    }

    companion object{
        fun launch(context: Context){
            context.startActivity(Intent(context, HomeActivity::class.java))
        }
    }
}
