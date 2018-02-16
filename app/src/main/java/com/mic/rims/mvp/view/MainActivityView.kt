package com.mic.rims.mvp.view

import com.mic.rims.mvp.MVPView
import com.mic.rims.mvp.model.User

/**
 * Created by Suman on 2/16/2018.
 */
interface MainActivityView : MVPView {
    fun onSuccessFetchUser(user: User)
    fun onFailureFetchUser(message: String?)

}