package com.danycarreto.movieapp.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.danycarreto.movieapp.R
import com.danycarreto.movieapp.cinemas.CinemaActivity
import com.danycarreto.movieapp.popular.view.PopularFragment
import com.danycarreto.movieapp.upcoming.view.UpcomingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.view.*

class HomeActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(home_content.toolbarTop)
        supportActionBar?.apply {
            setDisplayShowHomeEnabled(true)
        }
        home_content.bottomNavigationView.setOnNavigationItemSelectedListener(this)
        supportFragmentManager
            .beginTransaction()
            .replace(home_content.topRatedFragment.id, PopularFragment())
            .commit()

        val toggle = ActionBarDrawerToggle(
            this, drawerHome, home_content.toolbarTop,
            R.string.app_name, R.string.app_name
        )
        toggle.syncState()
        drawerHome.addDrawerListener(toggle)
        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    print("Home")
                }
                R.id.nav_cinema -> {
                    CinemaActivity.launch(this)
                }
            }
            drawerHome.closeDrawers()
            false
        }

    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.action_popular -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(home_content.topRatedFragment.id, PopularFragment())
                    .commit()
            }
            R.id.action_top_rated -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(home_content.topRatedFragment.id, TopRatedFragment())
                    .commit()
            }

            R.id.action_upcoming -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(home_content.topRatedFragment.id, UpcomingFragment())
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
