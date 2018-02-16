package com.mic.rims.ui.activities

import android.content.Intent
import android.os.Bundle
import com.mic.rims.R
import com.mic.rims.base.BaseActivity
import com.mic.rims.mvp.model.User
import com.mic.rims.mvp.presenter.MainActivityPresenter
import com.mic.rims.mvp.view.MainActivityView
import com.mic.rims.persistence.PreferencesHelper
import com.mic.rims.utils.ProgressBarHandler
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainActivityView {
    @Inject lateinit var presenter: MainActivityPresenter
    @Inject lateinit var preferenceHelper: PreferencesHelper
    private var progressBarHandler: ProgressBarHandler? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);
        getActivityComponent().inject(this)
        progressBarHandler = ProgressBarHandler(this)
        presenter.attachView(this)
        presenter.getLoggedInUser(preferenceHelper.userId)
        take_survey.setOnClickListener { startActivity(Intent(this,SurveyActivity::class.java)) }
    }

    override fun onSuccessFetchUser(user: User) {
        showUserDetail(user)
    }

    private fun showUserDetail(user: User) {
       showToast(user.name!!)
    }

    override fun onFailureFetchUser(message: String?) {
        showToast(message!!)
    }

    override fun showProgress() {
        progressBarHandler!!.show()
    }

    override fun hideProgress() {
        progressBarHandler!!.hide()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

}
