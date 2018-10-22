package com.mic.debrismanagement.mvp.presenter

import android.content.Context
import com.mic.debrismanagement.R
import com.mic.debrismanagement.api.DataManager
import com.mic.debrismanagement.base.BasePresenter
import com.mic.debrismanagement.injection.ApplicationContext
import com.mic.debrismanagement.mvp.model.User
import com.mic.debrismanagement.mvp.view.LoginView
import com.mic.debrismanagement.persistence.DatabaseHelper
import com.mic.debrismanagement.persistence.PreferencesHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Suman on 2/15/2018.
 */

class LoginPresenter @Inject constructor(private val dataManager: DataManager, private val databaseHelper: DatabaseHelper, @ApplicationContext context: Context) : BasePresenter<LoginView>(context) {
    @Inject lateinit var preferenceHelper: PreferencesHelper
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun detachView() {
        super.detachView()
        compositeDisposable.dispose()
    }

    fun login(username: String, password: String) {
        checkViewAttached()
        if (isCredentialsValid(username, password)) {
            mvpView!!.showProgress()
            compositeDisposable.add(dataManager.login(username, password).subscribeOn(Schedulers.io())
                    .doOnNext { user: User ->
                        databaseHelper.getUserDao()!!.insert(user)
                        preferenceHelper.userId = user.userId!!.toLong()
                        preferenceHelper.setIsLoggedIn(true)
                    }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ user ->
                        mvpView!!.onSuccessLogin(user)
                        mvpView!!.hideProgress()
                    },
                            { t: Throwable? ->
                                mvpView!!.onFailureLogin(t?.message)
                                mvpView!!.hideProgress()
                            }))
        }
    }

    private fun isCredentialsValid(username: String?, password: String?): Boolean {

        val resources = context.resources
        val correctUsername = username!!.replaceFirst("\\s++$".toRegex(), "").trim { it <= ' ' }
        if (username.isEmpty()) {
            mvpView!!.showUsernameError(context.getString(R.string.error_validation_blank), context.getString(R.string.username))
            return false
        } else if (username.length < 4) {
            mvpView!!.showUsernameError(context.getString(R.string.error_validation_minimum_chars), resources.getString(R.string.username))
            return false
        } else if (correctUsername.contains(" ")) {
            mvpView!!.showUsernameError(context.getString(
                    R.string.error_validation_cannot_contain_spaces),
                    context.getString(R.string.username))
            return false
        } else if (password == null || password.matches("\\s*".toRegex()) || password.isEmpty()) {
            mvpView!!.showPasswordError(context.getString(R.string.error_validation_blank),
                    context.getString(R.string.password))
            return false
        } else if (password.length < 4) {
            mvpView!!.showPasswordError(context.getString(R.string.error_validation_minimum_chars), resources.getString(R.string.password))
            return false
        }

        return true
    }

    fun checkIsLoggedIn() {
        checkViewAttached()
        if(preferenceHelper.isLoggedIn){
            mvpView!!.gotoMainActivity();
        }
    }
}