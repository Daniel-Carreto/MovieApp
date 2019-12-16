package com.danycarreto.movieapp

import android.app.Application
import com.danycarreto.movieapp.login.data.PreferencesProvider

class MovieAppAplication: Application() {

    override fun onCreate() {
        super.onCreate()
        PreferencesProvider.init(this)
    }
}