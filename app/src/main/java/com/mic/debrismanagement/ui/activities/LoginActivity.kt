package com.mic.debrismanagement.ui.activities

import android.content.Intent
import android.os.Bundle
import com.mic.debrismanagement.R
import com.mic.debrismanagement.base.BaseActivity
import com.mic.debrismanagement.mvp.model.User
import com.mic.debrismanagement.mvp.presenter.LoginPresenter
import com.mic.debrismanagement.mvp.view.LoginView
import com.mic.debrismanagement.utils.ProgressBarHandler
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : BaseActivity(), LoginView {


    @Inject lateinit var presenter: LoginPresenter
    private lateinit var progressBarHandler: ProgressBarHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        getActivityComponent().inject(this)
        progressBarHandler = ProgressBarHandler(this)
        presenter.attachView(this)
        presenter.checkIsLoggedIn()
        login.setOnClickListener {
            presenter.login(username.text.toString().trim(), password.text.toString())
        }
    }

    override fun showUsernameError(string: String?, field: String?) {
        showError(string, field)
    }

    private fun showError(string: String?, field: String?) {
        showSnackBar(container,field+" "+string)
    }

    override fun showPasswordError(string: String?, field: String?) {
        showError(string, field)
    }

    override fun showProgress() {
        progressBarHandler.show()
    }

    override fun hideProgress() {
        progressBarHandler.hide()
    }

    override fun onSuccessLogin(user: User) {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onFailureLogin(message: String?) {
        showSnackBar(container,message.toString())
    }

    override fun gotoMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}
