package com.danycarreto.movieapp.login.data

import android.content.SharedPreferences
import com.danycarreto.movieapp.login.data.PreferencesProvider.IS_USER_LOGIN
import com.danycarreto.movieapp.login.data.PreferencesProvider.USER_NAME

class UserPreferenceManager: UserPreferences {

    var sharedPreferences: SharedPreferences? = null

    init {
        sharedPreferences = PreferencesProvider.providePreferences()
    }

    override fun setUserLoginSuccess(status: Boolean) {
        sharedPreferences?.edit()?.putBoolean(IS_USER_LOGIN,status)?.apply()
    }

    override fun setUserNameSuccess(username: String) {
        sharedPreferences?.edit()?.putString(USER_NAME, username)?.apply()
    }
    override fun isUserLogin(): Boolean {
        return sharedPreferences?.getBoolean(IS_USER_LOGIN, false)!!
    }

    override fun clearUserSession() {
        sharedPreferences?.edit()?.clear()?.apply()
    }
}