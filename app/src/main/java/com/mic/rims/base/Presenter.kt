package com.mic.rims.base


import com.mic.rims.mvp.MVPView

/**
 * @author Suman
 */
interface Presenter<V : MVPView> {

    fun attachView(mvpView: V)

    fun detachView()

}
