package com.mic.rims.ui.activities

import android.os.Bundle
import android.support.design.widget.Snackbar
import com.mic.rims.R
import com.mic.rims.base.BaseActivity
import com.mic.rims.mvp.model.User
import com.mic.rims.mvp.presenter.LoginPresenter
import com.mic.rims.mvp.view.LoginView
import com.mic.rims.utils.ProgressBarHandler
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
        login.setOnClickListener {
            presenter.login(username.text.toString().trim(), password.text.toString())
        }
    }

    override fun showUsernameError(string: String?, field: String?) {
        showError(string, field)
    }

    private fun showError(string: String?, field: String?) {
        Snackbar.make(container, field + " " + string, Snackbar.LENGTH_SHORT).show()
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
        Snackbar.make(container, user.name, Snackbar.LENGTH_SHORT).show()
    }

    override fun onFailureLogin(message: String?) {
        Snackbar.make(container, message.toString() + "", Snackbar.LENGTH_SHORT).show()
    }
}
