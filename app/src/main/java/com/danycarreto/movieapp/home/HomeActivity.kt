package com.danycarreto.movieapp.home

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.danycarreto.movieapp.R
import com.danycarreto.movieapp.popular.view.PopularFragment
import com.danycarreto.movieapp.upcoming.view.UpcomingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbarTop)
        bottomNavigationView.setOnNavigationItemSelectedListener(this)
        supportFragmentManager
            .beginTransaction()
            .replace(topRatedFragment.id, PopularFragment())
            .commit()
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.action_popular -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(topRatedFragment.id, PopularFragment())
                    .commit()
            }
            R.id.action_top_rated -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(topRatedFragment.id, TopRatedFragment())
                    .commit()
            }

            R.id.action_upcoming -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(topRatedFragment.id, UpcomingFragment())
                    .commit()
            }

        }
        return false
    }

    companion object {
        fun launch(context: Context) {
            context.startActivity(Intent(context, HomeActivity::class.java))
        }
    }
}
