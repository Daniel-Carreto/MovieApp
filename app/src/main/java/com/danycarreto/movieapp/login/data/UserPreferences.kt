package com.danycarreto.movieapp.login.data

interface UserPreferences {
    fun setUserLoginSuccess(status: Boolean)
    fun setUserNameSuccess(username:String)
    fun isUserLogin(): Boolean
    fun clearUserSession()
}