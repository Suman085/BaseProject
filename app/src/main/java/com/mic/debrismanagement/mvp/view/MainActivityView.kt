package com.mic.debrismanagement.mvp.view

import com.mic.debrismanagement.mvp.MVPView
import com.mic.debrismanagement.mvp.model.User

/**
 * Created by Suman on 2/16/2018.
 */
interface MainActivityView : MVPView {
    fun onSuccessFetchUser(user: User)
    fun onFailureFetchUser(message: String?)

}