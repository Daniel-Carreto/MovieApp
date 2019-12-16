package com.danycarreto.movieapp.login.viewimport androidx.appcompat.app.AppCompatActivityimport android.os.Bundleimport android.view.Viewimport android.widget.Toastimport androidx.appcompat.app.AlertDialogimport com.danycarreto.movieapp.Rimport com.danycarreto.movieapp.home.HomeActivityimport com.danycarreto.movieapp.login.data.UserPreferenceManagerimport com.danycarreto.movieapp.login.presenter.LoginContractimport com.danycarreto.movieapp.login.presenter.LoginPresenterimport com.danycarreto.movieapp.login.resources.LoginResourcesimport kotlinx.android.synthetic.main.activity_login.*class LoginActivity : AppCompatActivity(), LoginContract.LoginView {    private lateinit var loginPresenter: LoginContract.LoginPresenter    override fun onCreate(savedInstanceState: Bundle?) {        super.onCreate(savedInstanceState)        setContentView(R.layout.activity_login)        supportActionBar?.hide()        loginPresenter = LoginPresenter(            this,            LoginResources(this),            UserPreferenceManager()        )        btnLogin.setOnClickListener {            loginPresenter.login(                tietUser.text.toString(),                tietPassword.text.toString()            )        }    }    override fun showLoading() {        progressBar.visibility = View.VISIBLE    }    override fun hideLoading() {        progressBar.visibility = View.GONE    }    override fun onSuccessLogin(message: String) {        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()        HomeActivity.launch(this)    }    override fun showErrorMessage(message: String) {        val dialog = AlertDialog.Builder(this)        dialog.setTitle(getString(R.string.app_name))        dialog.setMessage(message)        dialog.setNeutralButton(getString(R.string.login_neutral_label), null)        dialog.create()        dialog.show()    }}