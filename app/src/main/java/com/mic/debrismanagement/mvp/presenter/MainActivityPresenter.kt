package com.mic.debrismanagement.mvp.presenter

import android.content.Context
import com.mic.debrismanagement.R
import com.mic.debrismanagement.api.DataManager
import com.mic.debrismanagement.base.BasePresenter
import com.mic.debrismanagement.injection.ApplicationContext
import com.mic.debrismanagement.mvp.view.MainActivityView
import com.mic.debrismanagement.persistence.DatabaseHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Suman on 2/16/2018.
 */


class MainActivityPresenter @Inject constructor(private val dataManager: DataManager, private val databaseHelper: DatabaseHelper, @ApplicationContext context: Context) : BasePresenter<MainActivityView>(context) {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun detachView() {
        super.detachView()
        compositeDisposable.dispose()
    }

    fun getLoggedInUser(id:Long) {
        checkViewAttached()
            mvpView!!.showProgress()
            compositeDisposable.add(databaseHelper.getUserDao()!!.getUser(id).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ user ->
                        mvpView!!.onSuccessFetchUser(user)
                        mvpView!!.hideProgress()
                    },
                            { t: Throwable? ->
                                mvpView!!.onFailureFetchUser(t?.message)
                                mvpView!!.hideProgress()
                            }))

    }

}