package com.danycarreto.movieapp.login.data

import android.content.Context
import android.content.SharedPreferences

object PreferencesProvider {
    var IS_USER_LOGIN = "isUserLogin"
    var USER_NAME = "username"
    var sharedPreference :SharedPreferences? = null

    fun providePreferences():SharedPreferences?{
        return sharedPreference
    }

    fun init(context: Context){
        sharedPreference = context
            .getSharedPreferences("Login", Context.MODE_PRIVATE)
    }
}