package com.mic.debrismanagement.base

import android.content.Context

import com.mic.debrismanagement.mvp.MVPView


/**
 * @author suman
 */
open class BasePresenter<T : MVPView> protected constructor(protected var context: Context) : Presenter<T> {
    var mvpView: T? = null
        private set

    val isViewAttached: Boolean
        get() = mvpView != null

    override fun attachView(mvpView: T) {
        this.mvpView = mvpView
    }

    override fun detachView() {
        mvpView = null
    }

    fun checkViewAttached() {
        if (!isViewAttached) throw MvpViewNotAttachedException()
    }

    class MvpViewNotAttachedException : RuntimeException("Please call Presenter.attachView(MvpView) before" + " requesting data to the Presenter")

}
