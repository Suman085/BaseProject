package com.mic.debrismanagement.base


import com.mic.debrismanagement.mvp.MVPView

/**
 * @author Suman
 */
interface Presenter<V : MVPView> {

    fun attachView(mvpView: V)

    fun detachView()

}
