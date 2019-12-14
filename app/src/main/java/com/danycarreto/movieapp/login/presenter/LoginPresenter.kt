package com.danycarreto.movieapp.login.presenter

import android.util.Patterns
import com.danycarreto.movieapp.login.resources.LoginResources

class LoginPresenter(
    val loginView: LoginContract.LoginView,
    val loginResources: LoginResources
) : LoginContract.LoginPresenter {

    override fun login(email: String, password: String) {
        loginView.showLoading()
        if(email.isEmpty()){
            loginView.showErrorMessage(loginResources.userEmpty)
            loginView.hideLoading()
            return
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            loginView.showErrorMessage(loginResources.userInvalid)
            loginView.hideLoading()
            return
        }
        if(password.isEmpty()){
            loginView.showErrorMessage(loginResources.passwordEmpty)
            loginView.hideLoading()
            return
        }
        loginView.hideLoading()
        loginView.onSuccessLogin(loginResources.successLogin.format(email))
    }

}