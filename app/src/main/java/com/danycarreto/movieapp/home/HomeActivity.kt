package com.danycarreto.movieapp.home

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.danycarreto.movieapp.R

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    companion object{
        fun launch(context: Context){
            context.startActivity(Intent(context, HomeActivity::class.java))
        }
    }
}
